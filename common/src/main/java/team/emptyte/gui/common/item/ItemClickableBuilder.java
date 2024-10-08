package team.emptyte.gui.common.item;

import java.util.function.Predicate;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.common.item.action.ItemClickableAction;

public final class ItemClickableBuilder {
  private final int slot;
  private ItemStack itemStack;
  private ItemClickableAction action;

  public ItemClickableBuilder(final int slot) {
    this.slot = slot;
  }

  public @NotNull ItemClickableBuilder item(final @NotNull ItemStack itemStack) {
    this.itemStack = itemStack;
    return this;
  }

  public @NotNull ItemClickableBuilder action(final @NotNull Predicate<@NotNull InventoryClickEvent> action) {
    this.action = ItemClickableAction.single(action);
    return this;
  }

  public @NotNull ItemClickable build() {
    return new ItemClickable(this.slot, this.itemStack, this.action);
  }
}
