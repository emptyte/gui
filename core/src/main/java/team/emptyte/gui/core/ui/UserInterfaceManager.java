package team.emptyte.gui.core.ui;

import java.util.Map;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.emptyte.gui.common.item.ItemClickable;
import team.emptyte.gui.core.component.Component;

public final class UserInterfaceManager {
  private final Plugin plugin;

  public UserInterfaceManager(final @NotNull Plugin plugin) {
    this.plugin = plugin;
  }

  public void open(final @NotNull Player player, final @NotNull UserInterface userInterface) {
    final Inventory inventory = this.createInventory(userInterface);
    if (inventory == null) {
      return;
    }
    player.openInventory(inventory);
    player.getPersistentDataContainer().set(new NamespacedKey(this.plugin, "user-interface"), PersistentDataType.STRING, userInterface.id());
  }

  private @Nullable Inventory createInventory(final @NotNull UserInterface userInterface) {
    final Map<String, Component> components = userInterface.components();
    if (components.isEmpty()) {
      return null;
    }
    final Inventory inventory = Bukkit.createInventory(null, userInterface.size(), MiniMessage.miniMessage().deserialize(userInterface.title()));
    for (final Component child : components.values()) {
      for (final ItemClickable itemClickable : child.render()) {
        inventory.setItem(itemClickable.slot(), itemClickable.itemStack());
      }
    }
    return inventory;
  }
}
