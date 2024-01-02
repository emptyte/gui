package team.emptyte.gui.parser.menu;

import java.util.concurrent.CompletableFuture;
import org.bukkit.inventory.Inventory;
import org.fenixteam.storage.repository.WithFallbackModelRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import team.emptyte.gui.parser.creator.MenuCreator;

/**
 * This class represents a manager
 * for the creation of menus.
 */
public final class MenuModelManager {
  private final Logger logger;
  private final WithFallbackModelRepository<MenuModel> repository;

  /**
   * This constructor allows us to create a manager
   * for the creation of menus.
   *
   * @param logger     The logger.
   * @param repository The repository.
   * @since 0.0.1
   */
  public MenuModelManager(final @NotNull Logger logger, final @NotNull WithFallbackModelRepository<MenuModel> repository) {
    this.logger = logger;
    this.repository = repository;
  }

  /**
   * This method allows us to load a {@link MenuModel}
   * from the cache.
   *
   * @param id The id of the menu.
   * @return The {@link MenuModel}.
   * @since 0.0.1
   */
  public @NotNull CompletableFuture<MenuModel> load(final @NotNull String id) {
    return this.repository.findAndSaveToFallback(id)
      .thenApply(model -> {
        this.logger.info("Loaded menu model with id {}", id);
        return model;
      })
      .exceptionally(throwable -> {
        this.logger.error("Failed to load menu model with id {}", id, throwable);
        return null;
      });
  }

  /**
   * This method allows us to create an {@link Inventory}
   * from a {@link MenuModel}.
   *
   * @param id The id of the menu.
   * @return The {@link Inventory}.
   * @since 0.0.1
   */
  public @NotNull Inventory createInventory(final @NotNull String id) {
    final var model = this.repository.findInBothSync(id);
    if (model == null) {
      throw new IllegalArgumentException("The menu with id " + id + " does not exist");
    }

    return MenuCreator.createInventory(model);
  }
}
