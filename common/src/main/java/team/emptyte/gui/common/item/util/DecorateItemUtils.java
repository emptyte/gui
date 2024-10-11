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
package team.emptyte.gui.common.item.util;

import org.bukkit.DyeColor;
import org.bukkit.inventory.ItemStack;
import team.emptyte.gui.common.item.ItemBuilder;

/**
 * Utility class for decorating items.
 *
 * @since 0.0.1
 */
public final class DecorateItemUtils {
  private DecorateItemUtils() {
    throw new UnsupportedOperationException();
  }

  /**
   * Creates a new stained pane builder.
   *
   * @param dyeColor the dye color
   * @return the stained pane builder
   * @since 0.0.1
   */
  public static ItemBuilder stainedPaneBuilder(DyeColor dyeColor) {
    return ItemBuilder.dyeBuilder("STAINED_GLASS_PANE", dyeColor)
      .name(" ");
  }

  /**
   * Creates a new stained pane.
   *
   * @param dyeColor the dye color
   * @return the stained pane
   * @since 0.0.1
   */
  public static ItemStack stainedPane(DyeColor dyeColor) {
    return stainedPaneBuilder(dyeColor).build();
  }

}
