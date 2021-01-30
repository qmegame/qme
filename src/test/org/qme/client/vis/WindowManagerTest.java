package org.qme.client.vis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.qme.world.World;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

class WindowManagerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldBeOpen() {
    }

    @Test
    void repaint() {
    }

    @Test
    void getWorldSize() {
        assertTrue(WindowManager.getWorldSize(RenderMaster.zoom) > RenderMaster.zoom);
        assertTrue(WindowManager.getWorldSize(RenderMaster.zoom) > RenderMaster.zoom * World.WORLD_SIZE);
        assertTrue(WindowManager.getWorldSize(RenderMaster.zoom) > RenderMaster.zoom * World.WORLD_SIZE * RenderMaster.TILE_SIZE);
        assertEquals(WindowManager.getWorldSize(RenderMaster.zoom), RenderMaster.zoom * World.WORLD_SIZE * RenderMaster.TILE_SPACING);
    }

    @Test
    void getMouseLocation() {
    }

    @Test
    void getWindowX() {
        assertEquals(WindowManager.getWindowX(), WindowManager.getWindowY());
    }

    @Test
    void getWindowY() {
        assertEquals(WindowManager.getWindowY(), WindowManager.getWindowX());
    }

    @Test
    void addObject() {
    }
}