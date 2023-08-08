package team.emptyte.gui.util;

import java.util.function.Function;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.item.ItemClickable;
import team.emptyte.gui.item.action.ItemClickableAction;
import team.emptyte.gui.type.PaginatedMenuInventory;

public final class PaginatedMenuUtil {

  private PaginatedMenuUtil() {
    throw new UnsupportedOperationException();
  }

  public static <E> @NotNull Inventory createPage(
    final @NotNull Inventory delegate, final @NotNull PaginatedMenuInventory<E> menuInventory
  ) {
    final int currentPage = menuInventory.currentPage();
    final var entities = menuInventory.entities();
    final var layoutItems = menuInventory.layoutItems();
    final var entityParser = menuInventory.entityParser();
    final int entitiesSize = entities.size();

    final var availableSlots = menuInventory.availableSlots();
    final int availableEntitySlots = menuInventory.availableEntitySlots();

    int entityIndex = currentPage == 1 ?
                      0 :
                      availableEntitySlots * (currentPage - 1);
    int currentSlot = 0;
    int entitySlotIndex = 0;

    final var itemIfNoEntities = menuInventory.itemIfNoEntities();

    for (final var layoutLine : menuInventory.layoutLines()) {
      for (final char c : layoutLine.toCharArray()) {
        ItemClickable itemClickable = null;

        switch (c) {
          case 'e' -> {
            if (entityIndex >= entitiesSize) {
              if (itemIfNoEntities != null) {
                itemClickable = itemIfNoEntities.clone(availableSlots.get(entitySlotIndex++));
              }
              break;
            }

            final var entity = entities.get(entityIndex++);
            itemClickable = entityParser.apply(entity)
                              .clone(availableSlots.get(entitySlotIndex++));
          }
          case 'n' -> itemClickable = PaginatedMenuUtil.interactPageItem(
            currentPage < menuInventory.maxPages(),
            currentPage,
            currentPage + 1,
            currentSlot,
            menuInventory,
            menuInventory.nextPageItem(),
            menuInventory.itemIfNoNextPage());
          case 'p' -> itemClickable = PaginatedMenuUtil.interactPageItem(
            currentPage > 1,
            currentPage,
            currentPage - 1,
            currentSlot,
            menuInventory,
            menuInventory.previousPageItem(),
            menuInventory.itemIfNoPreviousPage());
          default -> {
            final var layoutItem = layoutItems.get(c);

            if (layoutItem != null) {
              itemClickable = layoutItem.clone(currentSlot);
            }
          }
        }

        if (itemClickable != null) {
          delegate.setItem(itemClickable.slot(), itemClickable.itemStack());
          menuInventory.item(itemClickable);
        }

        currentSlot++;
      }
    }

    return delegate;
  }

  private static @NotNull ItemClickable interactPageItem(
    final boolean expression,
    final int currentPage,
    final int newPage,
    final int currentSlot,
    final @NotNull PaginatedMenuInventory<?> menuInventory,
    final @NotNull Function<Integer, ItemClickable> pageItem,
    final @NotNull ItemClickable orElseItem
  ) {
    ItemClickable itemClickable = null;
    if (expression) {
      itemClickable = pageItem.apply(currentPage)
                        .clone(currentSlot)
                        .clone(ItemClickableAction.single(event -> {
                          menuInventory.clearItems();
                          final var inventory = event.getClickedInventory();
                          inventory.clear();
                          PaginatedMenuUtil.createPage(inventory, menuInventory.clone(newPage));
                          return true;
                        }));
    } else {
      itemClickable = orElseItem.clone(currentSlot);
    }

    return itemClickable;
  }
}
