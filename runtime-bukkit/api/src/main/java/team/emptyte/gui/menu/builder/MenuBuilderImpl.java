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
package team.emptyte.gui.menu.builder;

import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.BukkitComponent;
import team.emptyte.gui.Component;
import team.emptyte.gui.menu.Menu;
import team.emptyte.gui.menu.item.MenuItem;

public final class MenuBuilderImpl implements MenuBuilder {
  private String title = "Menu";
  private int size;
  private boolean canIntroduceItems = false;
  private final BukkitComponent component;

  public MenuBuilderImpl(final @NotNull BukkitComponent component) {
    this.component = component;

    int size = 9;
    for (final @NotNull Component<?> child : component.descendents()) {
      if (!(child instanceof  BukkitComponent bukkitComponent)) {
        continue;
      }

      for (final @NotNull MenuItem item : bukkitComponent.render()) {
        size = Math.max(size, item.slot());
      }
    }
    if (size % 9 != 0) {
      size += 9 - (size % 9);
    }
    this.size = Math.min(size, 54);
  }

  @Override
  public @NotNull MenuBuilder title(final @NotNull String title) {
    this.title = title;
    return this;
  }

  @Override
  public @NotNull MenuBuilder size(final int size) {
    this.size = size;
    return this;
  }

  @Override
  public @NotNull MenuBuilder canIntroduceItems() {
    this.canIntroduceItems = true;
    return this;
  }

  @Override
  public @NotNull Menu build() {
    return new Menu(this.title, this.size, this.canIntroduceItems, this.component);
  }
}
