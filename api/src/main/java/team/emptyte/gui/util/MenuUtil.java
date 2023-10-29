package team.emptyte.gui.util;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class MenuUtil {

  public static void fillItemList(final @NotNull List<ItemStack> items, final int slots) {
    for (int i = 0; i < slots; i++) {
      items.add(null);
    }
  }
}
