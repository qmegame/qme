package org.qme.world.gen;

import org.qme.utils.Direction;
import org.qme.world.TileType;

public class Touches extends WorldGenerator{

    /**
     * A utility function to check if a tile touches an ocean
     * @author santiago, tom
     * @since 0.1.0
     * @param world The world
     * @param x The tile's x
     * @param y The tile's y
     * @return Whether or not the tile touches an ocean
     */
    static boolean touchesOcean(TileType[][] world, int x, int y) {
        try {
            if(getTileDirectional(world, x, y, Direction.UP)    == TileType.OCEAN) { return true; }
            if(getTileDirectional(world, x, y, Direction.DOWN)  == TileType.OCEAN) { return true; }
            if(getTileDirectional(world, x, y, Direction.LEFT)  == TileType.OCEAN) { return true; }
            if(getTileDirectional(world, x, y, Direction.RIGHT) == TileType.OCEAN) { return true; }
            return false;
        } catch(ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    /**
     * A utility function to check if a tile touches a sea
     * @author santiago, tom
     * @since 0.2.0
     * @param world The world
     * @param x The tile's x
     * @param y The tile's y
     * @return Whether or not the tile touches a sea
     */
    static boolean touchesSea(TileType[][] world, int x, int y) {
        try {
            if (getTileDirectional(world, x, y, Direction.UP)    == TileType.SEA) { return true; }
            if (getTileDirectional(world, x, y, Direction.DOWN)  == TileType.SEA) { return true; }
            if (getTileDirectional(world, x, y, Direction.LEFT)  == TileType.SEA) { return true; }
            if (getTileDirectional(world, x, y, Direction.RIGHT) == TileType.SEA) { return true; }
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    /**
     * A utility function that checks whether a tile touches ocean on two sides
     * @author santiago, tom
     * @since 0.1.0
     * @param world The world with the tile
     * @param x The tile to be checked
     * @param y The tile to be checked
     * @return True or false: it touches two ocean tiles
     */
    static boolean touchesTwoOceans(TileType[][] world, int x, int y) {
        try {
            // Set up count
            int oceansTouched = 0;

            // Count
            if(isOcean(getTileDirectional(world, x, y, Direction.UP)))    { oceansTouched++; }
            if(isOcean(getTileDirectional(world, x, y, Direction.DOWN)))  { oceansTouched++; }
            if(isOcean(getTileDirectional(world, x, y, Direction.LEFT)))  { oceansTouched++; }
            if(isOcean(getTileDirectional(world, x, y, Direction.RIGHT))) { oceansTouched++; }

            // Return
            return oceansTouched >= 2;
        } catch(ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    /**
     * A utility function to determine if a tile touches land
     * @author santiago
     * @since 0.1.0
     * @param world The world with no continental shelves
     * @param x The tile to be checked
     * @param y The tile to be checked
     */
    static boolean touchesLand(TileType[][] world, int x, int y) {
        try {
            if(!isOcean(getTileDirectional(world, x, y, Direction.UP))) { return true; }
            if(!isOcean(getTileDirectional(world, x, y, Direction.DOWN))) { return true; }
            if(!isOcean(getTileDirectional(world, x, y, Direction.LEFT))) { return true; }
            if(!isOcean(getTileDirectional(world, x, y, Direction.RIGHT))) { return true; }
            return false;
        } catch(ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}