package team.emptyte.gui.menu.builder;

import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.BukkitComponent;
import team.emptyte.gui.menu.Menu;

import java.util.function.Predicate;

public final class MenuBuilderImpl implements MenuBuilder {
  private String title;
  private int size;
  private final BukkitComponent component;
  private Predicate<Inventory> openAction;
  private Predicate<Inventory> closeAction;
  private boolean canIntroduceItems;

  public MenuBuilderImpl(final @NotNull BukkitComponent component) {
    this.component = component;
  }

  @Override
  public @NotNull MenuBuilder title(final @NotNull String title) {
    this.title = title;
    return this;
  }

  @Override
  public @NotNull MenuBuilder size(final int size) {
    this.size = size;
    return this;
  }

  @Override
  public @NotNull MenuBuilder openAction(final @NotNull Predicate<Inventory> action) {
    this.openAction = action;
    return this;
  }

  @Override
  public @NotNull MenuBuilder closeAction(final @NotNull Predicate<Inventory> action) {
    this.closeAction = action;
    return this;
  }

  @Override
  public @NotNull MenuBuilder canIntroduceItems() {
    this.canIntroduceItems = true;
    return this;
  }

  @Override
  public @NotNull Menu build() {
    return new Menu(this.title, this.size, this.component, this.openAction, this.closeAction, this.canIntroduceItems);
  }
}
