/**
 * Test class for class Caravel.
 * Author: ${user.name}
 * Date: 2025-11-19 15:10
 *
 * Cyclomatic Complexity:
 * - constructor: 5
 * - getSize(): 1
 */

package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaravelTest {

    private Caravel caravel;

    @BeforeEach
    void setUp() {
        caravel = new Caravel(Compass.NORTH, new Position(0, 0));
    }

    @AfterEach
    void tearDown() {
        caravel = null;
    }

    // ---------------------------------------------------------
    //   CONSTRUCTOR - 5 TEST PATHS
    // ---------------------------------------------------------

    /**
     * Path 1:
     * bearing = null → gera AssertionError no Ship.
     */
    @Test
    void constructor1() {
        assertThrows(AssertionError.class,
                () -> new Caravel(null, new Position(0, 0)),
                "Error: expected AssertionError when bearing is null");
    }

    /**
     * Path 2:
     * NORTH → posições verticais (descendo).
     */
    @Test
    void constructor2() {
        Caravel c = new Caravel(Compass.NORTH, new Position(2, 3));

        assertAll("Constructor NORTH",
                () -> assertEquals(2, c.getPositions().size(),
                        "Error: expected size 2"),
                () -> assertEquals(2, c.getPositions().get(0).getRow(),
                        "Error: expected starting row 2"),
                () -> assertEquals(3, c.getPositions().get(0).getColumn(),
                        "Error: expected column 3"),
                () -> assertEquals(1, c.getPositions().get(1).getRow(),
                        "Error: expected row decreasing for NORTH")
        );
    }

    /**
     * Path 3:
     * SOUTH → posições verticais (subindo).
     */
    @Test
    void constructor3() {
        Caravel c = new Caravel(Compass.SOUTH, new Position(1, 1));

        assertAll("Constructor SOUTH",
                () -> assertEquals(2, c.getPositions().size(),
                        "Error: expected size 2"),
                () -> assertEquals(1, c.getPositions().get(0).getRow(),
                        "Error: wrong initial row"),
                () -> assertEquals(2, c.getPositions().get(1).getRow(),
                        "Error: expected row increasing for SOUTH")
        );
    }

    /**
     * Path 4:
     * EAST → posições horizontais (direita).
     */
    @Test
    void constructor4() {
        Caravel c = new Caravel(Compass.EAST, new Position(5, 2));

        assertAll("Constructor EAST",
                () -> assertEquals(2, c.getPositions().size(),
                        "Error: expected size 2"),
                () -> assertEquals(2, c.getPositions().get(0).getColumn(),
                        "Error: incorrect first column"),
                () -> assertEquals(3, c.getPositions().get(1).getColumn(),
                        "Error: incorrect second column")
        );
    }

    /**
     * Path 5:
     * WEST → posições horizontais (esquerda).
     */
    @Test
    void constructor5() {
        Caravel c = new Caravel(Compass.WEST, new Position(4, 7));

        assertAll("Constructor WEST",
                () -> assertEquals(2, c.getPositions().size(),
                        "Error: expected size 2"),
                () -> assertEquals(7, c.getPositions().get(0).getColumn(),
                        "Error: incorrect first column"),
                () -> assertEquals(6, c.getPositions().get(1).getColumn(),
                        "Error: expected decreasing column for WEST")
        );
    }

    // ---------------------------------------------------------
    //   GETSIZE - CC = 1
    // ---------------------------------------------------------

    @Test
    void getSize() {
        assertEquals(2, caravel.getSize(),
                "Error: expected size 2 but got different value");
    }
}
