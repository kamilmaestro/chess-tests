package pl.edu.pwsztar.domain.chess;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PawnTest {

  private RulesOfGame pawn = new RulesOfGame.Pawn();

  @Tag("Pawn")
  @ParameterizedTest
  @CsvSource({
      " 4,  5,  4,  7",
      " 4,  5,  4,  6",
      " 8,  2,  8,  3",
      " 3,  2,  3,  3",
  })
  void checkCorrectMoveForPawn(int xStart, int yStart, int xStop, int yStop) {
    assertTrue(pawn.isCorrectMove(xStart, yStart, xStop, yStop));
  }

  @ParameterizedTest
  @CsvSource({
      " 8,  2,  8,  1",
      " 4,  5,  4,  8",
      " 8,  2,  7,  2",
      " 3,  3,  2,  2"
  })
  void checkIncorrectMoveForPawn(int xStart, int yStart, int xStop, int yStop) {
    assertFalse(pawn.isCorrectMove(xStart, yStart, xStop, yStop));
  }
}
