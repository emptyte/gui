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

import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.BukkitComponent;
import team.emptyte.gui.menu.builder.MenuBuilder;
import team.emptyte.gui.menu.builder.MenuBuilderImpl;

public record Menu(
  @NotNull String title,
  int size,
  boolean canIntroduceItems,
  @NotNull BukkitComponent component
) {
  public Menu {
    if (title.length() > 32) {
      throw new IllegalArgumentException("Title must be at most 32 characters");
    }

    if (size % 9 != 0) {
      throw new IllegalArgumentException("Size must be a multiple of 9");
    }

    if (size < 9 || size > 54) {
      throw new IllegalArgumentException("Size must be between 9 and 54");
    }
  }

  public static @NotNull MenuBuilder builder(final @NotNull BukkitComponent component) {
    return new MenuBuilderImpl(component);
  }
}
