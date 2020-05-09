package pl.edu.pwsztar.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.domain.chess.RulesOfGame;
import pl.edu.pwsztar.domain.dto.FigureMoveDto;
import pl.edu.pwsztar.domain.dto.MoveDetailsDto;
import pl.edu.pwsztar.domain.enums.FigureType;
import pl.edu.pwsztar.exception.UnsupportedMove;
import pl.edu.pwsztar.service.ChessService;

@Service
public class ChessServiceImpl implements ChessService {

    private final RulesOfGame bishop;
    private final RulesOfGame king;
    private final RulesOfGame knight;
    private final RulesOfGame pawn;
    private final RulesOfGame queen;
    private final RulesOfGame rook;

    @Autowired
    public ChessServiceImpl(@Qualifier("Bishop") RulesOfGame bishop,
                            @Qualifier("King") RulesOfGame king,
                            @Qualifier("Knight") RulesOfGame knight,
                            @Qualifier("Pawn") RulesOfGame pawn,
                            @Qualifier("Queen") RulesOfGame queen,
                            @Qualifier("Rook") RulesOfGame rook) {
        this.bishop = bishop;
        this.king = king;
        this.knight = knight;
        this.pawn = pawn;
        this.queen = queen;
        this.rook = rook;
    }

    @Override
    public boolean isCorrectMove(FigureMoveDto figureMoveDto) {
        final MoveDetailsDto start;
        final MoveDetailsDto destination;
        final MoveDetailsProvider moveDetailsProvider = MoveDetailsProvider.create(figureMoveDto);
        try {
            start = moveDetailsProvider.getStartDetails();
            destination = moveDetailsProvider.getDestinationDetails();
        } catch (UnsupportedMove unsupportedMove) {
            return false;
        }

        return canBeMoved(figureMoveDto.getType(), start, destination);
    }

    private boolean canBeMoved(FigureType type, MoveDetailsDto start, MoveDetailsDto destination) {
        switch (type) {
            case BISHOP:
                return bishop.isCorrectMove(start.getColumn(), start.getRow(), destination.getColumn(), destination.getRow());
            case KING:
                return king.isCorrectMove(start.getColumn(), start.getRow(), destination.getColumn(), destination.getRow());
            case KNIGHT:
                return knight.isCorrectMove(start.getColumn(), start.getRow(), destination.getColumn(), destination.getRow());
            case PAWN:
                return pawn.isCorrectMove(start.getColumn(), start.getRow(), destination.getColumn(), destination.getRow());
            case QUEEN:
                return queen.isCorrectMove(start.getColumn(), start.getRow(), destination.getColumn(), destination.getRow());
            case ROCK:
                return rook.isCorrectMove(start.getColumn(), start.getRow(), destination.getColumn(), destination.getRow());
            default:
                return false;
        }
    }
}
