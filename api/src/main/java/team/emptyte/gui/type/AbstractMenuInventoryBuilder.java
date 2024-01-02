package team.emptyte.gui.type;

import java.util.List;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import team.emptyte.items.parser.model.ItemModel;

public abstract class AbstractMenuInventoryBuilder<Builder extends MenuInventoryBuilder> implements MenuInventoryBuilder {
  private final Component title;

  private boolean applyPlaceholderApi;
  private boolean introduceItems;

  private List<String> layout;
  private List<ItemModel> replacements;

  public AbstractMenuInventoryBuilder(final @NotNull Component title) {
    this.title = title;
  }

  @Override
  public @NotNull MenuInventoryBuilder applyPlaceholderApi(final boolean applyPlaceholderApi) {
    this.applyPlaceholderApi = applyPlaceholderApi;
    return this.self();
  }

  @Override
  public @NotNull MenuInventoryBuilder introduceItems(final boolean introduceItems) {
    this.introduceItems = introduceItems;
    return this.self();
  }

  @Override
  public @NotNull MenuInventoryBuilder layout(final @NotNull String... layout) {
    this.layout = List.of(layout);
    return this.self();
  }

  @Override
  public @NotNull MenuInventoryBuilder layout(final @NotNull List<String> layout) {
    this.layout = layout;
    return this.self();
  }

  @Override
  public @NotNull MenuInventoryBuilder replacements(final @NotNull ItemModel... replacements) {
    this.replacements = List.of(replacements);
    return this.self();
  }

  @Override
  public @NotNull MenuInventoryBuilder replacements(final @NotNull List<ItemModel> replacements) {
    this.replacements = replacements;
    return this.self();
  }

  @Override
  public @NotNull Inventory build() {
    final var inventory = Bukkit.createInventory(null, (this.layout.size() * 9), this.title);

    int slotIndex = 0;
    for (final var layoutLine : this.layout) {
      for (final char c : layoutLine.toCharArray()) {
        for (final var replacement : this.replacements) {
          if (c != replacement.id().charAt(0)) {
            continue;
          }

          inventory.setItem(slotIndex, replacement.data().itemBuilder().build());
        }

        slotIndex++;
      }
    }

    return inventory;
  }

  protected abstract @NotNull Builder self();
}
