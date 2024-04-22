import com.treasureMap.model.Adventurer;
import com.treasureMap.model.TreasureMap;
import com.treasureMap.model.handlers.TreasureMapHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreasureMapHandlerTest {

    private TreasureMapHandler treasureMapHandler;
    private TreasureMap treasureMap;

    @BeforeEach
    void setUp() {
        treasureMapHandler = new TreasureMapHandler();
        treasureMap = new TreasureMap(4, 4);
    }

    @Test
    public void performAdventurerMovementsShouldStopIfNoMovementsLeft() {
        List<Adventurer> adventurers = new ArrayList<>();
        Adventurer adventurer1 = new Adventurer(0, 0, "Lara", "N", "DADAGA");
        Adventurer adventurer2 = new Adventurer(1, 1, "Indiana", "S", "AAGA");
        adventurers.add(adventurer1);
        adventurers.add(adventurer2);

        treasureMap.placeElement(adventurer1);
        treasureMap.placeElement(adventurer2);

        treasureMapHandler.performAdventurerMovements(treasureMap, adventurers);

        assertEquals(2, adventurer1.getX());
        assertEquals(1, adventurer1.getY());
        assertEquals(2, adventurer2.getX());
        assertEquals(3, adventurer2.getY());
    }
}
