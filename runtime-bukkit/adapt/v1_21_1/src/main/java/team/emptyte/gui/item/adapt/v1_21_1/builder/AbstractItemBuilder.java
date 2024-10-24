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
package team.emptyte.gui.item.adapt.v1_21_1.builder;

import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.component.Unbreakable;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.minecraft.adapt.v1_21_1.ComponentHelper;

public abstract class AbstractItemBuilder implements ItemBuilder {
  private final Material material;
  private final int amount;

  private String name;
  private List<String> lore;
  private int customModelData;
  private boolean unbreakable;

  protected AbstractItemBuilder(final @NotNull Material material, final int amount) {
    this.material = material;
    this.amount = amount;
  }

  @Override
  public @NotNull ItemBuilder name(final @NotNull String name) {
    this.name = name;
    return this.back();
  }

  @Override
  public @NotNull ItemBuilder lore(final @NotNull String... lore) {
    this.lore = List.of(lore);
    return this.back();
  }

  @Override
  public @NotNull ItemBuilder lore(final @NotNull List<String> lore) {
    this.lore = lore;
    return this.back();
  }

  @Override
  public @NotNull ItemBuilder customModelData(final int customModelData) {
    this.customModelData = customModelData;
    return this.back();
  }

  @Override
  public @NotNull ItemBuilder unbreakable(final boolean unbreakable) {
    this.unbreakable = unbreakable;
    return this.back();
  }

  @Override
  public @NotNull ItemStack build() {
    final Holder<Item> itemHolder = Holder.direct(BuiltInRegistries.ITEM.get(ResourceLocation.parse(this.material.getKey().asString())));
    final DataComponentPatch dataComponentPatch = DataComponentPatch.builder()
      .set(DataComponents.ITEM_NAME, ComponentHelper.deserialize(this.name))
      .set(DataComponents.LORE, new ItemLore(ComponentHelper.deserialize(this.lore)))
      .set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(this.customModelData))
      .set(DataComponents.UNBREAKABLE, new Unbreakable(this.unbreakable))
      .build();
    final net.minecraft.world.item.ItemStack itemStack = new net.minecraft.world.item.ItemStack(
      itemHolder,
      this.amount,
      dataComponentPatch
    );
    return itemStack.getBukkitStack();
  }

  public abstract @NotNull ItemBuilder back();
}
