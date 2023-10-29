package team.emptyte.gui.menu.inventory;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class StringLayoutMenuInventory extends AbstractMenuInventory {
  protected final List<String> layoutLines;
  protected final Map<Character, ItemStack> layoutItems;

  public StringLayoutMenuInventory(
    final @NotNull Component title,
    final int size,
    final @NotNull List<ItemStack> items,
    final boolean canIntroduceItems,
    final @NotNull List<String> layoutLines,
    final @NotNull Map<Character, ItemStack> layoutItems
  ) {
    super(title, size, items, canIntroduceItems);
    this.layoutLines = layoutLines;
    this.layoutItems = layoutItems;
  }

  public @NotNull List<String> layoutLines() {
    return this.layoutLines;
  }

  public @NotNull Map<Character, ItemStack> layoutItems() {
    return this.layoutItems;
  }
}
