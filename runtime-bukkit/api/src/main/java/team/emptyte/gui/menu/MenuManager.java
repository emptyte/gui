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
package team.emptyte.gui.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.BukkitComponent;
import team.emptyte.gui.adapt.AdaptionModule;
import team.emptyte.gui.adapt.AdaptionModuloFactory;
import team.emptyte.gui.annotated.Application;
import team.emptyte.gui.event.listener.InventoryClickListener;

public final class MenuManager {
  private final AdaptionModule adaptionModule;

  public MenuManager(final @NotNull Plugin plugin) {
    this.adaptionModule = AdaptionModuloFactory.create();

    final PluginManager pluginManager = plugin.getServer().getPluginManager();
    pluginManager.registerEvents(new InventoryClickListener(), plugin);
  }

  private @NotNull Inventory inventoryOf(final @NotNull BukkitComponent root) {
    final Class<? extends BukkitComponent> componentClass = root.getClass();
    final Application application = componentClass.getAnnotation(Application.class);
    if (application == null) {
      throw new IllegalStateException("Component class must be annotated with @Application");
    }
    final String title = application.title();
    final int size = application.size();
    final boolean canIntroduceItems = application.canIntroduceItems();
    return this.adaptionModule.createInventory(root, title, size, canIntroduceItems);
  }

  public void openInventory(final @NotNull Player player, final @NotNull BukkitComponent root) {
    player.openInventory(this.inventoryOf(root));
  }

  public void openInventory(final @NotNull Player player, final @NotNull BukkitComponent root, final @NotNull String title) {
    player.openInventory(this.adaptionModule.createInventory(root, title, 9, false));
  }

  public void openInventory(final @NotNull Player player, final @NotNull BukkitComponent root, final @NotNull String title, final int size) {
    player.openInventory(this.adaptionModule.createInventory(root, title, size, false));
  }

  public void openInventory(final @NotNull Player player, final @NotNull BukkitComponent root, final @NotNull String title, final int size, final boolean canIntroduceItems) {
    player.openInventory(this.adaptionModule.createInventory(root, title, size, canIntroduceItems));
  }
}
