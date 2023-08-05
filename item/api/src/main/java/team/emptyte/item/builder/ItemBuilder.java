package team.emptyte.item.builder;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.emptyte.item.flag.ItemFlag;
import team.emptyte.item.skull.model.SkinModel;
import team.emptyte.item.util.DyeItemUtils;

import java.util.List;
import java.util.Map;

public interface ItemBuilder {

  @NotNull ItemBuilder name(final @NotNull String name);

  @NotNull ItemBuilder lore(final @NotNull List<String> lore);

  @NotNull ItemBuilder lore(final @NotNull String... lines);

  @NotNull ItemBuilder enchantments(final @NotNull Map<Enchantment, Integer> enchantments);

  @NotNull ItemBuilder enchant(final @NotNull Enchantment enchantment, int level);

  @NotNull ItemBuilder flags(final @NotNull List<ItemFlag> flags);

  @NotNull ItemBuilder flag(final @NotNull ItemFlag... flags);

  @NotNull ItemBuilder grow();

  @NotNull ItemBuilder unbreakable(final boolean unbreakable);

  @NotNull ItemStack build();

  static @NotNull ItemBuilder builder(final @NotNull Material material) {
    return builder(material, 1);
  }

  static @NotNull ItemBuilder builder(final @NotNull Material material, final int amount) {
    return new DefaultItemBuilder(material, amount);
  }

  static @NotNull ItemBuilder skullBuilder(final @NotNull SkinModel skinModel, final int amount) {
    return new SkullItemBuilder(skinModel, amount);
  }

  static @NotNull ItemBuilder dyeBuilder(final @NotNull String materialKey, final @NotNull DyeColor dyeColor) {
    return dyeBuilder(materialKey, dyeColor, 1);
  }

  static @NotNull ItemBuilder dyeBuilder(
    final @NotNull String materialKey,
    final @NotNull DyeColor dyeColor,
    final @NotNull int amount
  ) {
    return DyeItemUtils.createBuilder(materialKey, dyeColor, amount);
  }
}
