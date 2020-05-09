package pl.edu.pwsztar.domain.chess;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QueenTest {

  private RulesOfGame queen = new RulesOfGame.Queen();

  @Tag("Queen")
  @ParameterizedTest
  @CsvSource({
      " 4,  4,  7,  4",
      " 4,  4,  4,  1",
      " 4,  4,  2,  2",
      " 4,  4,  7,  7",
  })
  void checkCorrectMoveForQueen(int xStart, int yStart, int xStop, int yStop) {
    assertTrue(queen.isCorrectMove(xStart, yStart, xStop, yStop));
  }

  @ParameterizedTest
  @CsvSource({
      " 4,  4,  2,  5",
      " 4,  4,  2,  1",
      " 3,  3,  8,  4",
      " 4,  4,  6,  1"
  })
  void checkIncorrectMoveForQueen(int xStart, int yStart, int xStop, int yStop) {
    assertFalse(queen.isCorrectMove(xStart, yStart, xStop, yStop));
  }
}
