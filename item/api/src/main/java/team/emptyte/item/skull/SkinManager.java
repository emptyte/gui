package team.emptyte.item.skull;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.emptyte.item.skull.model.SkinModel;
import team.emptyte.item.skull.provider.SkinProvider;
import team.emptyte.item.skull.provider.Base64SkinProvider;
import team.emptyte.item.skull.provider.MaterialSkinProvider;

import java.util.UUID;

public final class SkinManager {
  public static final UUID PROFILE_UUID = UUID.randomUUID();

  private SkinManager() {
    throw new UnsupportedOperationException("");
  }

  public static @Nullable ItemStack createItemStack(final @NotNull SkinModel skinModel) {
    SkinProvider skinProvider;
    switch (skinModel.providerId()) {
      case "base64" -> skinProvider = Base64SkinProvider.INSTANCE;
      case "material" -> skinProvider = MaterialSkinProvider.INSTANCE;
      default -> throw new UnsupportedOperationException("");
    }

    return skinProvider.createItemStack(skinModel.value());
  }
}
