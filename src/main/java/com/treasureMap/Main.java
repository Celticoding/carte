package com.treasureMap;

import com.treasureMap.model.Adventurer;
import com.treasureMap.model.Mountain;
import com.treasureMap.model.Treasure;
import com.treasureMap.model.TreasureMap;
import com.treasureMap.model.TreasureMapElement;
import com.treasureMap.model.handlers.TreasureMapHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.treasureMap.output.FileWriter.writeOutputFile;

public class Main {

    public static void main(String[] args) {
        String filePath = "src/main/resources/treasureMap.txt";

        TreasureMap treasureMap = null;
        List<TreasureMapElement> treasureMapElements = new ArrayList<>();

        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            List<Adventurer> adventurers = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null) {
                String[] characters = line.replaceAll("\\s", "").split("-");

                switch (characters[0]) {
                    case "#":
                        return;
                    case "C":
                        if (treasureMap == null) {
                            treasureMap = new TreasureMap(Integer.parseInt(characters[1]), Integer.parseInt(characters[2]));
                        } else {
                            throw new IllegalArgumentException("Can't create two treasure map");
                        }
                        break;
                    case "T":
                        Treasure treasure = new Treasure(Integer.parseInt(characters[1]), Integer.parseInt(characters[2]),
                                Integer.parseInt(characters[3]));
                        treasureMapElements.add(treasure);
                        break;
                    case "A":
                        Adventurer adventurer = new Adventurer(Integer.parseInt(characters[2]), Integer.parseInt(characters[3]),
                                characters[1], characters[4], characters[5]);
                        adventurers.add(adventurer);
                        treasureMapElements.add(adventurer);
                        break;
                    case "M":
                        Mountain mountain = new Mountain(Integer.parseInt(characters[1]), Integer.parseInt(characters[2]));
                        treasureMapElements.add(mountain);
                        break;
                    default:
                        break;
                }
            }

            bufferedReader.close();

            if (treasureMap != null) {
                treasureMapElements.forEach(treasureMap::placeElement);
            } else {
                System.out.println("No treasureMap was given");
                throw new IllegalArgumentException();
            }

            TreasureMapHandler treasureMapHandler = new TreasureMapHandler();
            treasureMapHandler.performAdventurerMovements(treasureMap, adventurers);
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getMessage());
        }

        writeOutputFile(treasureMap, treasureMapElements);
    }


}
