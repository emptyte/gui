package team.emptyte.gui.item;

import java.util.function.Consumer;
import java.util.function.Predicate;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.item.action.ItemClickableAction;
import team.emptyte.gui.item.action.ItemClickableActionBuilder;

public class ItemClickableBuilder {

  private final int slot;
  private ItemStack item;
  private ItemClickableAction action;

  protected ItemClickableBuilder(final int slot) {
    this.slot = slot;
  }

  public @NotNull ItemClickableBuilder item(final @NotNull ItemStack item) {
    this.item = item;
    return this;
  }

  public @NotNull ItemClickableBuilder multipleAction(final @NotNull Consumer<ItemClickableActionBuilder.Multiple> action) {
    final var actionBuilder = ItemClickableAction.builder()
                                                          .multipleAction();
    action.accept(actionBuilder);
    this.action = actionBuilder.build();
    return this;
  }

  public @NotNull ItemClickableBuilder action(final @NotNull Predicate<InventoryClickEvent> action) {
    this.action = ItemClickableAction.single(action);
    return this;
  }

  public @NotNull ItemClickable build() {
    return ItemClickable.of(this.slot, this.item, this.action);
  }
}
