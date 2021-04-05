package org.qme.world.gen;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldGeneratorTest {

    private static final int WORLD_SIZE = 250;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void generateWorldMapTest() {
        assertNotNull(WorldGenerator.generateWorldMap(WORLD_SIZE), "Did not crash");
    }

}