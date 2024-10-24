package team.emptyte.gui.menu.builder;

import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.menu.Menu;

import java.util.function.Predicate;

public interface MenuBuilder {
  @NotNull MenuBuilder title(final@NotNull String title);

  @NotNull MenuBuilder size(int size);

  @NotNull MenuBuilder openAction(final @NotNull Predicate<Inventory> action);

  @NotNull MenuBuilder closeAction(final @NotNull Predicate<Inventory> action);

  @NotNull MenuBuilder canIntroduceItems();

  @NotNull Menu build();
}
