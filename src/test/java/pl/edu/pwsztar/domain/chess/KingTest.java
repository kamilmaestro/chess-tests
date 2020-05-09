package pl.edu.pwsztar.domain.chess;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KingTest {

  private RulesOfGame king = new RulesOfGame.King();

  @Tag("King")
  @ParameterizedTest
  @CsvSource({
      " 4,  4,  5,  5",
      " 5,  4,  5,  5",
      " 5,  4,  6,  4",
      " 2,  2,  1,  1",
  })
  void checkCorrectMoveForKing(int xStart, int yStart, int xStop, int yStop) {
    assertTrue(king.isCorrectMove(xStart, yStart, xStop, yStop));
  }

  @ParameterizedTest
  @CsvSource({
      " 4,  4,  2,  6",
      " 2,  5,  1,  2",
      " 3,  2,  7,  4",
      " 5,  4,  7,  4"
  })
  void checkIncorrectMoveForKing(int xStart, int yStart, int xStop, int yStop) {
    assertFalse(king.isCorrectMove(xStart, yStart, xStop, yStop));
  }
}
