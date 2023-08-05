package team.emptyte.item.skull.provider;

import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import team.emptyte.item.skull.SkinManager;

@SuppressWarnings("unused")
public enum Base64SkinProvider implements SkinProvider {
  INSTANCE;

  public static final String ID = "base64";

  @Override
  public @NotNull String id() {
    return ID;
  }

  @Override
  public @NotNull ItemStack createItemStack(final @NotNull String value) {
    final var itemStack = new ItemStack(Material.PLAYER_HEAD);
    itemStack.editMeta(SkullMeta.class, skullMeta -> {
      final var playerProfile = Bukkit.createProfile(SkinManager.PROFILE_UUID, null);
      playerProfile.setProperty(new ProfileProperty("textures", value, null));
      skullMeta.setPlayerProfile(playerProfile);
    });
    return itemStack;
  }

  @Override
  public boolean loadSkinSync(final @NotNull String value) {
    return true; // this provider shouldn't be loaded
  }
}
