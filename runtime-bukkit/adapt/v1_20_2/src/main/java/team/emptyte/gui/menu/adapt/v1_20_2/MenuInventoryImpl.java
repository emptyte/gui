/*
 * This file is part of storage, licensed under the MIT License
 *
 * Copyright (c) 2025 Emptyte Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package team.emptyte.gui.menu.adapt.v1_20_2;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.List;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.craftbukkit.v1_20_R2.inventory.CraftInventoryCustom;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.adapt.menu.MenuInventory;
import team.emptyte.gui.component.BukkitComponent;
import team.emptyte.gui.menu.item.MenuItem;
import team.emptyte.gui.menu.item.action.MenuItemAction;
import team.emptyte.gui.tree.TreeHelper;

public class MenuInventoryImpl extends CraftInventoryCustom implements MenuInventory {
  private final Int2ObjectOpenHashMap<BukkitComponent> rendered;
  private final boolean allowItemInsertion;

  public MenuInventoryImpl(
    final @NotNull BukkitComponent root,
    final @NotNull String title,
    final int size,
    final boolean allowItemInsertion
  ) {
    super(null, size, MiniMessage.miniMessage().deserialize(title));

    this.rendered = new Int2ObjectOpenHashMap<>();
    this.allowItemInsertion = allowItemInsertion;

    // Render the components to the inventory
    for (final BukkitComponent bukkitComponent : TreeHelper.flatten(root, BukkitComponent.class)) {
      // Render the component
      for (final @NotNull MenuItem menuItem : bukkitComponent.render()) {
        final int slot = menuItem.slot();
        final ItemStack currentItem = this.getItem(slot);
        // Skip if the item is already set
        if (menuItem.item().equals(currentItem)) {
          continue;
        }
        this.setItem(slot, menuItem.item());
        this.rendered.put(slot, bukkitComponent);
      }
    }
  }

  private void reconcile(final @NotNull BukkitComponent dirty) {
    // Get the base component
    final BukkitComponent base = this.rendered.values()
      .stream()
      .filter(component -> component.id().equals(dirty.id()))
      .findFirst()
      .orElse(null);
    if (base == null) {
      // The component is not rendered
      return;
    }
    // Get the differences between the equivalent and dirty components
    final List<BukkitComponent> diffing = TreeHelper.diff(base, dirty, BukkitComponent.class);
    if (diffing.isEmpty()) {
      // No differences
      return;
    }
    // Re-render the differences
    for (final BukkitComponent diff : diffing) {
      for (final MenuItem menuItem : diff.render()) {
        final int slot = menuItem.slot();
        super.setItem(slot, menuItem.item());
        this.rendered.put(slot, diff);
      }
    }
  }

  @Override
  public boolean onClick(final @NotNull Player player, final int clickedSlot, final @NotNull ClickType clickType) {
    // Get the component that was clicked
    BukkitComponent bukkitComponent = this.rendered.get(clickedSlot);
    if (bukkitComponent == null) {
      return !this.allowItemInsertion;
    }
    bukkitComponent = (BukkitComponent) bukkitComponent.clone();
    // Get the menu item that was clicked
    final MenuItem menuItem = bukkitComponent.render()
      .stream()
      .filter(item -> item.slot() == clickedSlot)
      .findFirst()
      .orElse(null);
    if (menuItem == null) {
      return !this.allowItemInsertion;
    }
    // Execute the action
    final MenuItemAction action = menuItem.action();
    if (action == null) {
      return !this.allowItemInsertion;
    }
    // Execute the action
    if (action.execute(player, clickedSlot, clickType)) {
      // Reconcile the component if the action was successful,
      // otherwise, it would be meaningless since there was no change.
      this.reconcile(bukkitComponent);
      return true;
    } else {
      return !this.allowItemInsertion;
    }
  }
}
