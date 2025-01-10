/*
 * This file is part of storage, licensed under the MIT License
 *
 * Copyright (c) 2024 Emptyte Team
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
package team.emptyte.gui.menu.adapt.v1_21;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.craftbukkit.inventory.CraftInventoryCustom;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.emptyte.gui.BukkitComponent;
import team.emptyte.gui.Component;
import team.emptyte.gui.adapt.menu.MenuInventory;
import team.emptyte.gui.menu.item.MenuItem;
import team.emptyte.gui.util.TreeHelper;

import java.util.List;

public class MenuInventoryImpl extends CraftInventoryCustom implements MenuInventory {
  private final boolean canIntroduceItems;
  private final BukkitComponent root;

  public MenuInventoryImpl(
    final @NotNull BukkitComponent root,
    final @NotNull String title,
    final int size,
    final boolean canIntroduceItems
  ) {
    super(null, size, MiniMessage.miniMessage().deserialize(title));
    this.canIntroduceItems = canIntroduceItems;
    this.root = root;

    // Render the components to the inventory
    for (final Component<?> component : TreeHelper.flatten(root)) {
      if (!(component instanceof BukkitComponent bukkitComponent)) {
        throw new IllegalArgumentException("Component must be a BukkitComponent");
      }
      for (final @NotNull MenuItem menuItem : bukkitComponent.render()) {
        this.setItem(menuItem.slot(), menuItem.item());
      }
    }
  }

  @Override
  public boolean canIntroduceItems() {
    return this.canIntroduceItems;
  }

  @Override
  public @Nullable BukkitComponent findBySlot(final int slot) {
    for (final BukkitComponent bukkitComponent : TreeHelper.flatten(this.root, BukkitComponent.class)) {
      for (final @NotNull MenuItem menuItem : bukkitComponent.render()) {
        if (menuItem.slot() == slot) {
          return bukkitComponent;
        }
      }
    }
    return null;
  }

  @Override
  public void reconcile(final @NotNull BukkitComponent after) {
    final BukkitComponent before = TreeHelper.flatten(this.root, BukkitComponent.class)
      .stream()
      .filter(component -> component.id().equals(after.id()))
      .findFirst()
      .orElse(null);
    if (before == null) {
      return;
    }
    final List<BukkitComponent> diff = TreeHelper.diff(before, after, BukkitComponent.class);
    if (diff.isEmpty()) {
      return;
    }
    for (final BukkitComponent component : diff) {
      for (final MenuItem menuItem : component.render()) {
        this.setItem(menuItem.slot(), menuItem.item());
      }
    }
  }
}
