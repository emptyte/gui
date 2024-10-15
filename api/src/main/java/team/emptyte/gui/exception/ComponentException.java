package team.emptyte.gui.exception;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;

public class ComponentException extends RuntimeException {
  @Serial private static final long serialVersionUID = 1L;

  public ComponentException(final @NotNull String message) {
    super(message);
  }

  public ComponentException(final @NotNull String message, final @NotNull Throwable cause) {
    super(message, cause);
  }
}
