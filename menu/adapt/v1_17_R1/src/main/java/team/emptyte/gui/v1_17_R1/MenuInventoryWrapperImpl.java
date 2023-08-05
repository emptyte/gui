package team.emptyte.gui.v1_17_R1;

import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftInventoryCustom;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.adapt.MenuInventoryWrapper;
import team.emptyte.gui.type.MenuInventory;

public class MenuInventoryWrapperImpl extends CraftInventoryCustom
        implements MenuInventoryWrapper {

    private final MenuInventory menuInventory;

    public MenuInventoryWrapperImpl(
            InventoryHolder owner,
            MenuInventory menuInventory
    ) {
        super(
                owner,
                menuInventory.getSlots(),
                menuInventory.getTitle()
        );

        this.menuInventory = menuInventory;
    }

    @Override
    public @NotNull Inventory getRawInventory() {
        return this;
    }

    @Override
    public @NotNull MenuInventory getMenuInventory() {
        return menuInventory;
    }

}
