package team.emptyte.gui.common.item.action;

import java.util.function.Predicate;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ItemClickableAction {
  ItemClickableAction CANCEL_CLICK = new SingleClickableAction(inventory -> true);

  @Nullable Predicate<@NotNull InventoryClickEvent> action(final @NotNull ClickType clickType);

  static @NotNull ItemClickableAction single(final @NotNull Predicate<@NotNull InventoryClickEvent> action) {
    return new SingleClickableAction(action);
  }
}
