import com.treasureMap.model.Adventurer;
import com.treasureMap.model.Direction;
import com.treasureMap.model.Mountain;
import com.treasureMap.model.Treasure;
import com.treasureMap.model.TreasureMap;
import com.treasureMap.model.handlers.AdventurerHandler;
import com.treasureMap.model.handlers.TreasureMapHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdventurerHandlerTest {

    private TreasureMap treasureMap;
    private AdventurerHandler adventurerHandler;

    @BeforeEach
    public void setup() {
        treasureMap = new TreasureMap(4, 4);
        adventurerHandler = new AdventurerHandler();
    }

    @Test
    public void adventurerInitialization() {
        Adventurer adventurer = new Adventurer(0, 0, "Lara", "N", "AADADAGGA");
        assertEquals("Lara", adventurer.getName());
        assertEquals(Direction.NORTH, adventurer.getDirection());
        assertEquals(0, adventurer.getTreasureAmount());
        assertEquals("AADADAGGA", adventurer.getMovements());
    }

    @Test
    public void adventurerShouldMove() {
        Adventurer adventurer = new Adventurer(1, 1, "Lara", "N", "ADDAADADAGGA");
        Treasure treasure = new Treasure(1, 1, 1);
        treasureMap.placeElement(treasure);
        adventurerHandler.move(treasureMap, adventurer);
        assertEquals(1, adventurer.getX());
        assertEquals(0, adventurer.getY());

        adventurerHandler.move(treasureMap, adventurer);
        adventurerHandler.move(treasureMap, adventurer);
        adventurerHandler.move(treasureMap, adventurer);
        assertEquals(1, adventurer.getTreasureAmount());
        assertEquals(0, treasure.getNumberOfTreasures());

        Mountain mountain = new Mountain(1, 2);
        treasureMap.placeElement(mountain);
        adventurerHandler.move(treasureMap, adventurer);

        assertEquals(1, adventurer.getX());
        assertEquals(1, adventurer.getY());
    }

    @Test
    public void adventurerShouldRotateAndChangeDirection() {
        Adventurer adventurer = new Adventurer(2, 2, "Lara", "N", "DGGGGD");

        adventurerHandler.move(treasureMap, adventurer);
        assertEquals(Direction.EAST, adventurer.getDirection());

        adventurerHandler.move(treasureMap, adventurer);
        assertEquals(Direction.NORTH, adventurer.getDirection());

        adventurerHandler.move(treasureMap, adventurer);
        assertEquals(Direction.WEST, adventurer.getDirection());

        adventurerHandler.move(treasureMap, adventurer);
        assertEquals(Direction.SOUTH, adventurer.getDirection());

        adventurerHandler.move(treasureMap, adventurer);
        assertEquals(Direction.EAST, adventurer.getDirection());

        adventurerHandler.move(treasureMap, adventurer);
        assertEquals(Direction.SOUTH, adventurer.getDirection());
    }

    @Test
    public void adventurerShouldGetTreasureAndThenDoNothing() {
        Adventurer adventurer = new Adventurer(3, 3, "Lara", "N", "AA");

        Treasure treasure = new Treasure(3, 2, 2);
        treasureMap.placeElement(treasure);
        adventurerHandler.move(treasureMap, adventurer);
        assertEquals(1, adventurer.getTreasureAmount());
        assertEquals(1, treasure.getNumberOfTreasures());

        adventurerHandler.move(treasureMap, adventurer);
        assertEquals(3, adventurer.getX());
        assertEquals(1, adventurer.getY());
    }

    @Test
    public void adventurerCarteResultShouldBeValid() {
        TreasureMap treasureMap = new TreasureMap(3, 4);
        Mountain mountain1 = new Mountain(1, 0);
        Mountain mountain2 = new Mountain(2, 1);
        Treasure treasure1 = new Treasure(1, 3, 3);
        Treasure treasure2 = new Treasure(0, 3, 2);
        Adventurer adventurer = new Adventurer(1, 1, "Lara", "S", "AADADAGGA");
        treasureMap.placeElement(treasure1);
        treasureMap.placeElement(treasure2);
        treasureMap.placeElement(mountain1);
        treasureMap.placeElement(mountain2);
        treasureMap.placeElement(adventurer);

        TreasureMapHandler treasureMapHandler = new TreasureMapHandler();

        treasureMapHandler.performAdventurerMovements(treasureMap, List.of(adventurer));
        assertEquals(3, adventurer.getTreasureAmount());
        assertEquals(0, adventurer.getX());
        assertEquals(3, adventurer.getY());
        assertEquals("S", adventurer.getDirection().getValue());

        assertEquals(2, treasure1.getNumberOfTreasures());
    }
}
