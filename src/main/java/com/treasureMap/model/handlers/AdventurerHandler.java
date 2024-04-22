package com.treasureMap.model.handlers;

import com.treasureMap.model.Adventurer;
import com.treasureMap.model.Direction;
import com.treasureMap.model.Mountain;
import com.treasureMap.model.Treasure;
import com.treasureMap.model.TreasureMap;
import com.treasureMap.model.TreasureMapElement;

import java.util.List;
import java.util.Optional;

public class AdventurerHandler {

    public AdventurerHandler() {
    }

    public void move(TreasureMap treasureMap, Adventurer adventurer) {

        if (adventurer.getMovements().isEmpty()) {
            return;
        }

        char nextMovement = adventurer.getNextMovement();

        if (isRotationMovement(nextMovement)) {
            handleRotation(nextMovement, adventurer);
        } else {
            int newX = calculateNewX(adventurer.getDirection(), adventurer.getX());
            int newY = calculateNewY(adventurer.getDirection(), adventurer.getY());

            if (isValidMove(treasureMap, newX, newY)) {
                checkAndHandleMoveResult(newX, newY, treasureMap, adventurer);
            }
        }
        adventurer.setMovements(adventurer.getMovements().substring(1)); // Remove the first character (current movement)
    }

    private void checkAndHandleMoveResult(int newX, int newY, TreasureMap treasureMap, Adventurer adventurer) {
        handlePreviousCell(treasureMap, adventurer);

        List<TreasureMapElement> mapElement = treasureMap.getElement(newX, newY);
        Optional<TreasureMapElement> treasureOptional = mapElement.stream()
                .filter(treasureMapElement -> treasureMapElement instanceof Treasure)
                .findFirst();

        if (treasureOptional.isPresent()) {
            handleTreasureInteraction((Treasure) treasureOptional.get(), newX, newY, treasureMap, adventurer);
        } else if (mapElement.isEmpty()) {
            handleEmptyMapCell(newX, newY, treasureMap, adventurer);
        }
    }

    private void handleTreasureInteraction(Treasure treasure, int newX, int newY, TreasureMap treasureMap, Adventurer adventurer) {
        int numberOfTreasures = treasure.getNumberOfTreasures();
        adventurer.setTreasureAmount(adventurer.getTreasureAmount() + 1);
        treasure.setNumberOfTreasures(numberOfTreasures - 1);
        adventurer.setPosition(newX, newY);

        if (numberOfTreasures <= 1) {
            treasureMap.clearPosition(newX, newY);
        }

        treasureMap.placeElement(adventurer);
    }

    private void handleEmptyMapCell(int newX, int newY, TreasureMap treasureMap, Adventurer adventurer) {
        // Handle current adventurer movements if there is no mountain, adventurer or treasure
        adventurer.setPosition(newX, newY);
        treasureMap.placeElement(adventurer);
    }

    private void handleRotation(char nextMovement, Adventurer adventurer) {
        // If rotation needed, change direction according to nextMovement input
        Direction nextDirection = MovementHandler.getNextDirection(adventurer.getDirection(), nextMovement);
        adventurer.setDirection(nextDirection);
    }

    private void handlePreviousCell(TreasureMap treasureMap, Adventurer adventurer) {
        List<TreasureMapElement> previousMapElement = treasureMap.getElement(adventurer.getX(), adventurer.getY());
        Optional<TreasureMapElement> previousTreasureOptional = previousMapElement.stream()
                .filter(treasureMapElement -> treasureMapElement instanceof Treasure)
                .findFirst();
        if (previousTreasureOptional.isEmpty()) {
            // Clear the old position on the map
            treasureMap.clearPosition(adventurer.getX(), adventurer.getY());
        } else {
            // Remove only adventurer from the old position on the map
            treasureMap.removeAdventurer(adventurer.getX(), adventurer.getY());
        }
    }

    private int calculateNewX(Direction direction, int newX) {
        switch (direction) {
            case EAST:
                newX++;
                break;
            case WEST:
                newX--;
                break;
            default:
                break;
        }
        return newX;
    }

    private int calculateNewY(Direction direction, int newY) {
        switch (direction) {
            case NORTH:
                newY--;
                break;
            case SOUTH:
                newY++;
                break;
            default:
                break;
        }
        return newY;
    }

    private boolean isRotationMovement(char movement) {
        return movement == 'D' || movement == 'G';
    }

    private boolean isValidMove(TreasureMap treasureMap, int newX, int newY) {
        return newX >= 0 && newX < treasureMap.getWidth() &&
                newY >= 0 && newY < treasureMap.getHeight() &&
                checkIfBlocked(treasureMap, newX, newY);
    }

    private boolean checkIfBlocked(TreasureMap treasureMap, int newX, int newY) {
        List<TreasureMapElement> mapElements = treasureMap.getElement(newX, newY);

        if (!mapElements.isEmpty()) {
            TreasureMapElement firstElement = mapElements.getFirst();
            return !(firstElement instanceof Mountain) && !(firstElement instanceof Adventurer);
        }
        return true;
    }

}
