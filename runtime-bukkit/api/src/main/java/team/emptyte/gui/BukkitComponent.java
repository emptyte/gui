package team.emptyte.gui;

import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.item.ItemClickable;

import java.util.List;

public abstract class BukkitComponent extends Component<ItemClickable> {
  public BukkitComponent() {
    super();
  }

  public BukkitComponent(final @NotNull BukkitComponent... children) {
    super(children);
  }

  @Override
  public abstract @NotNull List<@NotNull ItemClickable> render();
}
