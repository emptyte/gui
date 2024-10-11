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
package team.emptyte.gui.api.listener;

import java.util.function.Predicate;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.api.menu.MenuInventory;
import team.emptyte.gui.api.menu.MenuInventoryWrapper;
import team.emptyte.gui.api.util.MenuUtil;
import team.emptyte.gui.core.Component;
import team.emptyte.gui.core.item.ItemClickable;
import team.emptyte.gui.core.item.action.ItemClickableAction;

public final class InventoryClickListener implements Listener {
  @EventHandler
  public void onClick(final @NotNull InventoryClickEvent event) {
    final Inventory inventory = event.getClickedInventory();
    if (inventory == null) {
      return;
    }
    if (MenuUtil.isCustomMenu(inventory)) {
      final int slot = event.getSlot();
      if (slot < 0) {
        return;
      }
      final MenuInventoryWrapper menuInventoryWrapper = MenuUtil.getAsWrapper(inventory);
      final MenuInventory menuInventory = menuInventoryWrapper.menuInventory();
      Component component = menuInventory.root();
      ItemClickable itemClickable = null;
      for (final ItemClickable item : menuInventory.root().render()) {
        if (item.slot() == slot) {
          itemClickable = item;
          break;
        }
      }
      for (final Component c : menuInventory.root().descendants()) {
        for (final ItemClickable item : c.render()) {
          if (item.slot() == slot) {
            component = c;
            itemClickable = item;
            break;
          }
        }
      }
      if (itemClickable == null) {
        event.setCancelled(true);
        return;
      }
      if (event.getRawSlot() != slot && !menuInventory.canIntroduceItems()) {
        event.setCancelled(true);
        return;
      }
      final ItemClickableAction itemClickableAction = itemClickable.action();
      final Predicate<InventoryClickEvent> clickEventPredicate = itemClickableAction.action(event.getClick());
      if (clickEventPredicate == null) {
        event.setCancelled(!menuInventory.canIntroduceItems());
      } else {
        event.setCancelled(clickEventPredicate.test(event));
      }

      this.render(inventory, component);
    }
  }

  private void render(final @NotNull Inventory inventory, final @NotNull Component component) {
    for (final ItemClickable item : component.render()) {
      inventory.setItem(item.slot(), item.itemStack());
    }
  }
}
