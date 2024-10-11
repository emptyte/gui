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
package team.emptyte.gui.api.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.api.util.MenuUtil;
import team.emptyte.gui.core.Component;
import team.emptyte.gui.core.item.ItemClickable;

/**
 * This class manages the menu inventory.
 *
 * @since 0.0.1
 */
public final class MenuInventoryManager {
  /**
   * Create a new instance of the {@link Inventory}.
   *
   * @param menuInventory the {@link MenuInventory}
   * @return the {@link Inventory} instance
   * @since 0.0.1
   */
  private @NotNull Inventory createInventory(final @NotNull MenuInventory menuInventory) {
    final Inventory inventory = MenuUtil.parseInventory(menuInventory);
    final Component root = menuInventory.root();

    for (final ItemClickable itemClickable : root.render()) {
      inventory.setItem(itemClickable.slot(), itemClickable.itemStack());
    }

    for (final Component component : root.descendants()) {
      for (final ItemClickable itemClickable : component.render()) {
        inventory.setItem(itemClickable.slot(), itemClickable.itemStack());
      }
    }

    return inventory;
  }

  /**
   * Open the inventory for the player.
   * If the player already has an inventory open, it will be closed.
   * The inventory will be opened for the player.
   *
   * @param player        the {@link Player}
   * @param menuInventory the {@link MenuInventory} to open for the player
   * @since 0.0.1
   */
  public void openInventory(final @NotNull Player player, final @NotNull MenuInventory menuInventory) {
    final Inventory inventory = this.createInventory(menuInventory);
    if (player.getInventory() != inventory) {
      player.closeInventory();
    }

    player.openInventory(inventory);
  }
}
