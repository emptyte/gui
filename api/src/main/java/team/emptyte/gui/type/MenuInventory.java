package team.emptyte.gui.type;

import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;
import team.emptyte.items.parser.model.ItemModel;

public abstract class MenuInventory {
  private final Component title;
  private final byte slots;

  private final boolean applyPlaceholderApi;
  private final boolean introduceItems;

  private final List<String> layouts;
  private final List<ItemModel> replacements;

  public MenuInventory(final @NotNull String title) {
    this(MiniMessage.miniMessage().deserialize(title), false, false, new ArrayList<>(), new ArrayList<>());
  }

  public MenuInventory(
    final @NotNull Component title,
    final boolean applyPlaceholderApi,
    final boolean introduceItems,
    final @NotNull List<String> layouts,
    final @NotNull List<ItemModel> replacements
  ) {
    this.title = title;
    this.applyPlaceholderApi = applyPlaceholderApi;
    this.introduceItems = introduceItems;
    this.layouts = layouts;
    if (this.layouts.isEmpty() || this.layouts.size() > 6) {
      throw new IllegalArgumentException("The layouts list must not be empty and must not exceed 6 elements");
    }
    this.replacements = replacements;
    this.slots = (byte) (this.layouts.size() * 9);
  }

  public static @NotNull MenuInventoryBuilder builder(final @NotNull Component title) {
    return new DefaultMenuInventoryBuilder(title);
  }

  public @NotNull Component title() {
    return this.title;
  }

  public byte slots() {
    return this.slots;
  }

  public boolean applyPlaceholderApi() {
    return this.applyPlaceholderApi;
  }

  public boolean introduceItems() {
    return this.introduceItems;
  }

  public @NotNull List<String> layouts() {
    return this.layouts;
  }

  public @NotNull List<ItemModel> replacements() {
    return this.replacements;
  }
}
