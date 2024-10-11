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
package team.emptyte.gui.api.menu;

import java.util.function.Predicate;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.emptyte.gui.core.Component;

/**
 * Represents a menu inventory that can be used in the GUI.
 *
 * @since 0.0.1
 */
public final class MenuInventory {
  private final Component root;
  private final String title;
  private final int size;
  private final boolean canIntroduceItems;
  private final Predicate<Inventory> openAction;
  private final Predicate<Inventory> closeAction;

  /**
   * Constructs a new menu inventory.
   *
   * @param title             the title
   * @param size              the size
   * @param root              the root component
   * @param canIntroduceItems if the inventory can introduce items
   * @param openAction        the open action
   * @param closeAction       the close action
   * @since 0.0.1
   */
  private MenuInventory(
    final @Nullable String title,
    final int size,
    final @NotNull Component root,
    final boolean canIntroduceItems,
    final @Nullable Predicate<Inventory> openAction,
    final @Nullable Predicate<Inventory> closeAction
  ) {
    this.title = title;
    this.size = size;
    this.root = root;
    this.canIntroduceItems = canIntroduceItems;
    this.openAction = openAction;
    this.closeAction = closeAction;
  }

  /**
   * Create a new builder. The root component is required.
   *
   * @param root the root component
   * @return the {@link Builder} instance
   * @since 0.0.1
   */
  public static @NotNull Builder builder(final @NotNull Component root) {
    return new Builder(root);
  }

  /**
   * Get the root component.
   *
   * @return the root component
   * @see Component
   * @since 0.0.1
   */
  public @NotNull Component root() {
    return this.root;
  }

  /**
   * Get the title.
   *
   * @return the title
   * @since 0.0.1
   */
  public @Nullable String title() {
    return this.title;
  }

  /**
   * Get the size.
   *
   * @return the size
   * @since 0.0.1
   */
  public int size() {
    return this.size;
  }

  /**
   * Check if the inventory can introduce items.
   *
   * @return {@code true} if the inventory can introduce items
   * @since 0.0.1
   */
  public boolean canIntroduceItems() {
    return this.canIntroduceItems;
  }

  /**
   * Get the open action.
   *
   * @return the open action
   * @since 0.0.1
   */
  public @Nullable Predicate<Inventory> openAction() {
    return this.openAction;
  }

  /**
   * Get the close action.
   *
   * @return the close action
   * @since 0.0.1
   */
  public @Nullable Predicate<Inventory> closeAction() {
    return this.closeAction;
  }

  /**
   * A builder for the menu inventory.
   *
   * @since 0.0.1
   */
  public static class Builder {
    private final Component root;

    private String title;
    private int size = 54;

    private boolean canIntroduceItems = false;

    private Predicate<Inventory> openAction;
    private Predicate<Inventory> closeAction;

    /**
     * Constructs a new builder.
     *
     * @param root the root component
     * @since 0.0.1
     */
    public Builder(final @NotNull Component root) {
      this.root = root;
    }

    /**
     * Set the title.
     *
     * @param title the title
     * @return the builder
     * @since 0.0.1
     */
    public @NotNull Builder title(final @NotNull String title) {
      this.title = title;
      return this;
    }

    /**
     * Set the size.
     *
     * @param size the size
     * @return the builder
     * @since 0.0.1
     */
    public @NotNull Builder size(final int size) {
      this.size = size;
      return this;
    }

    /**
     * Set if the inventory can introduce items.
     *
     * @return the builder
     * @since 0.0.1
     */
    public @NotNull Builder canIntroduceItems() {
      this.canIntroduceItems = true;
      return this;
    }

    /**
     * Set the open action.
     *
     * @param openAction the open action
     * @return the builder
     * @since 0.0.1
     */
    public @NotNull Builder openAction(final @NotNull Predicate<Inventory> openAction) {
      this.openAction = openAction;
      return this;
    }

    /**
     * Set the close action.
     *
     * @param closeAction the close action
     * @return the builder
     * @since 0.0.1
     */
    public @NotNull Builder closeAction(final @NotNull Predicate<Inventory> closeAction) {
      this.closeAction = closeAction;
      return this;
    }

    /**
     * Build the menu inventory.
     *
     * @return the menu inventory
     * @since 0.0.1
     */
    public @NotNull MenuInventory build() {
      return new MenuInventory(this.title, this.size, this.root, this.canIntroduceItems, this.openAction, this.closeAction);
    }
  }
}
