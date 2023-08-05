package team.emptyte.gui.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import team.emptyte.gui.adapt.MenuInventoryWrapper;
import team.emptyte.gui.type.MenuInventory;
import team.emptyte.gui.util.MenuUtil;

import java.util.function.Predicate;

public class InventoryOpenListener
        implements Listener {

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        Inventory inventory = event.getInventory();

        if (MenuUtil.isCustomMenu(inventory)) {
            MenuInventoryWrapper wrapper = MenuUtil.getAsWrapper(inventory);
            MenuInventory menuInventory = wrapper.getMenuInventory();
            Predicate<Inventory> action = menuInventory.getOpenAction();

            if (action != null) {
                if (action.test(inventory)) {
                    event.setCancelled(true);
                }
            }
        }
    }

}
