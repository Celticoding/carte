package com.treasureMap.model.handlers;

import com.treasureMap.model.Direction;

import java.util.Objects;

public class MovementHandler {

    public static Direction getNextDirection(Direction currentDirection, char movement) {
        switch (currentDirection) {
            case NORTH:
                if (Objects.equals(movement, 'G')) {
                    return Direction.WEST;
                } else if (Objects.equals(movement, 'D')) {
                    return Direction.EAST;
                }
                break;
            case EAST:
                if (Objects.equals(movement, 'G')) {
                    return Direction.NORTH;
                } else if (Objects.equals(movement, 'D')) {
                    return Direction.SOUTH;
                }
                break;
            case SOUTH:
                if (Objects.equals(movement, 'G')) {
                    return Direction.EAST;
                } else if (Objects.equals(movement, 'D')) {
                    return Direction.WEST;
                }
                break;
            case WEST:
                if (Objects.equals(movement, 'G')) {
                    return Direction.SOUTH;
                } else if (Objects.equals(movement, 'D')) {
                    return Direction.NORTH;
                }
                break;
        }
        return currentDirection;
    }

}

