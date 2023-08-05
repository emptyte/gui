package team.emptyte.item.skull.provider;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public interface SkinProvider {
  @NotNull String id();

  @Nullable ItemStack createItemStack(final @NotNull String value);

  boolean loadSkinSync(final @NotNull String value) throws Exception;
}