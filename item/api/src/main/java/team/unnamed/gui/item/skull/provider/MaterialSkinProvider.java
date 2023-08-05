package team.unnamed.gui.item.skull.provider;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public enum MaterialSkinProvider implements SkinProvider {
  INSTANCE;

  @Override
  public @NotNull String id() {
    return "material";
  }

  @Override
  public @Nullable ItemStack createItemStack(final @NotNull String value) {
    final var material = Material.getMaterial(value);
    if (material == null) {
      return null;
    }
    return new ItemStack(material);
  }

  @Override
  public boolean loadSkinSync(final @NotNull String value) {
    return Material.getMaterial(value) != null;
  }
}