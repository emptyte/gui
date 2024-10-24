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
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.BukkitComponent;
import team.emptyte.gui.menu.Menu;
import team.emptyte.gui.menu.adapt.MenuInventory;

public final class MenuListener implements Listener {
  @EventHandler
  public void onMenuClick(final @NotNull InventoryClickEvent event) {
    final int clickedSlot = event.getSlot();
    if (clickedSlot < 0) {
      return;
    }
    final var inventory = event.getInventory();
    if (inventory instanceof MenuInventory menuInventory) {
      final Menu menu = menuInventory.menu();
      final BukkitComponent bukkitComponent = menu.get(clickedSlot);
      if (bukkitComponent == null) {
        return;
      }
      for (final var item : bukkitComponent.render()) {
        if (item.slot() != clickedSlot) {
          continue;
        }
        if (item.action().execute(event)) {
          event.setCancelled(true);
        }
        break;
      }
      // If the item is not found, cancel the event
      final BukkitComponent modifiedComponent = menu.findModified(bukkitComponent);
      if (modifiedComponent == null) {
        return;
      }
      for (final var item : modifiedComponent.render()) {
        inventory.setItem(item.slot(), item.item());
      }
    }
  }

  @EventHandler
  public void onMenuOpen(final @NotNull InventoryOpenEvent event) {
    final var inventory = event.getInventory();
    if (inventory instanceof MenuInventory menuInventory) {
      final Menu menu = menuInventory.menu();
      final var openAction = menu.openAction();
      if (openAction != null) {
        openAction.test(inventory);
      }
    }
  }

  @EventHandler
  public void onMenuClose(final @NotNull InventoryCloseEvent event) {
    final var inventory = event.getInventory();
    if (inventory instanceof MenuInventory menuInventory) {
      final Menu menu = menuInventory.menu();
      final var closeAction = menu.closeAction();
      if (closeAction != null) {
        closeAction.test(inventory);
      }
    }
  }
}
