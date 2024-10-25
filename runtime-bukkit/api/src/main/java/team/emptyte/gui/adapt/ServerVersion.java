/*
 * This file is part of storage, licensed under the MIT License
 *
 * Copyright (c) 2024 Emptyte Team
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
package team.emptyte.gui.adapt;

import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public record ServerVersion(int major, int minor, int releases) {
  public static final ServerVersion CURRENT = ServerVersion.getVersionOfString(Bukkit.getMinecraftVersion());

  @Override
  public @NotNull String toString() {
    if (this.releases == -1) {
      return this.major + "_" + this.minor;
    }

    return this.major + "_" + this.minor + "_" + this.releases;
  }

  private static @NotNull ServerVersion getVersionOfString(final @NotNull String version) {
    final String[] parts = version.split(Pattern.quote("."));

    if (parts.length != 2 && parts.length != 3) {
      throw new IllegalStateException("Failed to determine minecraft version!");
    }

    return new ServerVersion(
      Integer.parseInt(parts[0]),
      Integer.parseInt(parts[1]),
      parts.length == 3 ? Integer.parseInt(parts[2]) : -1
    );
  }
}
