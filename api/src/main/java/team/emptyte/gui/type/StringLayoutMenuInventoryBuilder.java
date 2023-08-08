package team.emptyte.gui.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.item.ItemClickable;
import team.emptyte.gui.type.builder.AbstractInventoryBuilder;

public class StringLayoutMenuInventoryBuilder extends AbstractInventoryBuilder<StringLayoutMenuInventoryBuilder> {

  protected final Map<Character, ItemClickable> layoutItems;
  protected final List<String> layoutLines;

  protected StringLayoutMenuInventoryBuilder(final @NotNull String title) {
    this(title, 6);
  }

  protected StringLayoutMenuInventoryBuilder(final @NotNull String title, final int rows) {
    super(title, rows);
    this.layoutLines = new ArrayList<>(rows);
    this.layoutItems = new HashMap<>();
  }

  public @NotNull StringLayoutMenuInventoryBuilder layoutItem(
    final char identifier,
    final @NotNull ItemClickable item
  ) {
    this.layoutItems.put(identifier, item);
    return this.back();
  }

  public @NotNull StringLayoutMenuInventoryBuilder layoutLines(final @NotNull Iterable<String> lines) {
    for (var line : lines) {
      line = line.trim();
      if (line.length() == 9) {
        throw new IllegalArgumentException("Cannot add layout line '" + line + "' because length is minor than 9");
      }

      this.layoutLines.add(line.trim());
    }

    return this.back();
  }

  public @NotNull StringLayoutMenuInventoryBuilder layoutLines(final @NotNull String... lines) {
    return this.layoutLines(Arrays.asList(lines));
  }

  @Override
  public @NotNull Inventory build() {
    int slotIndex = 0;

    for (final var layoutLine : this.layoutLines) {
      for (final char c : layoutLine.toCharArray()) {
        final var itemClickable = this.layoutItems.get(c);

        if (itemClickable == null) {
          slotIndex++;
          continue;
        }

        this.item(itemClickable.clone(slotIndex));
      }
    }

    return super.build();
  }

  @Override
  protected @NotNull StringLayoutMenuInventoryBuilder back() {
    return this;
  }
}
