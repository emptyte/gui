package team.emptyte.gui.parser.codec;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.fenixteam.storage.codec.ModelDeserializer;
import org.fenixteam.storage.gson.codec.JsonReader;
import org.jetbrains.annotations.NotNull;
import team.emptyte.gui.parser.menu.MenuModel;
import team.emptyte.items.parser.codec.ItemModelCodec;

/**
 * This class represents a codec
 * for the {@link MenuModel}.
 *
 * @since 0.0.1
 */
public final class MenuModelCodec {
  private MenuModelCodec() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  /**
   * This enum represents a deserializer
   * for the {@link MenuModel}.
   *
   * @since 0.0.1
   */
  public enum ObjectDeserializer implements ModelDeserializer<MenuModel, JsonObject> {
    INSTANCE;

    @Override
    public @NotNull MenuModel deserialize(final @NotNull JsonObject serialized) {
      final var reader = JsonReader.create(serialized);
      final var id = reader.readString("id");
      if (id == null) {
        throw new NullPointerException("id");
      }
      final var applyPlaceholder = reader.readBoolean("applyPlaceholderApi");
      final var title = reader.readString("title");
      if (title == null) {
        throw new NullPointerException("title");
      }
      final var introduceItems = reader.readBoolean("introduceItems");
      final var layout = reader.readPrimitiveCollection("layout", JsonElement::getAsString, ArrayList::new);
      if (layout == null) {
        throw new NullPointerException("layout");
      }
      final var replacements = reader.readCollection("replacements", ArrayList::new, ItemModelCodec.ObjectDeserializer.INSTANCE);
      if (replacements == null) {
        throw new NullPointerException("replacements");
      }
      return new MenuModel(id, applyPlaceholder, MiniMessage.miniMessage().deserialize(title), introduceItems, layout, replacements);
    }
  }
}
