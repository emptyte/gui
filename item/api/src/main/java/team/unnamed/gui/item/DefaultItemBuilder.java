package team.unnamed.gui.item;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class DefaultItemBuilder
        extends ItemBuilderLayout<DefaultItemBuilder> {

    protected DefaultItemBuilder(
            Material material, int amount,
            byte data
    ) {
        super(material, amount, data);
    }

    @Override
    protected @NotNull DefaultItemBuilder back() {
        return this;
    }
}
