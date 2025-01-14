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
package team.emptyte.gui;

import java.util.List;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.adapt.AdaptionModule;
import team.emptyte.gui.adapt.AdaptionModuloFactory;
import team.emptyte.gui.annotated.Application;
import team.emptyte.gui.component.BukkitComponent;
import team.emptyte.gui.event.listener.InventoryClickListener;
import team.emptyte.gui.menu.item.MenuItem;
import team.emptyte.gui.tree.TreeHelper;

public final class ReactMenu {
  private final AdaptionModule adaptionModule;

  public ReactMenu(final @NotNull Plugin plugin) {
    this.adaptionModule = AdaptionModuloFactory.create();

    final PluginManager pluginManager = plugin.getServer().getPluginManager();
    pluginManager.registerEvents(new InventoryClickListener(), plugin);
  }

  private int calculateSize(final @NotNull BukkitComponent root) {
    final List<BukkitComponent> components = TreeHelper.flatten(root, BukkitComponent.class);
    int size = 9;
    for (final @NotNull BukkitComponent component : components) {
      for (final @NotNull MenuItem item : component.render()) {
        size = Math.max(size, item.slot());
      }
    }
    if (size % 9 != 0) {
      size += 9 - (size % 9);
    }
    return Math.min(size, 54);
  }

  public void render(final @NotNull Player player, final @NotNull BukkitComponent root) {
    final Class<?> rootType = root.getClass();
    final Application application = rootType.getAnnotation(Application.class);
    if (application == null) {
      throw new IllegalArgumentException("Root component must be annotated with @Application");
    }
    final String title = PlaceholderAPI.setPlaceholders(player, application.title());
    int size = application.size();
    if (size == -1) {
      size = this.calculateSize(root);
    }
    final boolean allowItemInsertion = application.allowItemInsertion();
    player.openInventory(this.adaptionModule.createInventory(root, title, size, allowItemInsertion));
  }
}
