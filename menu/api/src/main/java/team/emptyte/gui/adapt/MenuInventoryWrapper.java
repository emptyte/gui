package team.emptyte.gui.adapt;

import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.type.MenuInventory;

public interface MenuInventoryWrapper {

  @NotNull MenuInventory getMenuInventory();

  @NotNull Inventory getRawInventory();
}
