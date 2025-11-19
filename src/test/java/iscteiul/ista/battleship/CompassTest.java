package iscteiul.ista.battleship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for enum Compass.
 * Author: ${user.name}
 * Date: 2025-11-18 12:58
 * Cyclomatic Complexity:
 * - constructor: 1
 * - getDirection(): 1
 * - toString(): 1
 * - charToCompass(): 5
 */
class CompassTest {

    private Compass compassUnderTest;

    @BeforeEach
    void setUp() {
        // Using a concrete enum constant as the instance under test
        compassUnderTest = Compass.NORTH;
    }

    @AfterEach
    void tearDown() {
        // Clean up reference after each test
        compassUnderTest = null;
    }

    /**
     * constructor() – CC = 1
     * Verifies that the enum constructor correctly stores the given character
     * for each defined constant (NORTH, SOUTH, EAST, WEST, UNKNOWN).
     */
    @Test
    void constructor() {
        Compass north = Compass.NORTH;
        Compass south = Compass.SOUTH;
        Compass east  = Compass.EAST;
        Compass west  = Compass.WEST;
        Compass unknown = Compass.UNKNOWN;

        assertAll(
                "Error: constructor did not correctly initialize internal direction characters",
                () -> assertEquals('n', north.getDirection(),
                        "Error: expected 'n' for NORTH but got '" + north.getDirection() + "'"),
                () -> assertEquals('s', south.getDirection(),
                        "Error: expected 's' for SOUTH but got '" + south.getDirection() + "'"),
                () -> assertEquals('e', east.getDirection(),
                        "Error: expected 'e' for EAST but got '" + east.getDirection() + "'"),
                () -> assertEquals('o', west.getDirection(),
                        "Error: expected 'o' for WEST but got '" + west.getDirection() + "'"),
                () -> assertEquals('u', unknown.getDirection(),
                        "Error: expected 'u' for UNKNOWN but got '" + unknown.getDirection() + "'")
        );
    }

    /**
     * getDirection() – CC = 1
     * Tests the simple path that returns the stored direction character.
     */
    @Test
    void getDirection() {
        char result = compassUnderTest.getDirection();

        assertEquals('n', result,
                "Error: expected 'n' for NORTH but got '" + result + "'");
    }

    /**
     * toString() – CC = 1
     * Tests that toString() returns the String representation of the stored character.
     */
    @Test
    void toStringTest() {
        String result = compassUnderTest.toString();

        assertEquals("n", result,
                "Error: expected \"n\" for NORTH.toString() but got \"" + result + "\"");
    }

    /**
     * charToCompass() – CC = 5
     * Path 1: switch branch for 'n' → Compass.NORTH
     */
    @Test
    void charToCompass1() {
        Compass result = Compass.charToCompass('n');

        assertEquals(Compass.NORTH, result,
                "Error: expected Compass.NORTH for input 'n' but got " + result);
    }

    /**
     * charToCompass() – CC = 5
     * Path 2: switch branch for 's' → Compass.SOUTH
     */
    @Test
    void charToCompass2() {
        Compass result = Compass.charToCompass('s');

        assertEquals(Compass.SOUTH, result,
                "Error: expected Compass.SOUTH for input 's' but got " + result);
    }

    /**
     * charToCompass() – CC = 5
     * Path 3: switch branch for 'e' → Compass.EAST
     */
    @Test
    void charToCompass3() {
        Compass result = Compass.charToCompass('e');

        assertEquals(Compass.EAST, result,
                "Error: expected Compass.EAST for input 'e' but got " + result);
    }

    /**
     * charToCompass() – CC = 5
     * Path 4: switch branch for 'o' → Compass.WEST
     */
    @Test
    void charToCompass4() {
        Compass result = Compass.charToCompass('o');

        assertEquals(Compass.WEST, result,
                "Error: expected Compass.WEST for input 'o' but got " + result);
    }

    /**
     * charToCompass() – CC = 5
     * Path 5: default branch for any other character → Compass.UNKNOWN
     */
    @Test
    void charToCompass5() {
        Compass result = Compass.charToCompass('x');

        assertEquals(Compass.UNKNOWN, result,
                "Error: expected Compass.UNKNOWN for input 'x' but got " + result);
    }
}