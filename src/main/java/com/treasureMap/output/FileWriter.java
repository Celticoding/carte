package com.treasureMap.output;

import com.treasureMap.model.Adventurer;
import com.treasureMap.model.Mountain;
import com.treasureMap.model.Treasure;
import com.treasureMap.model.TreasureMap;
import com.treasureMap.model.TreasureMapElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileWriter {

    public static void writeOutputFile(TreasureMap treasureMap, List<TreasureMapElement> treasureMapElements) {
        String outputFilePath = "src/main/resources/treasureMapResult.txt";
        List<String> linesToWrite = generateOutputLines(treasureMap, treasureMapElements);

        try {
            Path filePathOutputFile = Paths.get(outputFilePath);
            Files.write(filePathOutputFile, linesToWrite);
            System.out.println("File written in resources folder: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error while writing file: " + e.getMessage());
        }
    }

    private static List<String> generateOutputLines(TreasureMap treasureMap, List<TreasureMapElement> treasureMapElements) {
        List<String> lines = new ArrayList<>();

        lines.add(treasureMap.getSymbol() + " - " + treasureMap.getWidth() + " - " + treasureMap.getHeight());

        for (TreasureMapElement treasureMapElement : treasureMapElements) {
            if (treasureMapElement instanceof Treasure treasure) {
                Integer numberOfTreasures = treasure.getNumberOfTreasures();
                if (numberOfTreasures != 0) {
                    lines.add(treasure.getSymbol() + " - " + treasure.getX() + " - " + treasure.getY() + " - " + numberOfTreasures);
                }
            } else if (treasureMapElement instanceof Adventurer adventurer) {
                lines.add(adventurer.getSymbol() + " - " + adventurer.getName() + " - " + adventurer.getX() + " - " + adventurer.getY() +
                        " - " + adventurer.getDirection().getValue() + " - " + adventurer.getTreasureAmount());
            } else if (treasureMapElement instanceof Mountain mountain) {
                lines.add(mountain.getSymbol() + " - " + mountain.getX() + " - " + mountain.getY());
            }
        }

        return lines;
    }
}
