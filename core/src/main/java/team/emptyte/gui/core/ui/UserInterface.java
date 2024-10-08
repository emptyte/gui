package team.emptyte.gui.core.ui;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.core.component.Component;

public final class UserInterface {
  private final String id;

  private final String title;
  private final int size;

  private final Component root;

  private final Map<String, Component> components = new HashMap<>();

  public UserInterface(
    final @NotNull String id,
    final @NotNull String title,
    final int size,
    final @NotNull Component root
  ) {
    this.id = id;
    this.title = title;
    this.size = size;
    this.root = root;

    for (final Component component : this.children(this.root)) {
      this.components.put(component.id(), component);
    }
  }

  private @NotNull List<Component> children(final @NotNull Component component) {
    final List<Component> children = component.children();
    if (children == null) {
      return Collections.emptyList();
    }

    final List<Component> components = new ArrayList<>();
    for (final Component child : children) {
      components.add(child);
      components.addAll(this.children(child));
    }
    components.add(component);

    return components;
  }

  public @NotNull String id() {
    return this.id;
  }

  public @NotNull String title() {
    return this.title;
  }

  public int size() {
    return this.size;
  }

  public @NotNull Component root() {
    return this.root;
  }

  public @NotNull Map<String, Component> components() {
    return this.components;
  }
}
