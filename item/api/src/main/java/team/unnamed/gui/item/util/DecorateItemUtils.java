package team.unnamed.gui.item.util;

import org.bukkit.DyeColor;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.unnamed.gui.item.builder.ItemBuilder;

public final class DecorateItemUtils {

    private DecorateItemUtils() {
        throw new UnsupportedOperationException();
    }

    public static @NotNull ItemBuilder stainedPaneBuilder(final @NotNull DyeColor dyeColor) {
        return ItemBuilder.dyeBuilder("STAINED_GLASS_PANE", dyeColor)
                .name(" ");
    }

    public static @NotNull ItemStack stainedPane(final @NotNull DyeColor dyeColor) {
        return DecorateItemUtils.stainedPaneBuilder(dyeColor).build();
    }

}
