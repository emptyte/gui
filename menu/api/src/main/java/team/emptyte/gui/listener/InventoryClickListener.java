package team.emptyte.gui.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import team.emptyte.gui.item.ItemClickable;
import team.emptyte.gui.item.action.ItemClickableAction;
import team.emptyte.gui.adapt.MenuInventoryWrapper;
import team.emptyte.gui.type.MenuInventory;
import team.emptyte.gui.util.MenuUtil;

import java.util.function.Predicate;

public class InventoryClickListener
        implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();

        if (MenuUtil.isCustomMenu(inventory)) {
            int clickedSlot = event.getSlot();

            if (clickedSlot < 0) {
                return;
            }

            MenuInventoryWrapper wrapper = MenuUtil.getAsWrapper(inventory);
            MenuInventory menuInventory = wrapper.getMenuInventory();
            ItemClickable itemClickable = menuInventory.getItem(clickedSlot);

            if (itemClickable == null) {
                event.setCancelled(!menuInventory.canIntroduceItems());
                return;
            }

            if (event.getRawSlot() != clickedSlot && !menuInventory.canIntroduceItems()) {
                event.setCancelled(true);
                return;
            }

            ItemClickableAction action = itemClickable.getAction();
            Predicate<InventoryClickEvent> clickAction = action.getAction(event.getClick());

            if (clickAction == null) {
                event.setCancelled(!menuInventory.canIntroduceItems());
            } else {
                event.setCancelled(clickAction.test(event));
            }
        }
    }

}
