package team.emptyte.gui.parser.creator;

import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.parser.menu.MenuModel;
import team.emptyte.gui.type.MenuInventory;

/**
 * this class represents a creator
 * for the creation of menus.
 *
 * @since 0.0.1
 */
public final class MenuCreator {
  private MenuCreator() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  /**
   * This method allows us to create an {@link Inventory}
   * from a {@link MenuModel}.
   *
   * @param model The model of the menu.
   * @return The {@link Inventory}.
   * @since 0.0.1
   */
  public static @NotNull Inventory createInventory(final @NotNull MenuModel model) {
    return MenuInventory.builder(model.title())
      .applyPlaceholderApi(model.applyPlaceholderApi())
      .introduceItems(model.introduceItems())
      .layout(model.layout())
      .replacements(model.replacements())
      .build();
  }
}
