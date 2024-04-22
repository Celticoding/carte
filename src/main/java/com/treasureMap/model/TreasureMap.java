package com.treasureMap.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreasureMap {

    private final Integer width;
    private final Integer height;
    private final List<TreasureMapElement>[][] grid;

    public TreasureMap(Integer width, Integer height) {
        this.width = width;
        this.height = height;
        this.grid = new List[height][width];
        initializeMap();
    }

    private void initializeMap() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.grid[y][x] = new ArrayList<>();
            }
        }
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public String getSymbol() {
        return "C";
    }

    public void placeElement(TreasureMapElement element) {
        int x = element.getX();
        int y = element.getY();

        if (isValidPosition(x, y)) {
            this.grid[y][x].add(element);
        } else {
            System.out.println("Invalid position, can't place element.");
        }
    }

    public List<TreasureMapElement> getElement(int x, int y) {
        return this.grid[y][x];
    }

    public void clearPosition(int x, int y) {
        this.grid[y][x] = new ArrayList<>();
    }

    public void removeAdventurer(int x, int y) {
        this.grid[y][x] = this.grid[y][x].stream()
                .filter(element -> !(element instanceof Adventurer))
                .collect(Collectors.toList());
    }

    public boolean isValidPosition(int x, int y) {
        boolean hasAdventurerOrMountain = this.grid[y][x].stream()
                .anyMatch(element -> element instanceof Adventurer || element instanceof Mountain);

        if (hasAdventurerOrMountain) {
            return false;
        }

        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public void displayMap() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (!this.grid[y][x].isEmpty()) {
                    if (this.grid[y][x].size() == 2) {
                        System.out.print(this.grid[y][x].get(0).getSymbol() + "/" + this.grid[y][x].get(1).getSymbol());
                    } else {
                        System.out.print(this.grid[y][x].getFirst().getSymbol() + "   ");
                    }
                } else {
                    System.out.print(".   ");
                }
            }
            System.out.println();
        }
    }

}
