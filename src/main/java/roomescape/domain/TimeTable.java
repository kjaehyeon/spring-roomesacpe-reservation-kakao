package roomescape.domain;

import java.time.LocalTime;
import java.util.Arrays;

public enum TimeTable {
    A(LocalTime.of(11, 30)),
    B(LocalTime.of(13, 00)),
    C(LocalTime.of(14, 30)),
    D(LocalTime.of(16, 00)),
    E(LocalTime.of(17, 30)),
    F(LocalTime.of(19, 00)),
    G(LocalTime.of(20, 30)),
    H(LocalTime.of(22, 00));

    private final LocalTime time;

    TimeTable(LocalTime time) {
        this.time = time;
    }

    public static boolean isExist(LocalTime time) {
        return Arrays.stream(values())
                .anyMatch(timeTable -> timeTable.time.equals(time));
    }
}
