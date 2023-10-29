package team.emptyte.gui.menu.inventory;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AbstractMenuInventory {
  private final Component title;
  private final int slots;
  private final List<ItemStack> items;
  private final boolean canIntroduceItems;

  public AbstractMenuInventory(final @NotNull Component title, final int slots, final @NotNull List<ItemStack> items, final boolean canIntroduceItems) {
    this.title = title;
    this.slots = slots;
    this.items = items;
    this.canIntroduceItems = canIntroduceItems;
  }

  public @NotNull Component title() {
    return this.title;
  }

  public int slots() {
    return this.slots;
  }

  public @NotNull List<ItemStack> items() {
    return this.items;
  }

  public void addItem(final @NotNull ItemStack itemStack) {
    this.items.add(itemStack);
  }

  public void removeItem(final @NotNull ItemStack itemStack) {
    this.items.remove(itemStack);
  }

  public boolean canIntroduceItems() {
    return this.canIntroduceItems;
  }
}
