package team.emptyte.gui.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.util.MenuUtil;

public class InventoryCloseListener implements Listener {

  private final Plugin plugin;

  public InventoryCloseListener(final @NotNull Plugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void handle(final @NotNull InventoryCloseEvent event) {
    final var inventory = event.getInventory();

    if (MenuUtil.isCustomMenu(inventory)) {
      final var wrapper = MenuUtil.wrapper(inventory);
      final var menuInventory = wrapper.menuInventory();
      final var action = menuInventory.closeAction();

      if (action != null) {
        if (action.test(inventory)) {
          Bukkit.getScheduler()
            .runTaskLater(
              this.plugin,
              () -> event.getPlayer()
                      .openInventory(inventory),
              1);
        }
      }
    }
  }
}
