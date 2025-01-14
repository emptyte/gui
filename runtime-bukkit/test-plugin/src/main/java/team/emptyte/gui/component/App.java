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
package team.emptyte.gui.component;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.adapt.item.builder.ItemBuilder;
import team.emptyte.gui.annotated.Application;
import team.emptyte.gui.component.button.Close;
import team.emptyte.gui.component.button.Test;
import team.emptyte.gui.menu.item.MenuItem;

@Application(title = "<bold><gray>EMPTYTE | </bold>test")
public class App extends BukkitComponent {
  public App() {
    super(
      new Close(),
      new Test()
    );
  }

  @Override
  public @NotNull List<@NotNull MenuItem> render() {
    final List<MenuItem> items = new ArrayList<>();
    int rows = 5;
    int columns = 9;
    for (int slot = 0; slot < rows * columns; slot++) {
      if ((slot < columns ||
        slot >= (rows - 1) * columns ||
        slot % columns == 0 ||
        slot % columns == columns - 1) &&
        slot != 40
      ) {
        items.add(
          MenuItem.builder(slot)
            .itemStack(
              ItemBuilder.builder(Material.BLACK_STAINED_GLASS_PANE)
                .name(" ")
                .build()
            )
            .build()
        );
      }
    }
    return items;
  }
}
