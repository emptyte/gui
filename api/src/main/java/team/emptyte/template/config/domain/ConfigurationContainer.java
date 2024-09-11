/*
 * This file is part of storage, licensed under the MIT License
 *
 * Copyright (c) 2024 EmptyteTeam
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
package team.emptyte.template.config.domain;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.loader.ConfigurationLoader;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public final class ConfigurationContainer<ConfigType extends ConfigurationFile> {
  private ConfigType configuration;
  private final ConfigurationLoader<?> loader;
  private final Class<ConfigType> clazz;

  public ConfigurationContainer(final @NotNull ConfigurationLoader<?> loader, final @NotNull Class<ConfigType> clazz) {
    this.loader = loader;
    this.clazz = clazz;

    try {
      this.configuration = this.load();
    } catch (final Throwable t) {
      throw new RuntimeException(t);
    }
  }

  public @NotNull ConfigType configuration() {
    return this.configuration;
  }

  private @NotNull ConfigType load() throws ConfigurateException {
    final ConfigurationNode node = this.loader.load();
    final ConfigType config = node.get(this.clazz);
    if (config == null) {
      throw new ConfigurateException(node, "Failed to load configuration");
    }
    node.set(this.clazz, config);
    this.loader.save(node);
    return config;
  }

  public @NotNull CompletableFuture<Void> reload() {
    return CompletableFuture.runAsync(() -> {
      try {
        this.configuration = this.load();
      } catch (final Exception e) {
        throw new CompletionException(e);
      }
    });
  }

  public @NotNull CompletableFuture<Void> save() {
    return CompletableFuture.runAsync(() -> {
      try {
        final ConfigurationNode node = this.loader.createNode();
        node.set(this.clazz, this.configuration);
        this.loader.save(node);
      } catch (final Exception e) {
        throw new CompletionException(e);
      }
    });
  }
}
