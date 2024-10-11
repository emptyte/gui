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

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jetbrains.annotations.NotNull;

public class LeatherArmorBuilder extends ItemBuilderLayout<LeatherArmorBuilder> {
  private int red;
  private int green;
  private int blue;

  protected LeatherArmorBuilder(final @NotNull Material material, final int amount) {
    super(material, amount);
  }

  public @NotNull LeatherArmorBuilder fromLeatherColor(final @NotNull LeatherArmorColor armorColor) {
    return this.fromRgb(armorColor.getRed(), armorColor.getGreen(), armorColor.getBlue());
  }

  public @NotNull LeatherArmorBuilder fromRgb(final int red, final int green, final int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    return this;
  }

  @Override
  public @NotNull ItemStack build() {
    final ItemStack itemStack = super.build();
    final LeatherArmorMeta armorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
    armorMeta.setColor(Color.fromRGB(this.red, this.green, this.blue));
    itemStack.setItemMeta(armorMeta);

    return itemStack;
  }

  @Override
  protected @NotNull LeatherArmorBuilder back() {
    return this;
  }
}
