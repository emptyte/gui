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
package team.emptyte.gui.menu.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.menu.item.action.MenuItemAction;

public final class MenuItemBuilder {
  private final int slot;
  private ItemStack itemStack = ItemStack.of(Material.BEDROCK);
  private MenuItemAction action = (player, clickedSlot, clickType) -> true;

  public MenuItemBuilder(final int slot) {
    this.slot = slot;
  }

  public @NotNull MenuItemBuilder itemStack(final @NotNull ItemStack itemStack) {
    this.itemStack = itemStack;
    return this;
  }

  public MenuItemBuilder action(final MenuItemAction action) {
    this.action = action;
    return this;
  }

  public @NotNull MenuItem build() {
    return new MenuItem(this.slot, this.itemStack, this.action);
  }
}
