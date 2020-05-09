package pl.edu.pwsztar.service.serviceImpl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import pl.edu.pwsztar.domain.dto.FigureMoveDto;
import pl.edu.pwsztar.domain.dto.MoveDetailsDto;
import pl.edu.pwsztar.exception.UnsupportedMove;

import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
final class MoveDetailsProvider {

  private static final String MOVE_DELIMITER = "_";
  private static final String COLUMN_PATTERN = "^[a-h]+$";
  private static final String ROW_PATTERN = "^[1-8]+$";
  public static final String EMPTY_MOVE = "";
  final String start;
  final String destination;

  private MoveDetailsProvider(FigureMoveDto figureMoveDto) {
    this.start = Optional.ofNullable(figureMoveDto)
        .filter(FigureMoveDto::hasProperStart)
        .map(FigureMoveDto::getStart)
        .orElse(EMPTY_MOVE);
    this.destination = Optional.ofNullable(figureMoveDto)
        .filter(FigureMoveDto::hasProperDestination)
        .map(FigureMoveDto::getDestination)
        .orElse(EMPTY_MOVE);
  }

  static MoveDetailsProvider create(FigureMoveDto figureMoveDto) {
     return new MoveDetailsProvider(figureMoveDto);
  }

  MoveDetailsDto getStartDetails() throws UnsupportedMove {
    return getMoveDetails(start);
  }

  MoveDetailsDto getDestinationDetails() throws UnsupportedMove {
    return getMoveDetails(destination);
  }

  private MoveDetailsDto getMoveDetails(String move) throws UnsupportedMove {
    final String[] moveDetails = move.split(MOVE_DELIMITER);
    if (hasWrongData(moveDetails)) {
      throw new UnsupportedMove();
    }

    return MoveDetailsDto.builder()
        .column(getColumn(moveDetails[0]))
        .row(getRow(moveDetails[1]))
        .build();
  }

  private boolean hasWrongData(String[] moveDetails) {
    return moveDetails.length != 2;
  }

  private char getColumn(String moveDetail) throws UnsupportedMove {
    if (!isColumn(moveDetail)) {
      throw new UnsupportedMove();
    }

    return moveDetail.charAt(0);
  }

  private boolean isColumn(String toCheck) {
      return toCheck.length() == 1 && toCheck.matches(COLUMN_PATTERN);
  }

  private int getRow(String moveDetail) throws UnsupportedMove {
    if (!isRow(moveDetail)) {
      throw new UnsupportedMove();
    }

    return Integer.parseInt(moveDetail);
  }

  private boolean isRow(String toCheck) {
    try {
      Integer.parseInt(toCheck);
      return toCheck.matches(ROW_PATTERN);
    } catch (NumberFormatException exception) {
      return false;
    }
  }

}
