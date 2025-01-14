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
package team.emptyte.gui.component.button;

import java.util.Collections;
import java.util.List;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.adapt.item.builder.ItemBuilder;
import team.emptyte.gui.component.BukkitComponent;
import team.emptyte.gui.menu.item.MenuItem;

public class Close extends BukkitComponent {
  @Override
  public @NotNull List<@NotNull MenuItem> render() {
    return Collections.singletonList(
      MenuItem.builder(40)
        .itemStack(
          ItemBuilder.builder(Material.BARRIER)
            .name("<red>Close")
            .build()
        )
        .action(((player, clickedSlot, clickType) -> {
          player.closeInventory();
          return true;
        }))
        .build()
    );
  }
}
