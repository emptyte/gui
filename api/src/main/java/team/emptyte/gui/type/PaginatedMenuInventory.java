package team.emptyte.gui.type;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.item.ItemClickable;

public class PaginatedMenuInventory<E> extends MenuInventory {

  private final int entitySlotFrom;
  private final int availableEntitySlots;
  private final List<Integer> availableSlots;
  private final int maxPages;
  private final List<E> entities;
  private final int currentPage;
  private final List<String> layoutLines;
  private final Map<Character, ItemClickable> layoutItems;
  private final Function<E, ItemClickable> entityParser;
  private final Function<Integer, ItemClickable> previousPageItem;
  private final Function<Integer, ItemClickable> nextPageItem;
  private final ItemClickable itemIfNoEntities;
  private final ItemClickable itemIfNoPreviousPage;
  private final ItemClickable itemIfNoNextPage;

  public PaginatedMenuInventory(
    final @NotNull String title,
    final int slots,
    final @NotNull List<ItemClickable> items,
    final @NotNull Predicate<Inventory> openAction,
    final @NotNull Predicate<Inventory> closeAction,
    final boolean canIntroduceItems,
    final int entitySlotFrom,
    final int availableEntitySlots,
    final @NotNull List<Integer> availableSlots,
    final @NotNull List<E> entities,
    final int currentPage,
    final @NotNull List<String> layoutLines,
    final @NotNull Map<Character, ItemClickable> layoutItems,
    final @NotNull Function<E, ItemClickable> entityParser,
    final @NotNull Function<Integer, ItemClickable> previousPageItem,
    final @NotNull Function<Integer, ItemClickable> nextPageItem,
    final @NotNull ItemClickable itemIfNoEntities,
    final @NotNull ItemClickable itemIfNoPreviousPage,
    final @NotNull ItemClickable itemIfNoNextPage
  ) {
    super(title, slots, items, openAction, closeAction, canIntroduceItems);
    this.entitySlotFrom = entitySlotFrom;
    this.availableEntitySlots = availableEntitySlots;
    this.availableSlots = availableSlots;
    this.maxPages = (int) Math.ceil(entities.size() / (double) availableEntitySlots);
    this.entities = entities;
    this.currentPage = currentPage;
    this.layoutLines = layoutLines;
    this.layoutItems = layoutItems;
    this.entityParser = entityParser;
    this.previousPageItem = previousPageItem;
    this.nextPageItem = nextPageItem;
    this.itemIfNoEntities = itemIfNoEntities;
    this.itemIfNoPreviousPage = itemIfNoPreviousPage;
    this.itemIfNoNextPage = itemIfNoNextPage;
  }

  public int entitySlotFrom() {
    return this.entitySlotFrom;
  }

  public @NotNull List<Integer> availableSlots() {
    return this.availableSlots;
  }

  public int availableEntitySlots() {
    return this.availableEntitySlots;
  }

  public int maxPages() {
    return this.maxPages;
  }

  public int currentPage() {
    return this.currentPage;
  }

  public @NotNull List<E> entities() {
    return this.entities;
  }

  public @NotNull List<String> layoutLines() {
    return this.layoutLines;
  }

  public @NotNull Map<Character, ItemClickable> layoutItems() {
    return this.layoutItems;
  }

  public @NotNull Function<E, ItemClickable> entityParser() {
    return this.entityParser;
  }

  public @NotNull Function<Integer, ItemClickable> previousPageItem() {
    return this.previousPageItem;
  }

  public @NotNull Function<Integer, ItemClickable> nextPageItem() {
    return this.nextPageItem;
  }

  public @NotNull ItemClickable itemIfNoEntities() {
    return this.itemIfNoEntities;
  }

  public @NotNull ItemClickable itemIfNoPreviousPage() {
    return this.itemIfNoPreviousPage;
  }

  public @NotNull ItemClickable itemIfNoNextPage() {
    return this.itemIfNoNextPage;
  }

  public @NotNull PaginatedMenuInventory<E> clone(final int page) {
    return new PaginatedMenuInventory<>(
      this.title,
      this.slots,
      this.items,
      this.openAction,
      this.closeAction,
      this.canIntroduceItems,
      this.entitySlotFrom,
      this.availableEntitySlots,
      this.availableSlots,
      this.entities,
      page,
      this.layoutLines,
      this.layoutItems,
      this.entityParser,
      this.previousPageItem,
      this.nextPageItem,
      this.itemIfNoEntities,
      this.itemIfNoPreviousPage,
      this.itemIfNoNextPage
    );
  }
}
