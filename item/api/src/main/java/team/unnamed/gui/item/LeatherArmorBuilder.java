package team.unnamed.gui.item;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jetbrains.annotations.NotNull;

import static team.unnamed.validate.Validate.isState;

public class LeatherArmorBuilder
        extends ItemBuilderLayout<LeatherArmorBuilder> {

    private int red;
    private int green;
    private int blue;

    protected LeatherArmorBuilder(final @NotNull Material material, final int amount) {
        super(material, amount, (byte) 0);
    }

    public @NotNull LeatherArmorBuilder fromLeatherColor(final @NotNull LeatherArmorColor armorColor) {
        return fromRgb(armorColor.getRed(), armorColor.getGreen(), armorColor.getBlue());
    }

    public @NotNull LeatherArmorBuilder fromRgb(final int red, final int green, final int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        return this;
    }

    @Override
    public @NotNull ItemStack build() {
        isState(material.name().startsWith("LEATHER_"),
                "Material must be leather armor.");

        final var itemStack = super.build();
        final var armorMeta = (LeatherArmorMeta) itemStack.getItemMeta();

        armorMeta.setColor(Color.fromRGB(this.red, this.green, this.blue));
        itemStack.setItemMeta(armorMeta);

        return itemStack;
    }

    @Override
    protected @NotNull LeatherArmorBuilder back() {
        return this;
    }
}
