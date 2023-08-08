package team.emptyte.gui.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.util.MenuUtil;

public class InventoryOpenListener implements Listener {

  @EventHandler
  public void handle(final @NotNull InventoryOpenEvent event) {
    final var inventory = event.getInventory();

    if (MenuUtil.isCustomMenu(inventory)) {
      final var wrapper = MenuUtil.wrapper(inventory);
      final var menuInventory = wrapper.menuInventory();
      final var action = menuInventory.openAction();

      if (action != null) {
        if (action.test(inventory)) {
          event.setCancelled(true);
        }
      }
    }
  }
}
