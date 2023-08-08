package team.emptyte.gui.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.adapt.MenuInventoryWrapper;
import team.emptyte.gui.item.ItemClickable;
import team.emptyte.gui.type.MenuInventory;
import team.unnamed.bukkit.ServerVersion;

public final class MenuUtil {

  private static final Constructor<?> WRAPPER_CONSTRUCTOR;

  static {
    try {
      WRAPPER_CONSTRUCTOR = Class.forName(
          "team.emptyte.gui.adapt" + ServerVersion.CURRENT
          + ".MenuInventoryWrapperImpl"
        )
                              .getConstructor(InventoryHolder.class, MenuInventory.class);
    } catch (ClassNotFoundException | NoSuchMethodException e) {
      throw new ExceptionInInitializerError("Your server version isn't supported for ungui.");
    }
  }

  private MenuUtil() {
    // the class shouldn't be instantiated
    throw new UnsupportedOperationException();
  }

  public static void fillItemList(final @NotNull List<ItemClickable> items, final int slots) {
    for (int i = 0; i < slots; i++) {
      items.add(null);
    }
  }

  public static @NotNull Inventory parseToInventory(final @NotNull MenuInventory menuInventory) {
    try {
      final var wrapper = (MenuInventoryWrapper) WRAPPER_CONSTRUCTOR.newInstance(null, menuInventory);
      return wrapper.rawInventory();
    } catch (final InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new ExceptionInInitializerError(
        "An error has occurred while creating menu "
        + menuInventory.title());
    }
  }

  public static boolean isCustomMenu(final @NotNull Inventory inventory) {
    final var holder = inventory.getHolder();
    return holder instanceof MenuInventoryWrapper
           || inventory instanceof MenuInventoryWrapper;
  }

  public static @NotNull MenuInventoryWrapper wrapper(final @NotNull Inventory inventory) {
    final var holder = inventory.getHolder();

    return holder == null ?
           (MenuInventoryWrapper) inventory :
           (MenuInventoryWrapper) holder;
  }
}
