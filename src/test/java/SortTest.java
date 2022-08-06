import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import validators.ColumnValidator;
import validators.TileValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortTest {

    List<Integer> result = new ArrayList<>(1);
    @Test
    @DisplayName("insertion sort")
    void sortsArray() {
        int[] arr = {1, 5, 3, 9, 2, 7, 4, 6, 8};
        ColumnValidator validator = new ColumnValidator(arr, result);
        int[] sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        validator.sort();
        assertEquals(Arrays.toString(sorted),
                Arrays.toString(validator.getColumn()),
                Arrays.toString(arr) + " should equal " + "[1, 2, 3, 4, 5, 6, 7, 8, 9]");
    }

    @Test
    @DisplayName("convert tile")
    void convertsTile() {
        int[][] tile = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        TileValidator validator = new TileValidator(tile, result);
        int[] res = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] asserted = validator.convertTile();
        assertEquals(Arrays.toString(res),
                Arrays.toString(res),
                Arrays.toString(asserted) + " should equal " + "[1, 2, 3, 4, 5, 6, 7, 8, 9]");
    }
}
