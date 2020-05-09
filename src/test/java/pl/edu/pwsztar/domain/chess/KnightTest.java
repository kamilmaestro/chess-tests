package pl.edu.pwsztar.domain.chess;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KnightTest {

  private RulesOfGame knight = new RulesOfGame.Knight();

  @Tag("Knight")
  @ParameterizedTest
  @CsvSource({
      " 4,  4,  2,  5",
      " 4,  4,  5,  6",
      " 3,  5,  2,  7",
      " 5,  6,  6,  8",
  })
  void checkCorrectMoveForKnight(int xStart, int yStart, int xStop, int yStop) {
    assertTrue(knight.isCorrectMove(xStart, yStart, xStop, yStop));
  }

  @ParameterizedTest
  @CsvSource({
      " 4,  4,  2,  6",
      " 2,  2,  1,  2",
      " 3,  2,  3,  4",
      " 4,  4,  5,  3"
  })
  void checkIncorrectMoveForKnight(int xStart, int yStart, int xStop, int yStop) {
    assertFalse(knight.isCorrectMove(xStart, yStart, xStop, yStop));
  }
}
