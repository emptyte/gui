package team.emptyte.gui.type.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.item.ItemClickable;
import team.emptyte.gui.type.PaginatedMenuInventory;
import team.emptyte.gui.type.StringLayoutMenuInventoryBuilder;
import team.emptyte.gui.util.MenuUtil;
import team.emptyte.gui.util.PaginatedMenuUtil;

public class PaginatedMenuInventoryBuilder<E> extends StringLayoutMenuInventoryBuilder {

  private int entitySlotFrom;
  private int entitySlotTo;
  private Iterable<Integer> skippedSlots;
  private int itemsPerRow;
  private List<E> entities;
  private Function<E, ItemClickable> entityParser;
  private Function<Integer, ItemClickable> previousPageItem;
  private Function<Integer, ItemClickable> nextPageItem;
  private ItemClickable itemIfNoEntities;
  private ItemClickable itemIfNoPreviousPage;
  private ItemClickable itemIfNoNextPage;

  public PaginatedMenuInventoryBuilder(final @NotNull String title) {
    super(title);
  }

  public PaginatedMenuInventoryBuilder(final @NotNull String title, final int rows) {
    super(title, rows);
  }

  public @NotNull PaginatedMenuInventoryBuilder<E> bounds(final int entitySlotFrom, final int entitySlotTo) {
    this.entitySlotFrom = entitySlotFrom;
    this.entitySlotTo = entitySlotTo;
    return this;
  }

  public @NotNull PaginatedMenuInventoryBuilder<E> itemsPerRow(final int itemsPerRow) {
    this.itemsPerRow = itemsPerRow;
    return this;
  }

  public @NotNull PaginatedMenuInventoryBuilder<E> skippedSlots(final @NotNull Iterable<Integer> skippedSlots) {
    this.skippedSlots = skippedSlots;
    return this;
  }

  public @NotNull PaginatedMenuInventoryBuilder<E> skippedSlots(final @NotNull Integer... skippedSlots) {
    this.skippedSlots = Arrays.asList(skippedSlots);
    return this;
  }

  public @NotNull PaginatedMenuInventoryBuilder<E> entities(final @NotNull Collection<E> entities) {
    this.entities = new ArrayList<>(entities);
    return this;
  }

  public @NotNull PaginatedMenuInventoryBuilder<E> entityParser(final @NotNull Function<E, ItemClickable> entityParser) {
    this.entityParser = entityParser;
    return this;
  }

  public @NotNull PaginatedMenuInventoryBuilder<E> nextPageItem(final @NotNull Function<Integer, ItemClickable> nextPageItem) {
    this.nextPageItem = nextPageItem;
    return this;
  }

  public @NotNull PaginatedMenuInventoryBuilder<E> previousPageItem(final @NotNull Function<Integer, ItemClickable> previousPageItem) {
    this.previousPageItem = previousPageItem;
    return this;
  }

  public @NotNull PaginatedMenuInventoryBuilder<E> itemIfNoEntities(final @NotNull ItemClickable itemIfNoEntities) {
    this.itemIfNoEntities = itemIfNoEntities;
    return this;
  }

  public @NotNull PaginatedMenuInventoryBuilder<E> itemIfNoPreviousPage(final @NotNull ItemClickable itemIfNoPreviousPage) {
    this.itemIfNoPreviousPage = itemIfNoPreviousPage;
    return this;
  }

  public @NotNull PaginatedMenuInventoryBuilder<E> itemIfNoNextPage(final @NotNull ItemClickable itemIfNoNextPage) {
    this.itemIfNoNextPage = itemIfNoNextPage;
    return this;
  }

  @Override
  public @NotNull Inventory build() {
    final var availableSlots = this.availableSlots();
    final var paginatedMenuInventory = new PaginatedMenuInventory<>(
      this.title, this.slots, this.items, this.openAction, this.closeAction, this.canIntroduceItems,
      this.entitySlotFrom, availableSlots.size(), availableSlots, this.entities, 1,
      this.layoutLines, this.layoutItems, this.entityParser,
      this.previousPageItem, this.nextPageItem, this.itemIfNoEntities,
      this.itemIfNoPreviousPage, this.itemIfNoNextPage
    );
    final var inventory = MenuUtil.parseToInventory(paginatedMenuInventory);
    return PaginatedMenuUtil.createPage(inventory, paginatedMenuInventory);
  }

  private @NotNull List<Integer> availableSlots() {
    final var nextIncrement = 9 - this.itemsPerRow;
    final var availableSlots = new ArrayList<Integer>();
    int itemsPerRowCounter = 0;

    for (int i = this.entitySlotFrom; i < this.entitySlotTo; i++) {
      itemsPerRowCounter++;

      boolean isSkippedSlot = false;

      if (this.skippedSlots != null) {
        for (final var skippedSlot : this.skippedSlots) {
          if (i == skippedSlot) {
            isSkippedSlot = true;
            break;
          }
        }
      }

      if (!isSkippedSlot) {
        availableSlots.add(i);
      }

      if (itemsPerRowCounter == this.itemsPerRow) {
        itemsPerRowCounter = 0;
        i += nextIncrement;
      }
    }
    return availableSlots;
  }

  @Override
  protected @NotNull PaginatedMenuInventoryBuilder<E> back() {
    return this;
  }
}
