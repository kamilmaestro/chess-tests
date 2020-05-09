package pl.edu.pwsztar.domain.chess;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RookTest {

  private RulesOfGame rook = new RulesOfGame.Rook();

  @Tag("Rook")
  @ParameterizedTest
  @CsvSource({
      " 4,  5,  2,  5",
      " 5,  4,  5,  1",
      " 5,  4,  7,  4",
      " 2,  2,  1,  2",
  })
  void checkCorrectMoveForRook(int xStart, int yStart, int xStop, int yStop) {
    assertTrue(rook.isCorrectMove(xStart, yStart, xStop, yStop));
  }

  @ParameterizedTest
  @CsvSource({
      " 4,  4,  2,  6",
      " 5,  4,  4,  3",
      " 3,  2,  2,  3",
      " 7,  7,  6,  6"
  })
  void checkIncorrectMoveForRook(int xStart, int yStart, int xStop, int yStop) {
    assertFalse(rook.isCorrectMove(xStart, yStart, xStop, yStop));
  }
}
