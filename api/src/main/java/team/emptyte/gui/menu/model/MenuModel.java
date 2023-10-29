package team.emptyte.gui.menu.model;

import org.fenixteam.storage.model.Model;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.menu.inventory.AbstractMenuInventory;

public record MenuModel(@NotNull String id, @NotNull AbstractMenuInventory menuInventory) implements Model {
}
