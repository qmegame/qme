package org.qme.world.gen;

import org.qme.io.Logger;
import org.qme.io.Severity;
import org.qme.utils.Direction;
import org.qme.world.TileType;

public class AddStuff extends WorldGenerator{
    /**
     * A utility function to generate and add a continent
     * @author santiago, tom
     * @since 0.1.0
     * @param world The array to be modified
     * @param side The size of the world
     * @param centerX The center of the continent
     * @param centerY The center of the continent
     * @return The inputted array with a continent added
     */
    static TileType[][] addContinent(TileType[][] world, double side, int centerX, int centerY) {

        // Set up return world
        TileType[][] newWorld;
        newWorld = world;

        Logger.log("assign center tile to random", Severity.DEBUG);
        // Assign center tile to a random non-mountain land type
        newWorld[centerX][centerY] = WorldGenerator.assignRandomFlatLand();
        Logger.log("centerX is: " + Integer.toString(centerX), Severity.DEBUG);
        Logger.log("centerY is: " + Integer.toString(centerY), Severity.DEBUG);

        // Set up extreme points with most extreme values
        int leftmost = 0;
        int upmost = 0;
        int rightmost = (int) (side - 1);
        int downmost = (int) (side - 1);

        Logger.log("before expand left", Severity.DEBUG);
        // Expand left
        for(int i = 1 /* helps with math*/ ; i <= centerX; i++) {
            try {
                if(newWorld[centerX - i][centerY] == TileType.OCEAN) {

                    // Set chance that tile is land
                    double chance = Math.pow(0.95, i) / 0.95;
                    if(i > side * 2 / 3) {
                        chance = 0;
                    }

                    if(rand.nextInt(10000) < Math.floor(10000 * chance)) {
                        newWorld[centerX - i][centerY] =
                                WorldGenerator.assignRandomFlatLand();
                    } else {
                        i--;

                        // Give leftmost a value
                        leftmost = centerX - i;

                        break; // If it's not triggered, stop expanding left
                    }
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                leftmost = centerX - i + 1;
                break;
            }
        }

        Logger.log(EXPANDED + Integer.toString(centerX - leftmost) +
                " tiles. before expand up", Severity.DEBUG);
        // Expand up
        for(int j = 1 /* helps with math*/ ; j <= centerY; j++) {
            try {
                if(newWorld[centerX][centerY - j] == TileType.OCEAN) {

                    // Set chance that tile is land
                    double chance = Math.pow(0.95, j) / 0.95;
                    if(j > side * 2 / 3) {
                        chance = 0;
                    }

                    // Determine if tile is land
                    if(rand.nextInt(10000) < Math.floor(10000 * chance)) {
                        newWorld[centerX][centerY - j] =
                                WorldGenerator.assignRandomFlatLand();
                    } else {
                        j--;

                        // Give upmost a value
                        upmost = centerY - j;

                        break; // If it's not triggered, stop expanding up
                    }
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                upmost = centerY - j + 1;
                break;
            }
        }

        Logger.log(EXPANDED + Integer.toString(centerY - upmost) +
                " tiles. before expand right", Severity.DEBUG);
        // Expand right
        for(int ii = 1 /* helps with math*/ ; ii < side; ii++) {
            try {
                if(newWorld[centerX + ii][centerY] == TileType.OCEAN) {

                    // Set chance that tile is land
                    double chance = Math.pow(0.95, ii) / 0.95;
                    if(ii > side * 2 / 3) {
                        chance = 0;
                    }

                    if(rand.nextInt(10000) < Math.floor(10000 * chance)) {
                        newWorld[centerX + ii][centerY] =
                                WorldGenerator.assignRandomFlatLand();
                    } else {
                        ii--;

                        // Give rightmost a value
                        rightmost = centerX + ii;

                        break; // If it's not triggered, stop expanding right
                    }
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                rightmost = centerX + ii - 1;
                break;
            }
        }

        Logger.log(EXPANDED + Integer.toString(centerX - rightmost) +
                " tiles. before expand down", Severity.DEBUG);
        // Expand down
        for(int jj = 1 /* helps with math*/ ; jj < side; jj++) {
            try {
                if(newWorld[centerX][centerY + jj] == TileType.OCEAN) {

                    // Set chance that tile is land
                    double chance = Math.pow(0.95, jj) / 0.95;
                    if(jj > side * 2 / 3) {
                        chance = 0;
                    }

                    if(rand.nextInt(10000) < Math.floor(10000 * chance)) {
                        newWorld[centerX][centerY + jj] =
                                WorldGenerator.assignRandomFlatLand();
                    } else {
                        jj--;

                        // Give downmost a value
                        downmost = centerY + jj;

                        break; // If it's not triggered, stop expanding down
                    }
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                downmost = centerY + jj - 1;
                break;
            }
        }

        Logger.log(EXPANDED + Integer.toString(centerY - downmost) +
                " tiles. before expand squares", Severity.DEBUG);
        // Expand continents into "squares"
        for(int k = leftmost; k <= rightmost; k++) {
            for(int l = upmost; l <= downmost; l++) {
                if(newWorld[k][l] == TileType.OCEAN) {
                    final double distance = Math.pow(Math.pow(Math.abs(centerX - k),
                            16 / 15) + Math.pow(Math.abs(centerY - l), 16 / 15), 7 / 4);

                    // Set chance that tile is land
                    double chance = Math.pow(0.95, distance) / 0.95;
                    if(distance > side * 2 / 3) {
                        chance = 0;
                    }

                    if(rand.nextInt(10000) < Math.floor(10000 * chance)) {
                        newWorld[k][l] = WorldGenerator.assignRandomFlatLand();
                    }
                }
            }
        }

        Logger.log("done with individual continent", Severity.DEBUG);
        // Return the world plus continent
        return newWorld;
    }

    /**
     * The final mountain addition (after continents)
     * @param world The world
     * @param side The side length
     */
    static TileType[][] addMountainFinal(TileType[][] world, int side) {
        TileType[][] mountainWorld = world;
        // Get range direction and length
        final int rangeLength = 7 + (rand.nextInt(5) - 2);
        final Direction rangeDirection = Direction.values()[rand.nextInt(4)];

        // Get start of range
        final int startX = rand.nextInt(side);
        final int startY = rand.nextInt(side);

        // Generate mountains
        mountainWorld[startX][startY] = WorldGenerator.assignRandomMountain();
        for(int i = 1; i < rangeLength; i++) {
            try {
                if(rangeDirection == Direction.UP) {
                    mountainWorld[startX][startY - i] = WorldGenerator.assignRandomMountain();
                } else if(rangeDirection == Direction.DOWN) {
                    mountainWorld[startX][startY + i] = WorldGenerator.assignRandomMountain();
                } else if(rangeDirection == Direction.LEFT) {
                    mountainWorld[startX - 1][startY] = WorldGenerator.assignRandomMountain();
                } else {
                    mountainWorld[startX + 1][startY] = WorldGenerator.assignRandomMountain();
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                break;
            }
        }

        // Sink floating mountains
        WorldGenerator.mountainSink(mountainWorld, side);

        return mountainWorld;
    }

    /**
     * The final river addition (after continents)
     * @param world The world
     * @param side The side length
     */
    static TileType[][] addRiverFinal(TileType[][] world, int side) {
        TileType[][] riverWorld = world;
        // Get river direction
        final Direction riverDirection = Direction.values()[rand.nextInt(4)];

        // Get start of river
        int startX = rand.nextInt(side);
        int startY = rand.nextInt(side);
        riverWorld[startX][startY] = TileType.OCEAN;

        // Generate river until touches ocean
        try {
            while (!isOcean(getTileDirectional(riverWorld, startX, startY, riverDirection))) {
                try {
                    if (riverDirection == Direction.UP) {
                        riverWorld[startX][startY - 1] = TileType.OCEAN;
                        startY--;
                    } else if (riverDirection == Direction.DOWN) {
                        riverWorld[startX][startY + 1] = TileType.OCEAN;
                        startY++;
                    } else if (riverDirection == Direction.LEFT) {
                        riverWorld[startX - 1][startY] = TileType.OCEAN;
                        startX--;
                    } else {
                        riverWorld[startX + 1][startY] = TileType.OCEAN;
                        startX++;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            return world;
        }

        // Return
        return riverWorld;
    }
}
