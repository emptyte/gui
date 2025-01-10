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
package team.emptyte.gui.event.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.BukkitComponent;
import team.emptyte.gui.adapt.menu.MenuInventory;
import team.emptyte.gui.menu.item.MenuItem;
import team.emptyte.gui.menu.item.action.MenuItemAction;

public final class InventoryClickListener implements Listener {

  @EventHandler
  public void onMenuClick(final @NotNull InventoryClickEvent event) {
    final int clickedSlot = event.getSlot();
    if (clickedSlot < 0) {
      return;
    }
    final var inventory = event.getInventory();
    if (inventory instanceof MenuInventory menuInventory) {
      final boolean canIntroduceItems = menuInventory.canIntroduceItems();
      if (event.getRawSlot() != clickedSlot && !canIntroduceItems) {
        event.setCancelled(true);
        return;
      }
      final BukkitComponent bukkitComponent = menuInventory.findBySlot(clickedSlot);
      if (bukkitComponent == null) {
        return;
      }
      MenuItem menuItem = null;
      for (final MenuItem item : bukkitComponent.render()) {
        final int slot = item.slot();
        if (slot == clickedSlot) {
          menuItem = item;
          break;
        }
      }
      if (menuItem == null) {
        return;
      }
      final MenuItemAction action = menuItem.action();
      if (action.execute(event)) {
        event.setCancelled(true);
      } else {
        event.setCancelled(!canIntroduceItems);
      }

      // Reconcile the slot after the action has been executed
      menuInventory.reconcile(bukkitComponent);
    }
  }
}
