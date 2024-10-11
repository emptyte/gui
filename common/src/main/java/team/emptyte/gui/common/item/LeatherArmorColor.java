/*
 * This file is part of storage, licensed under the MIT License
 *
 * Copyright (c) 2024 Emptyte Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package team.emptyte.gui.common.item;

public enum LeatherArmorColor {
  DARK_BLUE(0, 0, 255),
  LIGHT_BLUE(51, 153, 255),
  LIGHT_RED(255, 0, 0),
  DARK_RED(152, 0, 0),
  CYAN(102, 255, 255),
  YELLOW(255, 255, 0),
  ORANGE(255, 128, 0),
  LIME(0, 255, 0),
  GREEN(0, 204, 0),
  PURPLE(76, 0, 153),
  PINK(255, 153, 255),
  BLACK(0, 0, 0),
  WHITE(255, 255, 255),
  DARK_GRAY(128, 128, 128),
  LIGHT_GRAY(192, 192, 192);

  private final int red;
  private final int green;
  private final int blue;

  LeatherArmorColor(final int red, final int green, final int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public int getRed() {
    return red;
  }

  public int getGreen() {
    return green;
  }

  public int getBlue() {
    return blue;
  }

}
