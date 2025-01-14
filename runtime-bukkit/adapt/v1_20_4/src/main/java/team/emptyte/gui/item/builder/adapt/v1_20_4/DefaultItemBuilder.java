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
package team.emptyte.gui.item.builder.adapt.v1_20_4;

import java.util.Map;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.adapt.item.builder.AbstractItemBuilder;

public final class DefaultItemBuilder extends AbstractItemBuilder {
  public DefaultItemBuilder(final @NotNull Material material, final int amount) {
    super(material, amount);
  }

  private @NotNull ListTag createLoreListTag() {
    final ListTag loreListTag = new ListTag();
    for (final String line : super.lore) {
      loreListTag.add(StringTag.valueOf(GsonComponentSerializer.gson().serialize(MiniMessage.miniMessage().deserialize(line))));
    }
    return loreListTag;
  }

  private @NotNull ListTag createEnchantmentsListTag() {
    final ListTag enchantmentsListTag = new ListTag();
    for (final Map.Entry<Enchantment, Integer> entry : super.enchantments.entrySet()) {
      final net.minecraft.world.item.enchantment.Enchantment enchantment
        = org.bukkit.craftbukkit.v1_20_R3.enchantments.CraftEnchantment.bukkitToMinecraft(entry.getKey());
      final int level = entry.getValue();
      enchantmentsListTag.add(EnchantmentHelper.storeEnchantment(EnchantmentHelper.getEnchantmentId(enchantment), (byte) level));
    }
    return enchantmentsListTag;
  }

  @Override
  public @NotNull ItemStack build() {
    final CompoundTag compoundTag = new CompoundTag();
    compoundTag.putString("id", super.material.getKey().asString());
    compoundTag.putInt("Count", super.amount);
    compoundTag.putInt("CustomModelData", super.customModelData);
    final CompoundTag displayCompoundTag = new CompoundTag();
    displayCompoundTag.putString(net.minecraft.world.item.ItemStack.TAG_DISPLAY_NAME, GsonComponentSerializer.gson().serialize(MiniMessage.miniMessage().deserialize(super.name)));
    displayCompoundTag.put(net.minecraft.world.item.ItemStack.TAG_LORE, this.createLoreListTag());
    compoundTag.put(net.minecraft.world.item.ItemStack.TAG_DISPLAY, displayCompoundTag);
    compoundTag.put(net.minecraft.world.item.ItemStack.TAG_ENCH, this.createEnchantmentsListTag());
    if (super.enchantments != null && !super.enchantments.isEmpty()) {
      if (super.disableEnchantGlow) {
        compoundTag.remove(net.minecraft.world.item.ItemStack.TAG_ENCH);
      } else if (super.glow) {
        compoundTag.put(net.minecraft.world.item.ItemStack.TAG_ENCH, new ListTag());
      }
    } else {
      if (super.glow) {
        compoundTag.put(net.minecraft.world.item.ItemStack.TAG_ENCH, new ListTag());
      } else if (super.disableEnchantGlow) {
        compoundTag.remove(net.minecraft.world.item.ItemStack.TAG_ENCH);
      }
    }
    return net.minecraft.world.item.ItemStack.of(compoundTag)
      .asBukkitCopy();
  }
}
