package org.qme.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MultithreadingTest {

    private Multithreading R1;
    private Multithreading R2;
    Scanner scanner = new Scanner(System.in);

    @BeforeEach
    void setUp() {
        R1 = new Multithreading("Thread 1");
        R2 = new Multithreading("Thread 2");
        R1.start();
        R2.start();
    }

    @AfterEach
    void tearDown() {
        R1.stop();
        R2.stop();
        scanner.close();
    }

    @Test
    void testRun() {
        R1.run();
        R2.run();
        assertNotNull(scanner.nextLine().split(" "), "Cool");
    }
}