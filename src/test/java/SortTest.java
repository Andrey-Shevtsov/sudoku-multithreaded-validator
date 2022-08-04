import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import validators.ColumnValidator;
import validators.TileValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortTest {

    @Test
    @DisplayName("insertion sort")
    void sortsArray() {
        ColumnValidator validator = new ColumnValidator();
        int[] arr = {1, 5, 3, 9, 2, 7, 4, 6, 8};
        int[] sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        validator.setColumn(arr);
        validator.sort();
        assertEquals(Arrays.toString(sorted),
                Arrays.toString(validator.getColumn()),
                Arrays.toString(arr) + " should equal " + "123456789");
    }

    @Test
    @DisplayName("convert tile")
    void convertsTile() {
        TileValidator validator = new TileValidator();
        int[][] tile = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[] res = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        validator.setTile(tile);
        int[] asserted = validator.convertTile();
        assertEquals(Arrays.toString(res),
                Arrays.toString(res),
                Arrays.toString(asserted) + " should equal " + "123456789");
    }
}
