package team.emptyte.gui.menu.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.menu.item.action.MenuItemAction;

public final class MenuItemBuilder {
  private final int slot;
  private ItemStack itemStack = ItemStack.of(Material.BEDROCK);
  private MenuItemAction action = MenuItemAction.CALL_BACK;

  public MenuItemBuilder(final int slot) {
    this.slot = slot;
  }

  public @NotNull MenuItemBuilder itemStack(final @NotNull ItemStack itemStack) {
    this.itemStack = itemStack;
    return this;
  }

  public MenuItemBuilder action(final MenuItemAction action) {
    this.action = action;
    return this;
  }

  public @NotNull MenuItem build() {
    return new MenuItem(this.slot, this.itemStack, this.action);
  }
}
