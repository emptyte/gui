package team.emptyte.gui.type;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class DefaultMenuInventoryBuilder extends AbstractMenuInventoryBuilder<DefaultMenuInventoryBuilder> {
  public DefaultMenuInventoryBuilder(final @NotNull Component title) {
    super(title);
  }

  @Override
  protected @NotNull DefaultMenuInventoryBuilder self() {
    return this;
  }
}
