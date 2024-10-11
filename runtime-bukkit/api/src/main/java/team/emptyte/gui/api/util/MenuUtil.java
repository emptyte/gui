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
package team.emptyte.gui.api.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.api.menu.MenuInventory;
import team.emptyte.gui.api.menu.MenuInventoryWrapper;

/**
 * Utility class for menus.
 *
 * @since 0.0.1
 */
public final class MenuUtil {
  /**
   * The constructor of the menu inventory wrapper.
   */
  private static final Constructor<?> WRAPPER_CONSTRUCTOR;

  static {
    try {
      WRAPPER_CONSTRUCTOR = Class.forName(
        "team.emptyte.gui.adapt.v1_21_1.menu.MenuInventoryWrapperImpl"
      ).getConstructor(InventoryHolder.class, MenuInventory.class);
    } catch (final ClassNotFoundException | NoSuchMethodException e) {
      throw new ExceptionInInitializerError("Your server version isn't supported for ungui.");
    }
  }

  private MenuUtil() {
    // This class cannot be instantiated.
    throw new UnsupportedOperationException("This class cannot be instantiated");
  }

  /**
   * Parse the menu inventory to the raw inventory.
   *
   * @param menuInventory the menu inventory
   * @return the {@link Inventory} instance
   * @since 0.0.1
   */
  public static @NotNull Inventory parseInventory(final @NotNull MenuInventory menuInventory) {
    try {
      final MenuInventoryWrapper wrapper
        = (MenuInventoryWrapper) WRAPPER_CONSTRUCTOR.newInstance(
        null, menuInventory
      );
      return wrapper.raw();
    } catch (final InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new ExceptionInInitializerError(
        "An error has occurred while creating menu "
          + menuInventory.title());
    }
  }

  /**
   * Check if the inventory is a custom menu.
   *
   * @param inventory the inventory
   * @return {@code true} if the inventory is a custom menu
   * @since 0.0.1
   */
  public static boolean isCustomMenu(final @NotNull Inventory inventory) {
    final InventoryHolder holder = inventory.getHolder();

    return holder instanceof MenuInventoryWrapper
      || inventory instanceof MenuInventoryWrapper;
  }

  /**
   * Get the inventory as a wrapper.
   *
   * @param inventory the inventory
   * @return the {@link MenuInventoryWrapper} instance
   * @since 0.0.1
   */
  public static @NotNull MenuInventoryWrapper getAsWrapper(final @NotNull Inventory inventory) {
    InventoryHolder holder = inventory.getHolder();

    return holder == null ?
      (MenuInventoryWrapper) inventory :
      (MenuInventoryWrapper) holder;
  }
}
