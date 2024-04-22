package com.treasureMap.model;

public class Treasure extends TreasureMapElement {

    private Integer numberOfTreasures;

    public Treasure(int horizontalAxis, int verticalAxis, Integer numberOfTreasures) {
        super(horizontalAxis, verticalAxis);
        this.numberOfTreasures = numberOfTreasures;
    }

    public Integer getNumberOfTreasures() {
        return numberOfTreasures;
    }

    public void setNumberOfTreasures(Integer numberOfTreasures) {
        this.numberOfTreasures = numberOfTreasures;
    }

    @Override
    public String getSymbol() {
        return "T";
    }
}
