package team.unnamed.gui.item.util;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import team.unnamed.bukkit.ServerVersion;
import team.unnamed.gui.item.builder.ItemBuilder;

import java.util.HashMap;
import java.util.Map;

public class DyeItemUtils {

  private static final Map<DyeColor, String> COLORS_BY_DYE;

  static {
    COLORS_BY_DYE = new HashMap<>();

    for (final var dyeColor : DyeColor.values()) {
      COLORS_BY_DYE.put(dyeColor, dyeColor.name());
    }
  }

  private DyeItemUtils() {
    throw new UnsupportedOperationException();
  }

  @SuppressWarnings("deprecation")
  public static ItemBuilder createBuilder(String materialKey, DyeColor dyeColor, int amount) {
    Material material;
    byte data;

    if (ServerVersion.CURRENT.getMinor() < 13) {
      material = Material.valueOf(materialKey);
      data = materialKey.equals("DYE") ?
             dyeColor.getDyeData() :
             dyeColor.getWoolData();
    } else {
      if (materialKey.equals("STAINED_CLAY")) {
        materialKey = "TERRACOTTA";
      }

      material = Material.valueOf(
        COLORS_BY_DYE.get(dyeColor) + "_" + materialKey
      );
      data = 0;
    }

    return ItemBuilder.builder(material, amount);
  }
}
