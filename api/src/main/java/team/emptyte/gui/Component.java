package team.emptyte.gui;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.emptyte.gui.exception.NoSuchStateComponentException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Component extends Tree {
  /**
   * If this parent is null, then this component is a root component.
   */
  protected Component parent = null;

  private Map<String, Object> states;

  public Component(final @NotNull Component... children) {
    for (final Component child : children) {
      child.parent = this;
      this.add(child);
    }

    this.states = null;
  }

  public void useState(final @NotNull String key, final @Nullable Object value) {
    if (this.states == null) {
      this.states = new HashMap<>();
    }
    this.states.put(key, value);
  }

  public void setState(final @NotNull String key, final @Nullable Object value) {
    if (this.states == null || !this.states.containsKey(key)) {
      throw new NoSuchStateComponentException("State not found");
    }
    this.states.put(key, value);
  }

  public @Nullable Object state(final @NotNull String key) {
    if (this.states == null || this.states.isEmpty()) {
      throw new NoSuchStateComponentException("No state found");
    }
    return this.states.get(key);
  }

  public abstract <E> @NotNull List<@NotNull E> render();
}
