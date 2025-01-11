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
package team.emptyte.gui.adapt.item.builder;

import java.util.List;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.util.ServerVersion;

public interface ItemBuilder {
  static @NotNull ItemBuilder builder(final @NotNull Material material) {
    return ItemBuilder.builder(material, 1);
  }

  static @NotNull ItemBuilder builder(final @NotNull Material material, final int amount) {
    final String className = "team.emptyte.gui.item.builder.adapt.v" + ServerVersion.CURRENT + ".DefaultItemBuilder";
    try {
      final Class<?> clazz = Class.forName(className);
      final Object module = clazz.getConstructor(Material.class, int.class)
        .newInstance(material, amount);
      if (!(module instanceof ItemBuilder)) {
        throw new IllegalStateException("Invalid item builder: '"
          + className + "'. It doesn't implement " + ItemBuilder.class);
      }
      return (ItemBuilder) module;
    } catch (final ClassNotFoundException e) {
      throw new IllegalStateException("Item builder not found: '" + className + '.');
    } catch (final ReflectiveOperationException e) {
      throw new IllegalStateException("Failed to instantiate item builder", e);
    }
  }

  @NotNull ItemBuilder name(final @NotNull String name);

  @NotNull ItemBuilder lore(final @NotNull List<String> lore);

  default @NotNull ItemBuilder lore(final @NotNull String... lore) {
    return this.lore(List.of(lore));
  }

  @NotNull ItemBuilder glow(final boolean glow);

  @NotNull ItemBuilder disableEnchantGlow(final boolean disableEnchantGlow);

  @NotNull ItemBuilder enchant(final @NotNull Enchantment enchantment, final int level);

  @NotNull ItemBuilder enchantments(final @NotNull Map<Enchantment, Integer> enchantments);

  @NotNull ItemBuilder customModelData(final int customModelData);

  @NotNull ItemStack build();
}
