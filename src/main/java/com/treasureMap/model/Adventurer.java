package com.treasureMap.model;

public class Adventurer extends TreasureMapElement {

    private final String name;
    private Direction direction;
    private Integer treasureAmount;
    private String movements;

    public Adventurer(int horizontalAxis, int verticalAxis, String name, String direction, String movements) {
        super(horizontalAxis, verticalAxis);
        this.name = name;
        this.direction = Direction.getFromString(direction);
        this.movements = movements;
        this.treasureAmount = 0;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public Integer getTreasureAmount() {
        return treasureAmount;
    }

    public void setTreasureAmount(Integer treasureAmount) {
        this.treasureAmount = treasureAmount;
    }

    public String getMovements() {
        return movements;
    }

    public void setMovements(String movements) {
        this.movements = movements;
    }

    public char getNextMovement() {
        return movements.charAt(0);
    }

    @Override
    public String getSymbol() {
        return "A";
    }

}
