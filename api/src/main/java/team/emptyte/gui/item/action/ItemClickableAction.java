package team.emptyte.gui.item.action;

import java.util.function.Predicate;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ItemClickableAction {

  ItemClickableAction CANCEL_CLICK = new SingleClickableAction(inventory -> true);

  @Nullable Predicate<InventoryClickEvent> action(final @NotNull ClickType clickType);

  static @NotNull ItemClickableActionBuilder builder() {
    return new ItemClickableActionBuilder();
  }

  static @NotNull ItemClickableAction single(final @NotNull Predicate<InventoryClickEvent> action) {
    return new SingleClickableAction(action);
  }
}
