package team.emptyte.gui.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.util.MenuUtil;

public class InventoryClickListener implements Listener {

  @EventHandler
  public void handle(final @NotNull InventoryClickEvent event) {
    final var inventory = event.getClickedInventory();

    if (MenuUtil.isCustomMenu(inventory)) {
      final int clickedSlot = event.getSlot();

      if (clickedSlot < 0) {
        return;
      }

      final var wrapper = MenuUtil.wrapper(inventory);
      final var menuInventory = wrapper.menuInventory();
      final var itemClickable = menuInventory.item(clickedSlot);

      if (itemClickable == null) {
        event.setCancelled(!menuInventory.canIntroduceItems());
        return;
      }

      if (event.getRawSlot() != clickedSlot && !menuInventory.canIntroduceItems()) {
        event.setCancelled(true);
        return;
      }

      final var action = itemClickable.action();
      final var clickAction = action.action(event.getClick());

      if (clickAction == null) {
        event.setCancelled(!menuInventory.canIntroduceItems());
      } else {
        event.setCancelled(clickAction.test(event));
      }
    }
  }
}
