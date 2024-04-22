import com.treasureMap.model.Direction;
import com.treasureMap.model.handlers.MovementHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovementHandlerTest {

    @Test
    public void shouldGetNextDirection() {
        assertEquals(Direction.WEST, MovementHandler.getNextDirection(Direction.NORTH, 'G'));
        assertEquals(Direction.EAST, MovementHandler.getNextDirection(Direction.NORTH, 'D'));

        assertEquals(Direction.NORTH, MovementHandler.getNextDirection(Direction.EAST, 'G'));
        assertEquals(Direction.SOUTH, MovementHandler.getNextDirection(Direction.EAST, 'D'));

        assertEquals(Direction.EAST, MovementHandler.getNextDirection(Direction.SOUTH, 'G'));
        assertEquals(Direction.WEST, MovementHandler.getNextDirection(Direction.SOUTH, 'D'));

        assertEquals(Direction.SOUTH, MovementHandler.getNextDirection(Direction.WEST, 'G'));
        assertEquals(Direction.NORTH, MovementHandler.getNextDirection(Direction.WEST, 'D'));

        assertEquals(Direction.NORTH, MovementHandler.getNextDirection(Direction.NORTH, 'X'));
    }
}
