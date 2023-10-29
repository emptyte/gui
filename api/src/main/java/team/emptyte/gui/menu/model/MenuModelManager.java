package team.emptyte.gui.menu.model;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.fenixteam.storage.repository.WithFallbackModelRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Singleton
public final class MenuModelManager {
  private final WithFallbackModelRepository<MenuModel> repository;

  public @Inject MenuModelManager(final @NotNull WithFallbackModelRepository<MenuModel> repository) {
    this.repository = repository;
  }

  /**
   * Load the menu model from the cache.
   *
   * @param id The id of the menu.
   * @return The menu model.
   */
  public @Nullable MenuModel loadAndSaveCache(final @NotNull String id) {
    return this.repository.findAndSaveToFallbackSync(id);
  }

  /**
   * Find the menu model from the cache.
   *
   * @param id The id of the menu.
   * @return The menu model.
   */
  public @Nullable MenuModel find(final @NotNull String id) {
    return this.repository.findInBothSync(id);
  }

  public @NotNull Inventory createInventory(final @NotNull String id) {
    final var menuModel = this.find(id);
    if (menuModel == null) {
      throw new NullPointerException("Menu model is null");
    }

    final var menuInventory = menuModel.menuInventory();
    final var inventory = Bukkit.createInventory(null, menuInventory.slots(), menuInventory.title());

    return inventory;
  }
}
