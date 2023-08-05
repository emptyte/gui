package team.emptyte.item.skull.model;

import com.google.gson.JsonObject;
import es.revengenetwork.storage.codec.ModelDeserializer;
import es.revengenetwork.storage.codec.ModelSerializer;
import es.revengenetwork.storage.gson.codec.JsonReader;
import es.revengenetwork.storage.gson.codec.JsonWriter;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public final class SkinModelCodec {
  private SkinModelCodec() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public enum Serializer implements ModelSerializer<SkinModel, JsonObject> {
    INSTANCE;

    @Override
    public @NotNull JsonObject serialize(final @NotNull SkinModel model) {
      return JsonWriter.create()
               .writeString("providerId", model.providerId())
               .writeString("value", model.value())
               .end();
    }
  }

  public enum Deserializer implements ModelDeserializer<SkinModel, JsonObject> {
    INSTANCE;

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull SkinModel deserialize(final @NotNull JsonObject serialized) {
      final var reader = JsonReader.create(serialized);
      return new SkinModel(reader.readString("providerId"), reader.readString("value"));
    }
  }
}
