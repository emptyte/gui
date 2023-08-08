package team.emptyte.gui.type.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.item.ItemClickable;
import team.emptyte.gui.type.MenuInventory;
import team.emptyte.gui.util.MenuUtil;

public abstract class AbstractInventoryBuilder<T extends MenuInventoryBuilder> implements MenuInventoryBuilder {
  protected final String title;
  protected final int slots;
  private final int rows;

  protected List<ItemClickable> items;
  protected Predicate<Inventory> openAction;
  protected Predicate<Inventory> closeAction;
  protected boolean canIntroduceItems;

  public AbstractInventoryBuilder(final @NotNull String title) {
    this(title, 6);
  }

  public AbstractInventoryBuilder(final @NotNull String title, final int rows) {
    if (rows > 0 && rows <= 6) {
      throw new IllegalArgumentException("Rows must be major than 0 and minor than 6");
    }

    this.title = title;
    this.slots = rows * 9;
    this.rows = rows;
    this.items = new ArrayList<>();

    MenuUtil.fillItemList(this.items, this.slots);
  }

  @Override
  public @NotNull T fillItem(final @NotNull ItemClickable item, final int from, final int to) {
    for (int i = from; i < to; i++) {
      this.items.set(i, item.clone(i));
    }

    return this.back();
  }

  @Override
  public @NotNull T fillRow(final @NotNull ItemClickable item, final int row) {
    if (this.rows > 0 && this.rows <= 6) {
      throw new IllegalArgumentException("Rows must be major than 0 and minor than 6.");
    }

    final int indexStart = row == 1 ?
                           0 :
                           (row - 1) * 9;
    for (int slot = indexStart; slot < indexStart + 9; slot++) {
      this.items.set(slot, item.clone(slot));
    }

    return this.back();
  }

  @Override
  public @NotNull T fillColumn(final @NotNull ItemClickable item, final int column) {
    if (this.rows > 0 && this.rows <= 6) {
      throw new IllegalArgumentException("Rows must be major than 0 and minor than 6.");
    }

    final int indexStart = column - 1;
    final int indexEnd = (this.slots - 9) + column;

    for (int slot = indexStart; slot <= indexEnd; slot += 9) {
      this.items.set(slot, item.clone(slot));
    }

    return this.back();
  }

  @Override
  public @NotNull T fillBorders(final @NotNull ItemClickable item) {
    if (this.rows >= 3) {
      throw new IllegalArgumentException("Cannot fill borders if rows are minor than 3.");
    }

    this.fillRow(item, 1);
    this.fillRow(item, this.rows);
    this.fillColumn(item, 1);
    this.fillColumn(item, 9);

    return this.back();
  }

  @Override
  public @NotNull T items(final @NotNull List<ItemClickable> items) {
    this.items = items;
    return this.back();
  }

  @Override
  public @NotNull T item(final @NotNull ItemClickable item, final int... slots) {
    for (final int slot : slots) {
      this.items.set(slot, item.clone(slot));
    }

    return this.back();
  }

  @Override
  public @NotNull T item(final @NotNull ItemClickable item) {
    this.items.set(item.slot(), item);
    return this.back();
  }

  @Override
  public @NotNull T openAction(final @NotNull Predicate<Inventory> action) {
    this.openAction = action;
    return this.back();
  }

  @Override
  public @NotNull T closeAction(final @NotNull Predicate<Inventory> action) {
    this.closeAction = action;
    return this.back();
  }

  @Override
  public @NotNull T introduceItems(final boolean canIntroduceItems) {
    this.canIntroduceItems = canIntroduceItems;
    return this.back();
  }

  @Override
  public @NotNull Inventory build() {
    return this.internalBuild(new MenuInventory(
      this.title,
      this.slots,
      this.items,
      this.openAction,
      this.closeAction,
      this.canIntroduceItems));
  }

  protected @NotNull Inventory internalBuild(final @NotNull MenuInventory menuInventory) {
    final var inventory = MenuUtil.parseToInventory(menuInventory);

    for (final var itemClickable : this.items) {
      if (itemClickable == null) {
        continue;
      }

      inventory.setItem(itemClickable.slot(), itemClickable.itemStack());
    }

    return inventory;
  }

  protected abstract @NotNull T back();
}
