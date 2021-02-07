package org.qme.client.vis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class LoadingBarTest {

    LoadingBar bar = new LoadingBar();
    Scanner scanner = new Scanner(System.in);

    @BeforeEach
    void setUp() {
        bar.newWindow();
    }

    @AfterEach
    void tearDown() {
        bar.done();
    }

    @Test
    void newWindowTest() {
        assertNotNull(scanner.nextLine().split(" "), "Cool");
    }
}