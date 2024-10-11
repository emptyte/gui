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
package team.emptyte.gui.common.item;

import java.util.List;
import java.util.Map;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.common.item.util.DyeItemUtils;

/**
 * A builder for creating {@link ItemStack}s.
 *
 * @since 1.0
 */
public interface ItemBuilder {
  /**
   * Sets the name of the item.
   *
   * @param name the name of the item
   * @return the {@link ItemBuilder} instance
   * @since 0.0.1
   */
  @NotNull ItemBuilder name(final @NotNull String name);

  /**
   * Sets the lore of the item.
   *
   * @param lore the lore of the item
   * @return the {@link ItemBuilder} instance
   * @since 0.0.1
   */
  @NotNull ItemBuilder lore(final @NotNull List<String> lore);

  /**
   * Sets the lore of the item.
   *
   * @param lines the lines of the lore
   * @return the {@link ItemBuilder} instance
   * @since 0.0.1
   */
  @NotNull ItemBuilder lore(final @NotNull String... lines);

  /**
   * Sets the enchantments of the item.
   *
   * @param enchantments the enchantments of the item
   * @return the {@link ItemBuilder} instance
   * @since 0.0.1
   */
  @NotNull ItemBuilder enchantments(final @NotNull Map<Enchantment, Integer> enchantments);

  /**
   * Adds an enchantment to the item.
   *
   * @param enchantment the enchantment to add
   * @param level       the level of the enchantment
   * @return the {@link ItemBuilder} instance
   * @since 0.0.1
   */
  @NotNull ItemBuilder enchant(final @NotNull Enchantment enchantment, final int level);

  /**
   * Sets the flags of the item.
   *
   * @param flags the flags of the item
   * @return the {@link ItemBuilder} instance
   * @since 0.0.1
   */
  @NotNull ItemBuilder flags(final @NotNull List<ItemFlag> flags);

  /**
   * Adds flags to the item.
   *
   * @param flags the flags to add
   * @return the {@link ItemBuilder} instance
   * @since 0.0.1
   */
  @NotNull ItemBuilder flag(final @NotNull ItemFlag... flags);

  /**
   * Adds the {@link Enchantment#UNBREAKING} enchantment with a level of 3 and the {@link ItemFlag#HIDE_ENCHANTS} flag.
   *
   * @return the {@link ItemBuilder} instance
   * @since 0.0.1
   */
  @NotNull ItemBuilder grow();

  /**
   * Sets whether the item is unbreakable.
   *
   * @param unbreakable whether the item is unbreakable
   * @return the {@link ItemBuilder} instance
   * @since 0.0.1
   */
  @NotNull ItemBuilder unbreakable(final boolean unbreakable);

  /**
   * Builds the {@link ItemStack}.
   *
   * @return the built {@link ItemStack}
   * @since 0.0.1
   */
  @NotNull ItemStack build();

  /**
   * Creates a new {@link ItemBuilder} instance with the specified {@link Material}.
   *
   * @param material the {@link Material} of the item
   * @return the new {@link ItemBuilder} instance
   * @since 0.0.1
   */
  static @NotNull ItemBuilder builder(final @NotNull Material material) {
    return ItemBuilder.builder(material, 1);
  }

  /**
   * Creates a new {@link ItemBuilder} instance with the specified {@link Material} and amount.
   *
   * @param material the {@link Material} of the item
   * @param amount the amount of the item
   * @return the new {@link ItemBuilder} instance
   * @since 0.0.1
   */
  static @NotNull ItemBuilder builder(final @NotNull Material material, final int amount) {
    return new DefaultItemBuilder(material, amount);
  }

  static @NotNull ItemBuilder dyeBuilder(final @NotNull String materialKey, final @NotNull DyeColor dyeColor) {
    return ItemBuilder.dyeBuilder(materialKey, dyeColor, 1);
  }

  static @NotNull ItemBuilder dyeBuilder(final @NotNull String materialKey, final @NotNull DyeColor dyeColor, final int amount) {
    return DyeItemUtils.createBuilder(materialKey, dyeColor, amount);
  }
}
