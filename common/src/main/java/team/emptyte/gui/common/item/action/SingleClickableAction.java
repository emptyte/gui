package team.emptyte.gui.common.item.action;

import java.util.function.Predicate;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SingleClickableAction implements ItemClickableAction {
  private final Predicate<InventoryClickEvent> action;

  protected SingleClickableAction(final @NotNull Predicate<InventoryClickEvent> action) {
    this.action = action;
  }

  @Override
  public @Nullable Predicate<@NotNull InventoryClickEvent> action(final @NotNull ClickType clickType) {
    return this.action;
  }
}
