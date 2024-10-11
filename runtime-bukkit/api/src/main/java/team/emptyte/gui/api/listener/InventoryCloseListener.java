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
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.api.menu.MenuInventory;
import team.emptyte.gui.api.menu.MenuInventoryWrapper;
import team.emptyte.gui.api.util.MenuUtil;

public final class InventoryCloseListener implements Listener {
  private final Plugin plugin;

  public InventoryCloseListener(final @NotNull Plugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onClose(final @NotNull InventoryCloseEvent event) {
    final Inventory inventory = event.getInventory();

    if (MenuUtil.isCustomMenu(inventory)) {
      final MenuInventoryWrapper menuInventoryWrapper = MenuUtil.getAsWrapper(inventory);
      final MenuInventory menuInventory = menuInventoryWrapper.menuInventory();
      final Predicate<Inventory> closeAction = menuInventory.closeAction();

      if (closeAction != null) {
        if (!closeAction.test(inventory)) {
          Bukkit.getScheduler()
            .runTaskLater(this.plugin, () -> event.getPlayer().openInventory(inventory), 1L);
        }
      }
    }
  }
}
