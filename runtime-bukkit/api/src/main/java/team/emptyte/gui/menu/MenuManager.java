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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.event.listener.MenuListener;

public final class MenuManager {
  private static final Constructor<?> MENU_INVENTORY_CONSTRUCTOR;

  static {
    try {
      MENU_INVENTORY_CONSTRUCTOR = Class.forName("team.emptyte.gui.menu.adapt.v1_21_1.MenuInventoryImpl")
        .getConstructor(Menu.class);
    } catch (final ReflectiveOperationException e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  public MenuManager(final @NotNull Plugin plugin) {
    final PluginManager pluginManager = plugin.getServer().getPluginManager();
    pluginManager.registerEvents(new MenuListener(), plugin);
  }

  public @NotNull Inventory createInventory(final @NotNull Menu menu) {
    try {
      return (Inventory) MENU_INVENTORY_CONSTRUCTOR.newInstance(menu);
    } catch (final InvocationTargetException | InstantiationException | IllegalAccessException e) {
      throw new ExceptionInInitializerError(
        "An error has occurred while creating menu "
          + menu.title());
    }
  }
}
