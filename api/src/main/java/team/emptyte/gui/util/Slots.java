package team.emptyte.gui.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public final class Slots {

  private Slots() {
    // the class shouldn't be instantiated
    throw new UnsupportedOperationException();
  }

  public static @NotNull List<Integer> borderSlots(final int rows) {
    if (rows < 3) {
      return Collections.emptyList();
    }

    final var slots = new ArrayList<Integer>();
    final int totalSlots = rows * 9;

    slots.addAll(Slots.rowSlots(1));
    slots.addAll(Slots.rowSlots(rows));
    slots.addAll(Slots.columnSlots(1, totalSlots));
    slots.addAll(Slots.columnSlots(9, totalSlots));

    return slots;
  }

  public static @NotNull List<Integer> rowSlots(final int row) {
    final var slots = new ArrayList<Integer>();
    final int indexStart = row == 1 ?
                     0 :
                     (row - 1) * 9;

    for (int slot = indexStart; slot < indexStart + 9; slot++) {
      slots.add(slot);
    }

    return slots;
  }

  public static @NotNull List<Integer> columnSlots(final int column, final int slots) {
    final var columnSlots = new ArrayList<Integer>();
    final int indexStart = column - 1;
    final int indexEnd = (slots - 9) + column;

    for (int slot = indexStart; slot <= indexEnd; slot += 9) {
      columnSlots.add(slot);
    }

    return columnSlots;
  }
}
