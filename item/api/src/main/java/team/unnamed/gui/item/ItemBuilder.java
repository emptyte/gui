package team.unnamed.gui.item;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.unnamed.gui.item.util.DyeItemUtils;

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

    static ItemBuilder builder(final @NotNull Material material) {
        return builder(material, 1);
    }

    static ItemBuilder builder(final @NotNull Material material, final int amount) {
        return builder(material, amount, (byte) 0);
    }

    static ItemBuilder builder(final @NotNull Material material, final int amount, final byte data) {
        return new DefaultItemBuilder(material, amount, data);
    }

    static ItemBuilder dyeBuilder(final @NotNull String materialKey, final @NotNull DyeColor dyeColor) {
        return dyeBuilder(materialKey, dyeColor, 1);
    }

    static ItemBuilder dyeBuilder(final @NotNull String materialKey, final @NotNull DyeColor dyeColor, final @NotNull int amount) {
        return DyeItemUtils.createBuilder(materialKey, dyeColor, amount);
    }

}
