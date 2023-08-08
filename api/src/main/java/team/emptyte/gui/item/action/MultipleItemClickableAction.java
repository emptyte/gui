package team.emptyte.gui.item.action;

import java.util.Map;
import java.util.function.Predicate;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MultipleItemClickableAction
  implements ItemClickableAction {

  private final Map<ClickType, Predicate<InventoryClickEvent>> actions;

  public MultipleItemClickableAction(final @NotNull Map<ClickType, Predicate<InventoryClickEvent>> actions) {
    this.actions = actions;
  }

  @Override
  public @Nullable Predicate<InventoryClickEvent> action(final @NotNull ClickType clickType) {
    return this.actions.get(clickType);
  }
}
