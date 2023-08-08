package team.emptyte.gui.type.builder;

import org.jetbrains.annotations.NotNull;

public class DefaultMenuInventoryBuilder extends AbstractInventoryBuilder<DefaultMenuInventoryBuilder> {

  public DefaultMenuInventoryBuilder(final @NotNull String title) {
    super(title);
  }

  public DefaultMenuInventoryBuilder(final @NotNull String title, final int rows) {
    super(title, rows);
  }

  @Override
  protected @NotNull DefaultMenuInventoryBuilder back() {
    return this;
  }
}
