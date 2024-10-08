package team.emptyte.gui.core.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.emptyte.gui.core.exception.ComponentException;

public class Container {
  private List<Component> children = null;

  public @Nullable List<@NotNull Component> children() {
    if (this.children == null) {
      return Collections.emptyList();
    }
    return this.children;
  }

  public void addChild(final @NotNull Component component) {
    if (this.children == null) {
      this.children = new ArrayList<>(Collections.singleton(component));
    } else {
      this.children.add(component);
    }
  }

  public void removeChild(final @NotNull Component component) {
    if (this.children == null) {
      throw new ComponentException("No children");
    }
    this.children.remove(component);
  }

  @Override
  public int hashCode() {
    return this.children != null ? this.children.hashCode() : 0;
  }

  @Override
  public @NotNull String toString() {
    return "Container{" +
      "children=" + this.children +
      '}';
  }
}
