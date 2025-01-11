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
package team.emptyte.gui.adapt;

import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.util.ServerVersion;

public final class AdaptionModuloFactory {
  private AdaptionModuloFactory() {
    throw new UnsupportedOperationException("This class cannot be instantiated");
  }

  public static @NotNull AdaptionModule create() {
    final String className = "team.emptyte.gui.AdaptionModule" + ServerVersion.CURRENT;
    try {
      final Class<?> clazz = Class.forName(className);
      final Object module = clazz.getConstructor().newInstance();
      if (!(module instanceof AdaptionModule)) {
        throw new IllegalStateException("Invalid adaption module: '"
          + className + "'. It doesn't implement " + AdaptionModule.class);
      }
      return (AdaptionModule) module;
    } catch (final ClassNotFoundException e) {
      throw new IllegalStateException("Adaption module not found: '" + className + '.');
    } catch (final ReflectiveOperationException e) {
      throw new IllegalStateException("Failed to instantiate adaption module", e);
    }
  }
}
