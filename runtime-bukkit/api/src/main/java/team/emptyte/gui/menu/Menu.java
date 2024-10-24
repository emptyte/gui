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

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Predicate;

import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.emptyte.gui.BukkitComponent;
import team.emptyte.gui.Component;
import team.emptyte.gui.menu.item.MenuItem;

public record Menu(
  @NotNull String title,
  int size,
  @NotNull BukkitComponent component,
  @Nullable Predicate<Inventory> openAction,
  @Nullable Predicate<Inventory> closeAction,
  boolean canIntroduceItems
) {
  public Menu {
    if (size % 9 != 0) {
      throw new IllegalArgumentException("Size must be a multiple of 9");
    }

    if (size < 9 || size > 54) {
      throw new IllegalArgumentException("Size must be between 9 and 54");
    }
  }

  /**
   * This method performs a breadth-first search
   * to find the modified component.
   *
   * @param component the component to find
   * @return the modified component or null if not found
   */
  public @Nullable BukkitComponent findModified(final @NotNull BukkitComponent component) {
    final Collection<BukkitComponent> components = this.components();
    if (components.isEmpty()) {
      return null;
    }
    for (final BukkitComponent current : components) {
      if (current.equals(component)) {
        return current;
      }
    }
    return null;
  }

  /**
   * This method extracts all items from the component.
   *
   * @return a collection of menu items
   */
  public @NotNull Collection<@NotNull MenuItem> items() {
    final Collection<MenuItem> items = new LinkedList<>();
    final Collection<BukkitComponent> components = this.components();
    for (final BukkitComponent component : components) {
      items.addAll(component.render());
    }
    return items;
  }

  public @Nullable BukkitComponent get(final int slot) {
    for (final BukkitComponent component : this.components()) {
      for (final MenuItem item : component.render()) {
        if (item.slot() == slot) {
          return component;
        }
      }
    }
    return null;
  }

  private @NotNull Collection<@NotNull BukkitComponent> components() {
    final Collection<BukkitComponent> components = new LinkedList<>();
    final Queue<BukkitComponent> queue = new LinkedList<>();
    queue.add(this.component);

    while (!queue.isEmpty()) {
      final BukkitComponent current = queue.poll();
      components.add(current);
      for (final Component<?> child : current.children()) {
        if (child instanceof BukkitComponent bukkitChild) {
          queue.add(bukkitChild);
        }
      }
    }

    return components;
  }
}
