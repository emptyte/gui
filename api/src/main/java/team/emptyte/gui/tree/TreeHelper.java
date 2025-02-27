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
package team.emptyte.gui.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.Component;

public final class TreeHelper {
  private TreeHelper() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  private static void collectFlattened(@NotNull List<Component<?>> list, @NotNull Component<?> component) {
    list.add(component);
    for (final Component<?> child : component.descendents()) {
      collectFlattened(list, child);
    }
  }

  public static @NotNull List<@NotNull Component<?>> flatten(final @NotNull Component<?> root) {
    final List<Component<?>> components = new ArrayList<>();
    collectFlattened(components, root);
    return components;
  }

  public static <T extends Component<?>> @NotNull List<@NotNull T> flatten(final @NotNull Component<?> root, final @NotNull Class<T> type) {
    final List<T> components = new ArrayList<>();
    for (final Component<?> component : flatten(root)) {
      if (type.isInstance(component)) {
        components.add(type.cast(component));
      }
    }
    return components;
  }

  public static @NotNull List<@NotNull Component<?>> diff(final @NotNull Component<?> base, final @NotNull Component<?> dirty) {
    final List<Component<?>> differences = new ArrayList<>();
    final Set<Component<?>> baseSet  = new HashSet<>(flatten(base));
    for (final Component<?> component : flatten(dirty)) {
      if (!baseSet.contains(component)) {
        differences.add(component);
      }
    }
    return differences;
  }

  public static <T extends Component<?>> @NotNull List<@NotNull T> diff(final @NotNull Component<?> before, final @NotNull Component<?> after, final @NotNull Class<T> type) {
    final List<T> diff = new ArrayList<>();
    for (final Component<?> component : diff(before, after)) {
      if (type.isInstance(component)) {
        diff.add(type.cast(component));
      }
    }
    return diff;
  }
}
