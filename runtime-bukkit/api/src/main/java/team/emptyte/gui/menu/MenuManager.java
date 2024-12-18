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
package team.emptyte.gui.menu;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.adapt.AdaptionModule;
import team.emptyte.gui.adapt.AdaptionModuloFactory;
import team.emptyte.gui.event.listener.InventoryClickListener;

public final class MenuManager {
  private final AdaptionModule adaptionModule;

  public MenuManager(final @NotNull Plugin plugin) {
    this.adaptionModule = AdaptionModuloFactory.create();

    final PluginManager pluginManager = plugin.getServer().getPluginManager();
    pluginManager.registerEvents(new InventoryClickListener(), plugin);
  }

  /**
   * Open the inventory for the player with the menu.
   *
   * @param player the player to open the inventory for
   * @param menu   the menu to open
   * @since 0.2.0
   */
  public void openInventory(final @NotNull Player player, final @NotNull Menu menu) {
    player.openInventory(this.adaptionModule.createInventory(menu));
  }
}
