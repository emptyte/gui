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
package team.emptyte.gui.item.builder.adapt.v1_20_6;

import java.util.Map;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.bukkit.Material;
import org.bukkit.craftbukkit.enchantments.CraftEnchantment;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.adapt.item.builder.AbstractItemBuilder;
import team.emptyte.gui.minecraft.adapt.v1_20_6.ComponentHelper;

public final class DefaultItemBuilder extends AbstractItemBuilder {
  public DefaultItemBuilder(final @NotNull Material material, final int amount) {
    super(material, amount);
  }

  private @NotNull ItemLore createItemLore() {
    if (super.lore == null || super.lore.isEmpty()) {
      return ItemLore.EMPTY;
    }
    return new ItemLore(ComponentHelper.deserialize(super.lore));
  }

  private @NotNull ItemEnchantments createItemEnchantments() {
    if (super.enchantments == null || super.enchantments.isEmpty()) {
      return ItemEnchantments.EMPTY;
    }
    final ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
    for (final Map.Entry<Enchantment, Integer> entry : super.enchantments.entrySet()) {
      final Enchantment enchantment = entry.getKey();
      final int level = entry.getValue();
      final net.minecraft.world.item.enchantment.Enchantment nmsEnchantment =
        CraftEnchantment.bukkitToMinecraft(enchantment);
      mutable.set(nmsEnchantment, level);
    }
    return mutable.toImmutable();
  }

  @Override
  public @NotNull ItemStack build() {
    final Holder<Item> itemHolder = Holder.direct(BuiltInRegistries.ITEM.get(ResourceLocation.tryParse(super.material.getKey().asString())));
    final DataComponentPatch.Builder dataComponentPatchBuilder = DataComponentPatch.builder()
      .set(DataComponents.ITEM_NAME, ComponentHelper.deserialize(super.name))
      .set(DataComponents.LORE, this.createItemLore())
      .set(DataComponents.ENCHANTMENTS, this.createItemEnchantments())
      .set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(super.customModelData));
    if (super.enchantments != null && !super.enchantments.isEmpty()) {
      if (super.disableEnchantGlow) {
        dataComponentPatchBuilder.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, false);
      } else if (super.glow) {
        dataComponentPatchBuilder.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);
      }
    } else {
      if (super.glow) {
        dataComponentPatchBuilder.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);
      } else if (super.disableEnchantGlow) {
        dataComponentPatchBuilder.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, false);
      }
    }
    return new net.minecraft.world.item.ItemStack(itemHolder, super.amount, dataComponentPatchBuilder.build())
      .asBukkitCopy();
  }
}
