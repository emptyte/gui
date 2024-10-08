package team.emptyte.gui.common.item;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.common.item.action.ItemClickableAction;

public record ItemClickable(
  int slot,
  @NotNull ItemStack itemStack,
  @NotNull ItemClickableAction action
) {
  public static @NotNull ItemClickableBuilder builder(final int slot) {
    return new ItemClickableBuilder(slot);
  }
}
