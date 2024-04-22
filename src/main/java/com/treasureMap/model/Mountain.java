package com.treasureMap.model;

public class Mountain extends TreasureMapElement {

    public Mountain(int horizontalAxis, int verticalAxis) {
        super(horizontalAxis, verticalAxis);
    }

    @Override
    public String getSymbol() {
        return "M";
    }
}
