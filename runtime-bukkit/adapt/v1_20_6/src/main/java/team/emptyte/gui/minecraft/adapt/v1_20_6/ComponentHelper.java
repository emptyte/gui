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
package team.emptyte.gui.minecraft.adapt.v1_20_6;

import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ComponentHelper {
  private ComponentHelper() {
    throw new UnsupportedOperationException("This class cannot be instantiated");
  }

  public static @NotNull Component deserialize(final @Nullable String string) {
    if (string == null || string.isEmpty()) {
      return Component.empty();
    }
    final Component component = Component.Serializer
      .fromJson(GsonComponentSerializer.gson().serialize(MiniMessage.miniMessage().deserialize(string)), RegistryAccess.EMPTY);
    if (component == null) {
      return Component.empty();
    }
    return component;
  }

  public static @NotNull List<Component> deserialize(final @Nullable List<String> strings) {
    if (strings == null || strings.isEmpty()) {
      return new ArrayList<>();
    }
    return strings.stream()
      .map(ComponentHelper::deserialize)
      .toList();
  }
}
