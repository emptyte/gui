package team.emptyte.gui.exception;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;

public class NoSuchStateComponentException extends ComponentException {
  @Serial private static final long serialVersionUID = 1L;

  public NoSuchStateComponentException(final @NotNull String message) {
    super(message);
  }

  public NoSuchStateComponentException(final @NotNull String message, final @NotNull Throwable cause) {
    super(message, cause);
  }
}
