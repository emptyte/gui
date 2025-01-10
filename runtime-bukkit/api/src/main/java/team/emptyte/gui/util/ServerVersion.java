package team.emptyte.gui.util;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public record ServerVersion(int major, int minor, int releases) {
  public static String VERSION_STRING = Bukkit.getMinecraftVersion();
  public static ServerVersion CURRENT = ServerVersion.fromString(VERSION_STRING);

  @Override
  public @NotNull String toString() {
    if (minor == 21 ||releases == -1) {
      return major + "_" + minor;
    }
    return major + "_" + minor + "_" + releases;
  }

  private static @NotNull ServerVersion fromString(final @NotNull String version) {
    final String[] parts = version.split(Pattern.quote("."));
    return switch (parts.length) {
      case 2 -> {
        final int major = Integer.parseInt(parts[0]);
        final int minor = Integer.parseInt(parts[1]);
        yield new ServerVersion(major, minor, -1);
      }
      case 3 -> {
        final int major = Integer.parseInt(parts[0]);
        final int minor = Integer.parseInt(parts[1]);
        final int releases = Integer.parseInt(parts[2]);
        yield new ServerVersion(major, minor, releases);
      }
      default -> throw new IllegalArgumentException("Invalid version format: " + version);
    };
  }
}
