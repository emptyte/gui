package team.emptyte.gui.item;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.item.action.ItemClickableAction;

public final class ItemClickable {

  private final int slot;
  private final ItemStack itemStack;
  private final ItemClickableAction action;

  private ItemClickable(
    final int slot,
    final @NotNull ItemStack itemStack,
    final @NotNull ItemClickableAction action
  ) {
    this.slot = slot;
    this.itemStack = itemStack;
    this.action = action;
  }

  public static @NotNull ItemClickable onlyItem(final @NotNull ItemStack itemStack) {
    return ItemClickable.onlyItem(itemStack, ItemClickableAction.CANCEL_CLICK);
  }

  public static @NotNull ItemClickable onlyItem(
    final @NotNull ItemStack itemStack,
    final @NotNull ItemClickableAction action
  ) {
    return ItemClickable.of(-1, itemStack, action);
  }

  public static @NotNull ItemClickable of(final int slot, final @NotNull ItemStack itemStack) {
    return ItemClickable.of(slot, itemStack, ItemClickableAction.CANCEL_CLICK);
  }

  public static @NotNull ItemClickable of(
    final int slot,
    final @NotNull ItemStack itemStack,
    final @NotNull ItemClickableAction action
  ) {
    return new ItemClickable(slot, itemStack, action);
  }

  public static @NotNull ItemClickableBuilder builder(final int slot) {
    return new ItemClickableBuilder(slot);
  }

  public static @NotNull ItemClickableBuilder builder() {
    return new ItemClickableBuilder(-1);
  }

  public int slot() {
    return this.slot;
  }

  public @NotNull ItemStack itemStack() {
    return this.itemStack;
  }

  public @NotNull ItemClickableAction action() {
    return this.action;
  }

  public @NotNull ItemClickable clone(final int slot) {
    return new ItemClickable(slot, this.itemStack, this.action);
  }

  public @NotNull ItemClickable clone(final @NotNull ItemClickableAction action) {
    return new ItemClickable(this.slot, this.itemStack, action);
  }

  public @NotNull ItemClickable clone(final @NotNull ItemStack itemStack) {
    return new ItemClickable(this.slot, itemStack, this.action);
  }

  @Override
  public @NotNull String toString() {
    return "ItemClickable{" +
           "slot=" + this.slot +
           ", itemStack=" + this.itemStack +
           '}';
  }
}
