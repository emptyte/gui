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
package team.emptyte.gui.adapt.item.builder;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractItemBuilder implements ItemBuilder {
  protected final Material material;
  protected final int amount;

  protected String name;
  protected List<String> lore;
  protected boolean glow;
  protected boolean disableEnchantGlow;
  protected Map<Enchantment, Integer> enchantments;
  protected int customModelData;

  protected AbstractItemBuilder(final @NotNull Material material, final int amount) {
    this.material = material;
    this.amount = amount;
  }

  @Override
  public @NotNull ItemBuilder name(final @NotNull String name) {
    this.name = name;
    return this;
  }

  @Override
  public @NotNull ItemBuilder lore(final @NotNull List<String> lore) {
    this.lore = lore;
    return this;
  }

  @Override
  public @NotNull ItemBuilder glow(final boolean glow) {
    this.glow = glow;
    return this;
  }

  @Override
  public @NotNull ItemBuilder disableEnchantGlow(final boolean disableEnchantGlow) {
    this.disableEnchantGlow = disableEnchantGlow;
    return this;
  }

  @Override
  public @NotNull ItemBuilder enchant(final @NotNull Enchantment enchantment, final int level) {
    if (this.enchantments == null) {
      this.enchantments = new HashMap<>();
    }
    this.enchantments.put(enchantment, level);
    return this;
  }

  @Override
  public @NotNull ItemBuilder enchantments(final @NotNull Map<Enchantment, Integer> enchantments) {
    this.enchantments = enchantments;
    return this;
  }

  @Override
  public @NotNull ItemBuilder customModelData(final int customModelData) {
    this.customModelData = customModelData;
    return this;
  }

  @Override
  public abstract @NotNull ItemStack build();
}
