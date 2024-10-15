package team.emptyte.gui;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Tree {
  private final List<Component> nodes = new ArrayList<>();

  public Tree() {
  }

  public @NotNull List<@NotNull Component> nodes() {
    return this.nodes;
  }

  public void add(final @NotNull Component node) {
    this.nodes.add(node);
  }

  public void remove(final @NotNull Component node) {
    this.nodes.remove(node);
  }

  public @NotNull List<Component> descendants() {
    final List<Component> descendants = new ArrayList<>();
    for (final Component node : this.nodes) {
      descendants.add(node);
      descendants.addAll(node.descendants());
    }
    return descendants;
  }
}
