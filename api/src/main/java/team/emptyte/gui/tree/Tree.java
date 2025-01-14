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

import java.util.*;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.emptyte.gui.Component;

public class Tree implements Iterable<Component<?>> {
  private Component<?>[] children;

  protected Tree(final @NotNull Component<?>... children) {
    this.children = children;
  }

  public @NotNull Collection<@NotNull Component<?>> children() {
    return new ArrayList<>(Arrays.asList(this.children));
  }

  public @NotNull Collection<@NotNull Component<?>> descendents() {
    if (this.children == null || this.children.length == 0) {
      return Collections.emptyList();
    }
    final List<Component<?>> descendants = new ArrayList<>();
    for (final Component<?> child : this.children) {
      descendants.add(child);
      descendants.addAll(child.descendents());
    }
    return descendants;
  }

  public void add(final @NotNull Component<?> child) {
    final Component<?>[] newChildren = new Component[this.children.length + 1];
    System.arraycopy(this.children, 0, newChildren, 0, this.children.length);
    newChildren[this.children.length] = child;
    this.children = newChildren;
  }

  public void addAll(final @NotNull Component<?>... children) {
    for (final Component<?> child : children) {
      this.add(child);
    }
  }

  public void addAll(final @NotNull Collection<@NotNull Component<?>> children) {
    for (final Component<?> child : children) {
      this.add(child);
    }
  }

  public void remove(final @NotNull Component<?> child) {
    final Component<?>[] newChildren = new Component[this.children.length - 1];
    int index = 0;
    for (final Component<?> component : this.children) {
      if (component != child) {
        newChildren[index++] = component;
      }
    }
    this.children = newChildren;
  }

  public void clear() {
    this.children = new Component[0];
  }

  public boolean contains(final @NotNull Component<?> child) {
    for (final Component<?> component : this.children) {
      if (component == child) {
        return true;
      }
    }
    return false;
  }

  public int size() {
    return this.children.length;
  }

  public boolean isEmpty() {
    return this.children.length == 0;
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(this.children);
  }

  @Override
  public boolean equals(final @Nullable Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }
    final Tree tree = (Tree) obj;
    return Arrays.equals(this.children, tree.children);
  }

  @Override
  public @NotNull Iterator<Component<?>> iterator() {
    return new Iterator<>() {
      private int index = 0;

      @Override
      public boolean hasNext() {
        return this.index < Tree.this.children.length;
      }

      @Override
      public @Nullable Component<?> next() {
        return Tree.this.children[this.index++];
      }
    };
  }
}
