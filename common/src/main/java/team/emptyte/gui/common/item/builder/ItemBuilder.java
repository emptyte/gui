package team.emptyte.gui.common.item.builder;

import java.util.List;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface ItemBuilder {
  @NotNull ItemBuilder name(final @NotNull String name);

  @NotNull ItemBuilder lore(final @NotNull List<String> lore);

  @NotNull ItemBuilder lore(final @NotNull String... lines);

  @NotNull ItemBuilder enchantments(final @NotNull Map<Enchantment, Integer> enchantments);

  @NotNull ItemBuilder enchant(final @NotNull Enchantment enchantment, final int level);

  @NotNull ItemBuilder flags(final @NotNull List<ItemFlag> flags);

  @NotNull ItemBuilder flag(final @NotNull ItemFlag... flags);

  @NotNull ItemBuilder grow();

  @NotNull ItemBuilder unbreakable(final boolean unbreakable);

  @NotNull ItemStack build();

  static ItemBuilder builder(final @NotNull Material material) {
    return builder(material, 1);
  }

  static ItemBuilder builder(final @NotNull Material material, int amount) {
    return new DefaultItemBuilder(material, amount);
  }
}
