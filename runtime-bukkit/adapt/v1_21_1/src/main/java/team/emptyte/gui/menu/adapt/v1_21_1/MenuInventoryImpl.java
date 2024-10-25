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
package team.emptyte.gui.menu.adapt.v1_21_1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.craftbukkit.inventory.CraftInventoryCustom;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.emptyte.gui.BukkitComponent;
import team.emptyte.gui.Component;
import team.emptyte.gui.adapt.menu.MenuInventory;
import team.emptyte.gui.menu.Menu;
import team.emptyte.gui.menu.item.MenuItem;

public class MenuInventoryImpl extends CraftInventoryCustom implements MenuInventory {
  private final Menu menu;

  public MenuInventoryImpl(final @NotNull Menu menu) {
    super(null, menu.size(), MiniMessage.miniMessage().deserialize(menu.title()));
    this.menu = menu;

    // Render the components to the inventory
    for (final @NotNull BukkitComponent bukkitComponent : this.components()) {
      for (final @NotNull MenuItem menuItem : bukkitComponent.render()) {
        this.setItem(menuItem.slot(), menuItem.item());
      }
    }
  }

  /**
   * Gets the components of the menu.
   *
   * @return the {@link List} of {@link BukkitComponent}
   * @since 0.2.0
   */
  private @NotNull List<@NotNull BukkitComponent> components() {
    final List<BukkitComponent> bukkitComponents = new ArrayList<>();
    for (final Component<?> component : menu.component().descendents()) {
      if (component instanceof BukkitComponent bukkitComponent) {
        bukkitComponents.add(bukkitComponent);
      }
    }
    return bukkitComponents;
  }

  @Override
  public @NotNull Menu menu() {
    return this.menu;
  }

  @Override
  public @Nullable BukkitComponent findBySlot(final int slot) {
    for (final @NotNull BukkitComponent bukkitComponent : this.components()) {
      for (final @NotNull MenuItem menuItem : bukkitComponent.render()) {
        if (menuItem.slot() == slot) {
          return bukkitComponent;
        }
      }
    }

    return null;
  }

  @Override
  public @NotNull Collection<@NotNull BukkitComponent> compareTo(final @NotNull BukkitComponent bukkitComponent) {
    final List<BukkitComponent> components = new ArrayList<>();
    for (final @NotNull BukkitComponent component : this.components()) {
      if (component.equals(bukkitComponent)) {
        components.add(component);
      }
    }

    return components;
  }
}
