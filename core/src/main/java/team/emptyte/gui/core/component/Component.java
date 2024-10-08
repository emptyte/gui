package team.emptyte.gui.core.component;

import com.google.common.base.Objects;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.emptyte.gui.common.item.ItemClickable;
import team.emptyte.gui.core.exception.ComponentException;

public abstract class Component extends Container {
  private final String id;

  /**
   * If the parent is null, then the
   * component is a root component.
   */
  private Container parent = null;

  private @Nullable Map<String, Object> state = null;

  public Component(final @NotNull String id) {
    this.id = id;
  }

  public @NotNull String id() {
    return this.id;
  }

  public @Nullable Container parent() {
    return this.parent;
  }

  public void parent(final @NotNull Container parent) {
    this.parent = parent;
  }

  public void useState(final @NotNull String key, final @NotNull Object value) {
    if (this.state == null) {
      this.state = new HashMap<>();
    }
    this.state.put(key, value);
  }

  public void setState(final @NotNull String key, final @NotNull Object value) {
    if (this.state == null) {
      throw new ComponentException("No initial state");
    }
    if (!this.state.containsKey(key)) {
      throw new ComponentException("No existing state");
    }

    this.state.put(key, value);
  }

  public @Nullable Object state(final @NotNull String key) {
    if (this.state == null) {
      throw new ComponentException("No initial state");
    }

    return this.state.get(key);
  }

  public abstract @NotNull List<@NotNull ItemClickable> render();

  @Override
  public int hashCode() {
    return Objects.hashCode(this.id, this.parent, this.state);
  }

  @Override
  public @NotNull String toString() {
    return "Component{" +
      "id='" + this.id + '\'' +
      ", parent=" + this.parent +
      ", state=" + this.state +
      '}';
  }
}
