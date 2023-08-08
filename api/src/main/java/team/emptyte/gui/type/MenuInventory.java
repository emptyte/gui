package team.emptyte.gui.type;

import java.util.List;
import java.util.function.Predicate;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.emptyte.gui.item.ItemClickable;
import team.emptyte.gui.util.MenuUtil;

public class MenuInventory {
  protected final String title;
  protected final int slots;
  protected final List<ItemClickable> items;
  protected final Predicate<Inventory> openAction;
  protected final Predicate<Inventory> closeAction;
  protected final boolean canIntroduceItems;

  public MenuInventory(
    final @NotNull String title,
    final int slots,
    final @NotNull List<ItemClickable> items,
    final @NotNull Predicate<Inventory> openAction,
    final @NotNull Predicate<Inventory> closeAction,
    final boolean canIntroduceItems
  ) {
    this.title = title;
    this.slots = slots;
    this.items = items;
    this.openAction = openAction;
    this.closeAction = closeAction;
    this.canIntroduceItems = canIntroduceItems;
  }

  public @NotNull String title() {
    return this.title;
  }

  public int slots() {
    return this.slots;
  }

  public @NotNull List<ItemClickable> items() {
    return this.items;
  }

  public void clearItems() {
    this.items.clear();
    MenuUtil.fillItemList(this.items, this.slots);
  }

  public @Nullable ItemClickable item(final int slot) {
    return this.items.get(slot);
  }

  public void item(final @NotNull ItemClickable item) {
    this.items.set(item.slot(), item);
  }

  public void removeItem(final int slot) {
    this.items.remove(slot);
  }

  public @Nullable Predicate<Inventory> openAction() {
    return this.openAction;
  }

  public @Nullable Predicate<Inventory> closeAction() {
    return this.closeAction;
  }

  public boolean canIntroduceItems() {
    return this.canIntroduceItems;
  }
}
