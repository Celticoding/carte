import com.treasureMap.model.Adventurer;
import com.treasureMap.model.Mountain;
import com.treasureMap.model.TreasureMap;
import com.treasureMap.model.TreasureMapElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TreasureMapTest {

    private TreasureMap treasureMap;

    @BeforeEach
    public void setUp() {
        int width = 4;
        int height = 4;
        this.treasureMap = new TreasureMap(width, height);

        Adventurer adventurer = new Adventurer(2, 2, "John", "N", "AGD");
        this.treasureMap.placeElement(adventurer);

        Mountain mountain = new Mountain(3, 3);
        this.treasureMap.placeElement(mountain);
    }

    @Test
    public void IsValidPositionAdventurerOrMountainPresent() {
        assertFalse(treasureMap.isValidPosition(2, 2));
        assertFalse(treasureMap.isValidPosition(3, 3));
    }

    @Test
    public void shouldNotPlaceElementOnExistingMountainOrAdventurer() {
        Adventurer adventurer = new Adventurer(2, 2, "Jack", "S", "AGD");
        this.treasureMap.placeElement(adventurer);

        List<TreasureMapElement> element = this.treasureMap.getElement(2, 2);

        assertEquals(1, element.size());
        assertNotEquals(adventurer, element.getFirst());
        assertEquals("John", ((Adventurer) element.getFirst()).getName());
    }
}
