package com.treasureMap.model.handlers;

import com.treasureMap.model.Adventurer;
import com.treasureMap.model.TreasureMap;

import java.util.List;

public class TreasureMapHandler {

    public TreasureMapHandler() {
    }

    public void performAdventurerMovements(TreasureMap treasureMap, List<Adventurer> adventurers) {
        boolean hasToMove;
        do {
            hasToMove = false;
            for (Adventurer adventurer : adventurers) {
                if (!adventurer.getMovements().isEmpty()) {
                    hasToMove = true;
                } else {
                    continue;
                }
                AdventurerHandler adventurerHandler = new AdventurerHandler();
                adventurerHandler.move(treasureMap, adventurer);
            }
        } while (hasToMove);
    }
}
