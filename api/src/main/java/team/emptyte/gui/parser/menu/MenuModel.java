package team.emptyte.gui.parser.menu;

import java.util.List;
import net.kyori.adventure.text.Component;
import org.fenixteam.storage.model.Model;
import org.jetbrains.annotations.NotNull;
import team.emptyte.items.parser.model.ItemModel;

/**
 * This class represents a model
 * for the creation of menus, using a Json object.
 *
 * @param id                  The id of the menu.
 * @param applyPlaceholderApi If the menu should apply the placeholder.
 * @param title               The title of the menu.
 * @param introduceItems      If the menu should introduce the items.
 * @param layout            The layouts of the menu.
 * @param replacements        The replacements of the menu.
 * @since 0.0.1
 */
public record MenuModel(
  @NotNull String id,
  boolean applyPlaceholderApi,
  @NotNull Component title,
  boolean introduceItems,
  @NotNull List<String> layout,
  @NotNull List<ItemModel> replacements
) implements Model {
}
