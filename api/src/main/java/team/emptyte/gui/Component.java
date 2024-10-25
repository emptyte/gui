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
package team.emptyte.gui;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.emptyte.gui.exception.NoSuchStateComponentException;

import java.util.*;

public abstract class Component<E> extends Tree {
  /**
   * If this parent is null, then this component is a root component.
   */
  protected Component<E> parent = null;

  private Map<String, Object> states;

  @SafeVarargs
  public Component(final @NotNull Component<E>... children) {
    for (final Component<E> child : children) {
      child.parent = this;
      super.add(child);
    }

    this.states = null;
  }

  public void useState(final @NotNull String key, final @Nullable Object value) {
    if (this.states == null) {
      this.states = new HashMap<>();
    }
    this.states.put(key, value);
  }

  public void setState(final @NotNull String key, final @Nullable Object value) {
    if (this.states == null || !this.states.containsKey(key)) {
      throw new NoSuchStateComponentException("State not found");
    }
    this.states.put(key, value);
  }

  public @Nullable Object state(final @NotNull String key) {
    if (this.states == null || this.states.isEmpty()) {
      throw new NoSuchStateComponentException("No state found");
    }
    return this.states.get(key);
  }

  public @NotNull Collection<@NotNull Component<?>> descendents() {
    if (this.children().isEmpty()) {
      return Collections.emptyList();
    }

    final Collection<Component<?>> components = new LinkedList<>();
    final Queue<Component<?>> queue = new LinkedList<>();
    queue.add(this);

    while (!queue.isEmpty()) {
      final Component<?> current = queue.poll();
      components.add(current);
      queue.addAll(current.children());
    }

    return components;
  }

  public abstract @NotNull List<@NotNull E> render();

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
