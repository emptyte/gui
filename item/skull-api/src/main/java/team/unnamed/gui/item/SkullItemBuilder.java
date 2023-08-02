package team.unnamed.gui.item;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import team.unnamed.bukkit.ServerVersion;
import team.unnamed.gui.item.skull.SkullSkin;

import java.lang.reflect.Field;
import java.util.UUID;

public class SkullItemBuilder
        extends ItemBuilderLayout<SkullItemBuilder> {

    private static final Field PROFILE_FIELD;
    private static final Material SKULL_MATERIAL;
    private static final byte DATA;

    static {
        try {
            Class<?> metaClass = Class.forName(
                    "org.bukkit.craftbukkit."
                            + ServerVersion.CURRENT +
                            ".inventory.CraftMetaSkull"
            );

            PROFILE_FIELD = metaClass.getDeclaredField("profile");
            SKULL_MATERIAL = Material.PLAYER_HEAD;
            DATA = 0;
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            throw new IllegalStateException("Cannot get the SkullMeta profile field!", e);
        }
    }

    private final SkullSkin skin;

    private SkullItemBuilder(final int amount, final @NotNull SkullSkin skin) {
        super(SKULL_MATERIAL, amount, DATA);
        this.skin = skin;
    }

    public static @NotNull SkullItemBuilder create(final @NotNull SkullSkin skin) {
        return new SkullItemBuilder(1, skin);
    }

    public static @NotNull SkullItemBuilder create(final int amount, final @NotNull SkullSkin skin) {
        return new SkullItemBuilder(amount, skin);
    }

    @Override
    public @NotNull ItemStack build() {
        final var itemStack = super.build();
        final var skullMeta = (SkullMeta) itemStack.getItemMeta();

        final var gameProfile = new GameProfile(UUID.randomUUID(), null);

        gameProfile.getProperties().put("textures", new Property(
                "textures",
                skin.getValue(),
                skin.getSignature()
        ));

        final boolean accessible = PROFILE_FIELD.isAccessible();
        PROFILE_FIELD.setAccessible(true);

        try {
            PROFILE_FIELD.set(skullMeta, gameProfile);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            PROFILE_FIELD.setAccessible(accessible);
        }

        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }

    @Override
    protected @NotNull SkullItemBuilder back() {
        return this;
    }
}
