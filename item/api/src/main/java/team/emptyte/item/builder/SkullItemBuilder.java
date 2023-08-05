package team.emptyte.item.builder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.emptyte.item.skull.model.SkinModel;
import team.emptyte.item.skull.provider.Base64SkinProvider;
import team.emptyte.item.skull.provider.SkinProvider;
import team.emptyte.item.skull.provider.MaterialSkinProvider;

public class SkullItemBuilder extends ItemBuilderLayout<SkullItemBuilder> {
  private final SkinModel skinModel;

  protected SkullItemBuilder(final @NotNull SkinModel skinModel, final int amount) {
    super(Material.PLAYER_HEAD, amount);

    this.skinModel = skinModel;
  }

  @Override
  protected @NotNull SkullItemBuilder back() {
    return this;
  }

  @Override
  public @NotNull ItemStack build() {
    SkinProvider provider = null;
    switch (this.skinModel.providerId()) {
      case "base64" -> provider = Base64SkinProvider.INSTANCE;
      case "material" -> provider = MaterialSkinProvider.INSTANCE;
    }

    if (provider == null) {
      throw new IllegalArgumentException("");
    }

    final var itemStack = provider.createItemStack(this.skinModel.value());
    if (itemStack == null) {
      throw new IllegalArgumentException("");
    }

    return itemStack;
  }
}
