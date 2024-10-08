package team.emptyte.gui.common.item.builder;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class DefaultItemBuilder extends AbstractItemBuilder<DefaultItemBuilder> {
  public DefaultItemBuilder(final @NotNull Material material, final int amount) {
    super(material, amount);
  }

  @Override
  public @NotNull DefaultItemBuilder back() {
    return this;
  }
}
