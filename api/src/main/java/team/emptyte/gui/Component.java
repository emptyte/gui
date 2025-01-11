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

public abstract class Component<E> extends Tree {
  private final String id;

  /**
   * If this parent is null, then this component is a root component.
   */
  protected Component<E> parent = null;

  private Map<String, Object> states;

  @SafeVarargs
  protected Component(final @NotNull Component<E>... children) {
    for (final Component<E> child : children) {
      child.parent = this;
      super.add(child);
    }

    this.states = null;

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
    if (this.states == null) {
      this.states = new HashMap<>();
    }
    this.states.put(key, value);
  }

  protected void setState(final @NotNull String key, final @Nullable Object value) {
    if (this.states == null || !this.states.containsKey(key)) {
      throw new NoSuchStateComponentException("State not found");
    }
    this.states.put(key, value);
  }

  protected @Nullable Object state(final @NotNull String key) {
    if (this.states == null || this.states.isEmpty()) {
      throw new NoSuchStateComponentException("No state found");
    }
    return this.states.get(key);
  }

  public abstract @NotNull List<@NotNull E> render();

  @Override
  public @NotNull String toString() {
    return "Component{" +
      "parent=" + this.parent +
      "states=" + this.states +
      '}';
  }

  @Override
  public int hashCode() {
    return this.states.hashCode();
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
    return Objects.equals(this.states, component.states);
  }
}
