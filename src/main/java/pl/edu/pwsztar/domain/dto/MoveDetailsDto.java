package pl.edu.pwsztar.domain.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import pl.edu.pwsztar.exception.UnsupportedMove;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public final class MoveDetailsDto {

  int column;
  int row;

  @Builder
  public MoveDetailsDto(char column, int row) {
    this.column = getColumnValue(column);
    this.row = row;
  }

  private int getColumnValue(char column) {
    return column - 'a' + 1;
  }

}
