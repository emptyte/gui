package team.emptyte.gui.common.item.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractItemBuilder<T extends ItemBuilder> implements ItemBuilder {
  protected final Material material;
  private final int amount;

  private String name;
  private List<String> lore;
  private Map<Enchantment, Integer> enchantments;
  private List<ItemFlag> flags;
  private boolean unbreakable;

  public AbstractItemBuilder(final @NotNull Material material, final int amount) {
    this.material = material;
    this.amount = amount;

    this.lore = new ArrayList<>();
    this.enchantments = new HashMap<>();
    this.flags = new ArrayList<>();
  }

  @Override
  public @NotNull ItemBuilder name(final @NotNull String name) {
    this.name = name;
    return this.back();
  }

  @Override
  public @NotNull ItemBuilder lore(final @NotNull List<String> lore) {
    this.lore = lore;
    return this.back();
  }

  @Override
  public @NotNull ItemBuilder lore(final @NotNull String... lines) {
    this.lore = List.of(lines);
    return this.back();
  }

  @Override
  public @NotNull ItemBuilder enchantments(final @NotNull Map<Enchantment, Integer> enchantments) {
    this.enchantments = enchantments;
    return this.back();
  }

  @Override
  public @NotNull ItemBuilder enchant(final @NotNull Enchantment enchantment, final int level) {
    this.enchantments.put(enchantment, level);
    return this.back();
  }

  @Override
  public @NotNull ItemBuilder flags(final @NotNull List<ItemFlag> flags) {
    this.flags = flags;
    return this.back();
  }

  @Override
  public @NotNull ItemBuilder flag(final @NotNull ItemFlag... flags) {
    this.flags = List.of(flags);
    return this.back();
  }

  @Override
  public @NotNull ItemBuilder grow() {
    this.enchantments.put(Enchantment.UNBREAKING, 3);
    this.flags.add(ItemFlag.HIDE_ENCHANTS);
    return this.back();
  }

  @Override
  public @NotNull ItemBuilder unbreakable(final boolean unbreakable) {
    this.unbreakable = unbreakable;
    return this.back();
  }

  @Override
  public @NotNull ItemStack build() {
    final ItemStack itemStack = new ItemStack(this.material, this.amount);

    itemStack.editMeta(meta -> {
      if (this.name != null) {
        meta.displayName(MiniMessage.miniMessage().deserialize(this.name));
      }

      if (!this.lore.isEmpty()) {
        meta.lore(this.lore.stream()
            .map(line -> MiniMessage.miniMessage().deserialize(line))
            .toList());
      }

      if (!this.enchantments.isEmpty()) {
        for (final Map.Entry<Enchantment, Integer> entry : this.enchantments.entrySet()) {
          meta.addEnchant(entry.getKey(), entry.getValue(), true);
        }
      }

      if (!this.flags.isEmpty()) {
        meta.addItemFlags(this.flags.toArray(new ItemFlag[0]));
      }

      meta.setUnbreakable(this.unbreakable);
    });

    return itemStack;
  }

  public abstract @NotNull T back();

}
