package team.emptyte.item.builder;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.emptyte.item.flag.ItemFlag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static team.unnamed.validate.Validate.isNotNull;

abstract class ItemBuilderLayout<T extends ItemBuilder>
  implements ItemBuilder {

  protected final Material material;
  private final int amount;

  private Component name;
  private List<Component> lore;
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
    this.name = isNotNull(MiniMessage.miniMessage()
                            .deserialize(name), "Item name cannot be null.");
    return back();
  }

  @Override
  public @NotNull ItemBuilder lore(final @NotNull List<String> lore) {
    final var newLore = new ArrayList<Component>();
    for (final var line : lore) {
      newLore.add(MiniMessage.miniMessage()
                    .deserialize(line));
    }

    this.lore = isNotNull(newLore, "Item lore cannot be null.");
    return back();
  }

  @Override
  public @NotNull ItemBuilder lore(final @NotNull String... lines) {
    final var newLore = new ArrayList<Component>();
    for (final var line : lines) {
      newLore.add(MiniMessage.miniMessage()
                    .deserialize(line));
    }

    this.lore = isNotNull(newLore, "Item lore cannot be null.");
    return back();
  }

  @Override
  public @NotNull ItemBuilder enchantments(final @NotNull Map<Enchantment, Integer> enchantments) {
    this.enchantments = isNotNull(enchantments, "Item enchantments cannot be null.");
    return back();
  }

  @Override
  public @NotNull ItemBuilder enchant(final @NotNull Enchantment enchantment, int level) {
    this.enchantments.put(isNotNull(enchantment, "Item enchantment cannot be null."), level);
    return back();
  }

  @Override
  public @NotNull ItemBuilder flags(final @NotNull List<ItemFlag> flags) {
    this.flags = isNotNull(flags, "Item flags cannot be null.");
    return back();
  }

  @Override
  public @NotNull ItemBuilder flag(final @NotNull ItemFlag... flags) {
    this.flags.addAll(Arrays.asList(flags));
    return back();
  }

  @Override
  public @NotNull ItemBuilder grow() {
    this.enchantments.put(Enchantment.DURABILITY, 3);
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
    final var itemStack = new ItemStack(material, amount);

    itemStack.editMeta(meta -> {
      enchantments.forEach((enchantment, level) -> meta.addEnchant(enchantment, level, true));

      meta.displayName(this.name);
      meta.lore(this.lore);

      final var itemFlags = new ArrayList<org.bukkit.inventory.ItemFlag>();
      for (final var itemFlag : flags) {
        itemFlags.add(org.bukkit.inventory.ItemFlag.valueOf(itemFlag.name()));
      }
      itemFlags.forEach(meta::addItemFlags);

      meta.setUnbreakable(unbreakable);
    });

    return itemStack;
  }

  protected abstract @NotNull T back();
}
