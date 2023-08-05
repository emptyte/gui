package team.unnamed.gui.item.builder;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class DefaultItemBuilder
        extends ItemBuilderLayout<DefaultItemBuilder> {

    protected DefaultItemBuilder(
            Material material, int amount
    ) {
        super(material, amount);
    }

    @Override
    protected @NotNull DefaultItemBuilder back() {
        return this;
    }
}
