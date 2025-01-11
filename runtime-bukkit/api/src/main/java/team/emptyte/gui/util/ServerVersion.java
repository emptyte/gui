/*
 * This file is part of storage, licensed under the MIT License
 *
 * Copyright (c) 2025 Emptyte Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package team.emptyte.gui.util;

import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

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
