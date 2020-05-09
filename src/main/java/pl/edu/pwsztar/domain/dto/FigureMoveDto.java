package pl.edu.pwsztar.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.edu.pwsztar.domain.enums.FigureType;

import java.io.Serializable;
import java.util.Optional;

@NoArgsConstructor
@Getter
@ToString
public class FigureMoveDto implements Serializable {
    private String start;
    private String destination;
    private FigureType type;

    public boolean hasProperStart() {
        return hasProperData(this.start);
    }

    public boolean hasProperDestination() {
        return hasProperData(this.destination);
    }

    private boolean hasProperData(String data) {
        return Optional.ofNullable(data)
            .filter(str -> !str.isBlank())
            .isPresent();
    }
}
