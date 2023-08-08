package team.emptyte.gui.type.builder;

import java.util.List;
import java.util.function.Predicate;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.item.ItemClickable;

public interface MenuInventoryBuilder {

  @NotNull MenuInventoryBuilder fillItem(final @NotNull ItemClickable item, final int from, final int to);

  @NotNull MenuInventoryBuilder fillRow(final @NotNull ItemClickable item, final int row);

  @NotNull MenuInventoryBuilder fillColumn(final @NotNull ItemClickable item, final int column);

  @NotNull MenuInventoryBuilder fillBorders(final @NotNull ItemClickable item);

  @NotNull MenuInventoryBuilder items(final @NotNull List<ItemClickable> items);

  @NotNull MenuInventoryBuilder item(final @NotNull ItemClickable item, final int... slots);

  @NotNull MenuInventoryBuilder item(final @NotNull ItemClickable item);

  @NotNull MenuInventoryBuilder openAction(final @NotNull Predicate<Inventory> action);

  @NotNull MenuInventoryBuilder closeAction(final @NotNull Predicate<Inventory> action);

  @NotNull MenuInventoryBuilder introduceItems(final boolean canIntroduceItems);

  @NotNull Inventory build();
}
