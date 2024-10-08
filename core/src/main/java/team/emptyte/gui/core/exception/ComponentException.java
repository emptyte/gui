package team.emptyte.gui.core.exception;

import org.jetbrains.annotations.NotNull;

public class ComponentException extends RuntimeException {
  public ComponentException(final @NotNull String message) {
    super(message);
  }
}
