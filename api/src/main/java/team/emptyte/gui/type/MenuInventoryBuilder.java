package team.emptyte.gui.type;

import java.util.List;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import team.emptyte.items.parser.model.ItemModel;

/**
 * This class represents a builder
 * for the creation of inventories.
 *
 * @since 0.0.1
 */
public interface MenuInventoryBuilder {
  /**
   * This method allows us to know if the
   * placeholder will be activated in the inventory.
   *
   * @param applyPlaceholderApi If the inventory should apply the placeholders.
   * @return The current instance of the builder.
   * @since 0.0.1
   */
  @NotNull MenuInventoryBuilder applyPlaceholderApi(final boolean applyPlaceholderApi);

  /**
   * This method allows us to know if the
   * items will be introduced in the inventory.
   *
   * @param introduceItems If the inventory should introduce the items.
   * @return The current instance of the builder.
   * @since 0.0.1
   */
  @NotNull MenuInventoryBuilder introduceItems(final boolean introduceItems);

  /**
   * This method allows us to know the size of
   * the inventory and where the items will be located.
   *
   * @param layout The layouts of the inventory.
   * @return The current instance of the builder.
   * @since 0.0.1
   */
  @NotNull MenuInventoryBuilder layout(final @NotNull String... layout);

  /**
   * This method allows us to know the size of
   * the inventory and where the items will be located.
   *
   * @param layout The layouts of the inventory.
   * @return The current instance of the builder.
   * @since 0.0.1
   */
  @NotNull MenuInventoryBuilder layout(final @NotNull List<String> layout);

  /**
   * This method allows us to know the replacements
   * that will be made in the inventory.
   *
   * @param replacements The replacements of the inventory.
   * @return The current instance of the builder.
   * @since 0.0.1
   */
  @NotNull MenuInventoryBuilder replacements(final @NotNull ItemModel... replacements);

  /**
   * This method allows us to know the replacements
   * that will be made in the inventory.
   *
   * @param replacements The replacements of the inventory.
   * @return The current instance of the builder.
   * @since 0.0.1
   */
  @NotNull MenuInventoryBuilder replacements(final @NotNull List<ItemModel> replacements);

  /**
   * This method allows us to build the {@link Inventory}.
   *
   * @return The {@link Inventory}.
   * @since 0.0.1
   */
  @NotNull Inventory build();
}
