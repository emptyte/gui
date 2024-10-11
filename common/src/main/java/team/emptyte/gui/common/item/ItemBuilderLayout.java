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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * A layout for the {@link ItemBuilder}.
 *
 * @param <T> the type of the builder
 * @see ItemBuilder
 * @since 0.0.1
 */
abstract class ItemBuilderLayout<T extends ItemBuilder> implements ItemBuilder {
  protected final Material material;
  private final int amount;

  private String name;
  private List<String> lore;
  private Map<Enchantment, Integer> enchantments;
  private List<ItemFlag> flags;
  private boolean unbreakable;

  protected ItemBuilderLayout(final @NotNull Material material, final int amount) {
    this.material = material;
    this.amount = amount;
    this.lore = new ArrayList<>();
    this.enchantments = new HashMap<>();
    this.flags = new ArrayList<>();
  }

  @Override
  public @NotNull ItemBuilder name(final @NotNull String name) {
    this.name = name;
    return back();
  }

  @Override
  public @NotNull ItemBuilder lore(final @NotNull List<String> lore) {
    this.lore = lore;
    return back();
  }

  @Override
  public @NotNull ItemBuilder lore(final @NotNull String @NotNull ... lines) {
    this.lore = Arrays.asList(lines);
    return back();
  }

  @Override
  public @NotNull ItemBuilder enchantments(final @NotNull Map<Enchantment, Integer> enchantments) {
    this.enchantments = enchantments;
    return back();
  }

  @Override
  public @NotNull ItemBuilder enchant(final @NotNull Enchantment enchantment, final int level) {
    this.enchantments.put(enchantment, level);
    return back();
  }

  @Override
  public @NotNull ItemBuilder flags(final @NotNull List<ItemFlag> flags) {
    this.flags = flags;
    return back();
  }

  @Override
  public @NotNull ItemBuilder flag(final @NotNull ItemFlag... flags) {
    this.flags.addAll(Arrays.asList(flags));
    return back();
  }

  @Override
  public @NotNull ItemBuilder grow() {
    this.enchantments.put(Enchantment.UNBREAKING, 3);
    this.flags.add(ItemFlag.HIDE_ENCHANTS);
    return back();
  }

  @Override
  public @NotNull ItemBuilder unbreakable(final boolean unbreakable) {
    this.unbreakable = unbreakable;
    return back();
  }

  @Override
  public @NotNull ItemStack build() {
    final ItemStack itemStack = new ItemStack(this.material, this.amount);

    itemStack.editMeta(itemMeta -> {
      if (this.name != null) {
        itemMeta.displayName(MiniMessage.miniMessage().deserialize(this.name));
      }

      if (!this.lore.isEmpty()) {
        itemMeta.lore(this.lore.stream().map(line -> MiniMessage.miniMessage().deserialize(line)).toList());
      }

      if (!this.enchantments.isEmpty()) {
        for (final Map.Entry<Enchantment, Integer> entry : this.enchantments.entrySet()) {
          itemMeta.addEnchant(entry.getKey(), entry.getValue(), true);
        }
      }

      if (!this.flags.isEmpty()) {
        for (final ItemFlag itemFlag : this.flags) {
          itemMeta.addItemFlags(itemFlag);
        }
      }

      itemMeta.setUnbreakable(this.unbreakable);
    });

    return itemStack;
  }

  /**
   * Returns the instance of the builder.
   *
   * @return the instance of the builder
   * @since 0.0.1
   */
  protected abstract @NotNull T back();
}
