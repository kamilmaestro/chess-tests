package pl.edu.pwsztar.domain.chess;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
abstract class BasicMoveFeatures {

  BasicMoveFeatures() {
  }

  boolean isMovedDiagonally(int xStart, int yStart, int xEnd, int yEnd) {
    return Math.abs(xEnd - xStart) == Math.abs(yEnd - yStart);
  }

  boolean isMovedHorizontally(int yStart, int yEnd) {
    return yStart == yEnd;
  }

  boolean isMovedVertically(int xStart, int xEnd) {
    return xStart == xEnd;
  }
}
