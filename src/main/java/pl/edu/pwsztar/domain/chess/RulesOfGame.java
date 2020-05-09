package pl.edu.pwsztar.domain.chess;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public interface RulesOfGame {

    /**
     * Metoda zwraca true, tylko gdy przejscie z polozenia
     * (xStart, yStart) na (xEnd, yEnd) w jednym ruchu jest zgodne
     * z zasadami gry w szachy
     */
    boolean isCorrectMove(int xStart, int yStart, int xEnd, int yEnd);

    @Component
    @Qualifier("Bishop")
    class Bishop extends BasicMoveFeatures implements RulesOfGame {

        @Override
        public boolean isCorrectMove(int xStart, int yStart, int xEnd, int yEnd) {
            if(super.isMovedHorizontally(yStart, yEnd) || super.isMovedVertically(xStart, xEnd)) {
                return false;
            }

            return super.isMovedDiagonally(xStart, yStart, xEnd, yEnd);
        }
    }

    @Component
    @Qualifier("King")
    final class King extends BasicMoveFeatures implements RulesOfGame {

        @Override
        public boolean isCorrectMove(int xStart, int yStart, int xEnd, int yEnd) {
            return isMovedOneSquare(xStart, yStart, xEnd, yEnd);
        }

        private boolean isMovedOneSquare(int xStart, int yStart, int xEnd, int yEnd) {
            return isMovedOneSquareDiagonally(xStart, yStart, xEnd, yEnd) ||
                isMovedOneSquareHorizontally(xStart, yStart, xEnd, yEnd) ||
                isMovedOneSquareVertically(xStart, yStart, xEnd, yEnd);
        }

        private boolean isMovedOneSquareDiagonally(int xStart, int yStart, int xEnd, int yEnd) {
            return Math.abs(xStart - xEnd) == 1 && Math.abs(yStart - yEnd) == 1;
        }

        private boolean isMovedOneSquareHorizontally(int xStart, int yStart, int xEnd, int yEnd) {
            return super.isMovedHorizontally(yStart, yEnd) && Math.abs(xStart - xEnd) == 1;
        }

        private boolean isMovedOneSquareVertically(int xStart, int yStart, int xEnd, int yEnd) {
            return super.isMovedVertically(xStart, xEnd) && Math.abs(yStart - yEnd) == 1;
        }

    }

    @Component
    @Qualifier("Knight")
    final class Knight extends BasicMoveFeatures implements RulesOfGame {

        @Override
        public boolean isCorrectMove(int xStart, int yStart, int xEnd, int yEnd) {
            if (super.isMovedHorizontally(yStart, yEnd) || super.isMovedVertically(xStart, xEnd)) {
                return false;
            }

            return isMovedInLShape(xStart, yStart, xEnd, yEnd);
        }

        private boolean isMovedInLShape(int xStart, int yStart, int xEnd, int yEnd) {
            return (Math.abs(xStart - xEnd) == 2 && Math.abs(yStart - yEnd) == 1) ||
                (Math.abs(xStart - xEnd) == 1 && Math.abs(yStart - yEnd) == 2);
        }
    }

    @Component
    @Qualifier("Pawn")
    final class Pawn extends BasicMoveFeatures implements RulesOfGame {

        boolean firstMove = false;

        @Override
        public boolean isCorrectMove(int xStart, int yStart, int xEnd, int yEnd) {
            if (isMovedBackwards(yStart, yEnd)) {
                return false;
            }
            final boolean wasProperlyMoved = isMovedVerticallyMaxTwoSquares(xStart, yStart, xEnd, yEnd);
            firstMove = true;

            return wasProperlyMoved;
        }

        private boolean isMovedBackwards(int yStart, int yEnd) {
            return yEnd < yStart;
        }

        private boolean isMovedVerticallyMaxTwoSquares(int xStart, int yStart, int xEnd, int yEnd) {
            return super.isMovedVertically(xStart, xEnd) &&
                (Math.abs(yStart - yEnd) == 1 || isMovedFirstlyTwoSquares(yStart, yEnd));
        }

        private boolean isMovedFirstlyTwoSquares(int yStart, int yEnd) {
            return Math.abs(yStart - yEnd) == 2 && !firstMove;
        }
    }

    @Component
    @Qualifier("Queen")
    final class Queen extends BasicMoveFeatures implements RulesOfGame {

        @Override
        public boolean isCorrectMove(int xStart, int yStart, int xEnd, int yEnd) {
            return super.isMovedDiagonally(xStart, yStart, xEnd, yEnd) ||
                super.isMovedVertically(xStart, xEnd) ||
                super.isMovedHorizontally(yStart, yEnd);
        }
    }

    @Component
    @Qualifier("Rook")
    final class Rook extends BasicMoveFeatures implements RulesOfGame {

        @Override
        public boolean isCorrectMove(int xStart, int yStart, int xEnd, int yEnd) {
            return super.isMovedVertically(xStart, xEnd) || super.isMovedHorizontally(yStart, yEnd);
        }
    }
}
