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
package team.emptyte.gui.core;

import com.google.common.base.Objects;
import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.emptyte.gui.core.exception.ComponentException;
import team.emptyte.gui.core.exception.ComponentStateException;
import team.emptyte.gui.core.item.ItemClickable;

/**
 * The base class for all components.
 *
 * <p>
 * Components are the building blocks of the GUI.
 * They can be used to create complex GUIs by combining them.
 * </p>
 *
 * @since 0.0.1
 */
public abstract class Component {
  private final UUID id;

  /**
   * If this component is a child of another component,
   * this field will contain the parent component.
   */
  private Component parent = null;
  /**
   * If this component has children components,
   * this field will contain the children components.
   */
  private List<Component> children = null;

  private @Nullable Map<String, Object> states = null;

  public Component() {
    this.id = UUID.randomUUID();
  }

  public @NotNull UUID id() {
    return this.id;
  }

  public @Nullable Component parent() {
    return this.parent;
  }

  public void parent(final @Nullable Component parent) {
    this.parent = parent;
  }

  /**
   * Get the root component.
   *
   * @return the root component
   * @since 0.0.1
   */
  @SuppressWarnings("ConstantConditions")
  public @NotNull Component root() {
    Component current = this;
    if (current.parent == null) {
      return this;
    }
    while (current.parent != null) {
      current = current.parent();
    }
    return current;
  }

  /**
   * Get the children components.
   *
   * @return the children components
   * @since 0.0.1
   */
  public @Nullable List<@NotNull Component> children() {
    if (this.children == null) {
      return Collections.emptyList();
    }
    return this.children;
  }

  /**
   * Add a child component.
   *
   * @param component the child component to add.
   * @since 0.0.1
   */
  public void add(final @NotNull Component component) {
    if (this.children == null) {
      this.children = new ArrayList<>(Collections.singleton(component));
    } else {
      this.children.add(component);
    }
    component.parent(this);
  }

  /**
   * Remove a child component.
   *
   * @param component the child component to remove.
   * @throws ComponentException if there are no children components.
   * @since 0.0.1
   */
  public void remove(final @NotNull Component component) {
    if (this.children == null) {
      throw new ComponentException("No children");
    }
    this.children.remove(component);
  }

  /**
   * Get the descendants components.
   *
   * @return the descendants components
   * @since 0.0.1
   */
  public @NotNull List<Component> descendants() {
    final List<Component> descendants = new ArrayList<>();
    if (this.children != null) {
      for (final Component child : this.children) {
        descendants.add(child);
        descendants.addAll(child.descendants());
      }
    }
    return descendants;
  }

  /**
   * Get the states.
   *
   * @return the states
   * @since 0.0.1
   */
  public @Nullable Map<String, Object> states() {
    return this.states;
  }

  /**
   * Use a state.
   *
   * @param key the key of the state
   * @param value the value of the state
   * @since 0.0.1
   */
  public void useState(final @NotNull String key, final @NotNull Object value) {
    if (this.states == null) {
      this.states = new HashMap<>();
    }
    this.states.put(key, value);
  }

  /**
   * Set a state.
   *
   * @param key the key of the state
   * @param value the value of the state
   * @throws ComponentStateException if there is no initial state
   * @throws ComponentStateException if there is no existing state
   * @since 0.0.1
   */
  public void setState(final @NotNull String key, final @NotNull Object value) {
    if (this.states == null) {
      throw new ComponentStateException("No initial state");
    }
    if (!this.states.containsKey(key)) {
      throw new ComponentStateException("No existing state");
    }

    this.states.put(key, value);
  }

  /**
   * Get a state.
   *
   * @param key the key of the state
   * @return the state
   * @throws ComponentStateException if there is no initial state
   * @since 0.0.1
   */
  public @Nullable Object state(final @NotNull String key) {
    if (this.states == null) {
      throw new ComponentStateException("No initial state");
    }

    return this.states.get(key);
  }

  /**
   * Render the component.
   *
   * @return the rendered components
   * @since 0.0.1
   */
  public abstract @NotNull List<@NotNull ItemClickable> render();

  @Override
  public int hashCode() {
    return Objects.hashCode(this.id, this.states);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }
    final Component other = (Component) obj;
    return Objects.equal(this.id, other.id);
  }

  @Override
  public @NotNull String toString() {
    return "Component{" +
      "id='" + this.id + '\'' +
      ", states=" + this.states +
      '}';
  }
}
