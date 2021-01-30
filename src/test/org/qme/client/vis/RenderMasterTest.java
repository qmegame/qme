package org.qme.client.vis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.qme.world.World;

import static org.junit.jupiter.api.Assertions.*;

class RenderMasterTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void drawTile() {
    }

    @Test
    void isInFrame() {

        double worldSize = World.WORLD_SIZE * (RenderMaster.TILE_SPACING + RenderMaster.TILE_SIZE);
        // In bounds
        assertTrue(RenderMaster.isInFrame(0, 0));
        assertTrue(RenderMaster.isInFrame(worldSize, worldSize));
        assertTrue(RenderMaster.isInFrame(worldSize / 2, worldSize / 2));

        // Out of bounds
        assertFalse(RenderMaster.isInFrame(-1000, -1000));
        assertFalse(RenderMaster.isInFrame(worldSize + 1000, worldSize + 1000));
    }
}