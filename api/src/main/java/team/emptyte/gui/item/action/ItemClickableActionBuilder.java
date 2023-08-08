package team.emptyte.gui.item.action;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class ItemClickableActionBuilder {

  protected ItemClickableActionBuilder() {
  }

  public Multiple multipleAction() {
    return new Multiple();
  }

  public @NotNull ItemClickableAction globalAction(final @NotNull Predicate<InventoryClickEvent> action) {
    return new SingleClickableAction(action);
  }

  public static class Multiple {

    private final Map<ClickType, Predicate<InventoryClickEvent>> actions;

    public Multiple() {
      this.actions = new HashMap<>();
    }

    public @NotNull Multiple link(
      final @NotNull ClickType clickType,
      final @NotNull Predicate<InventoryClickEvent> action
    ) {
      this.actions.put(clickType, action);
      return this;
    }

    public @NotNull ItemClickableAction build() {
      return new MultipleItemClickableAction(this.actions);
    }
  }
}
