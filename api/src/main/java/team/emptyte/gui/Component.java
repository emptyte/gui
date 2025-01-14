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

import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.emptyte.gui.exception.NoSuchStateComponentException;
import team.emptyte.gui.tree.Tree;

public abstract class Component<E> extends Tree implements Cloneable {
  private final String id;

  /**
   * If this parent is null, then this component is a root component.
   */
  protected Component<E> parent = null;

  private Map<String, Object> states = new HashMap<>();

  @SafeVarargs
  protected Component(final @NotNull Component<E>... children) {
    for (final Component<E> child : children) {
      child.parent = this;
      super.add(child);
    }

    if (this.parent != null) {
      this.id = this.getClass().getSimpleName() + "@" + this.parent.getClass().getSimpleName() + "-" + this.depth();
    } else {
      this.id = this.getClass().getSimpleName() + "-0";
    }
  }

  public @NotNull String id() {
    return this.id;
  }

  public @Nullable Component<E> parent() {
    return this.parent;
  }

  public int depth() {
    int depth = 0;
    Component<E> current = this;
    while (current.parent != null) {
      depth++;
      current = current.parent;
    }
    return depth;
  }

  protected void useState(final @NotNull String key, final @Nullable Object value) {
    this.states.put(key, value);
  }

  protected void setState(final @NotNull String key, final @Nullable Object value) {
    if (!this.states.containsKey(key)) {
      throw new NoSuchStateComponentException("State not found");
    }
    this.states.put(key, value);
  }

  protected @Nullable Object state(final @NotNull String key) {
    if (this.states.isEmpty()) {
      throw new NoSuchStateComponentException("No state found");
    }
    return this.states.get(key);
  }

  public abstract @NotNull List<@NotNull E> render();

  @Override
  public @NotNull Component<E> clone() {
    try {
      @SuppressWarnings("unchecked")
      final Component<E> clone = (Component<E>) super.clone();
      if (this.states != null) {
        clone.states = new HashMap<>(this.states);
      }
      final Collection<Component<?>> children = super.children();
      if (!children.isEmpty()) {
        for (Component<?> child : children) {
          clone.add(child.clone());
        }
      }
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new AssertionError("Cloning not supported", e);
    }
  }

  @Override
  public @NotNull String toString() {
    return "Component{" +
      "id='" + this.id + '\'' +
      ", parent=" + this.parent.id() +
      ", depth=" + this.depth() +
      ", states=" + this.states +
      '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.parent, this.depth(), this.states);
  }

  @Override
  public boolean equals(final @Nullable Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }
    final Component<?> component = (Component<?>) obj;
    if (!this.id.equals(component.id)) {
      return false;
    }
    if (this.parent != null && !this.parent.equals(component.parent)) {
      return false;
    }
    if (this.depth() != component.depth()) {
      return false;
    }
    if (this.states.size() != component.states.size()) {
      return false;
    }
    return Objects.equals(this.states, component.states);
  }
}
