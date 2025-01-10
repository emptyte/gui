package team.emptyte.gui.util;

import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.Component;

import java.util.ArrayList;
import java.util.List;

public final class TreeHelper {
  private TreeHelper() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static @NotNull List<@NotNull Component<?>> flatten(final @NotNull Component<?> root) {
    final List<Component<?>> components = new ArrayList<>(root.descendents());
    components.add(root);
    return components;
  }

  public static <T extends Component<?>> @NotNull List<@NotNull T> flatten(final @NotNull Component<?> root, final @NotNull Class<T> type) {
    final List<T> components = new ArrayList<>();
    for (final Component<?> component : flatten(root)) {
      if (type.isInstance(component)) {
        components.add(type.cast(component));
      }
    }
    return components;
  }

  public static @NotNull List<@NotNull Component<?>> diff(final @NotNull Component<?> before, final @NotNull Component<?> after) {
    final List<Component<?>> diff = new ArrayList<>();
    for (final Component<?> component : before) {
      if (!after.contains(component)) {
        diff.add(component);
      }
    }
    for (final Component<?> component : before.descendents()) {
      if (!after.descendents().contains(component)) {
        diff.add(component);
      }
    }
    return diff;
  }

  public static <T extends Component<?>> @NotNull List<@NotNull T> diff(final @NotNull Component<?> before, final @NotNull Component<?> after, final @NotNull Class<T> type) {
    final List<T> diff = new ArrayList<>();
    for (final Component<?> component : diff(before, after)) {
      if (type.isInstance(component)) {
        diff.add(type.cast(component));
      }
    }
    return diff;
  }
}
