package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarrackTest {

    private Carrack carrack;

    @BeforeEach
    void setUp() {
        // instância padrão
        carrack = new Carrack(Compass.NORTH, new Position(0, 0));
    }

    @AfterEach
    void tearDown() {
        carrack = null;
    }

    // ---------------------------------------------------------
    //   CONSTRUCTOR TESTS – VALORES VÁLIDOS
    // ---------------------------------------------------------

    @Test
    void constructorNorth() {
        Carrack c = new Carrack(Compass.NORTH, new Position(2, 3));

        assertAll("Constructor NORTH",
                () -> assertEquals(3, c.getPositions().size(),
                        "Error: expected size 3"),
                () -> assertEquals(2, c.getPositions().get(0).getRow(),
                        "Error: wrong row for first position"),
                () -> assertEquals(3, c.getPositions().get(0).getColumn(),
                        "Error: wrong column for first position"),
                () -> assertEquals(4, c.getPositions().get(2).getRow(),
                        "Error: wrong row for third position")
        );
    }

    @Test
    void constructorSouth() {
        Carrack c = new Carrack(Compass.SOUTH, new Position(1, 1));

        assertAll("Constructor SOUTH",
                () -> assertEquals(3, c.getPositions().size(),
                        "Error: expected size 3"),
                () -> assertEquals(1, c.getPositions().get(0).getRow(),
                        "Error: wrong row for first position"),
                () -> assertEquals(3, c.getPositions().get(2).getRow(),
                        "Error: wrong row for third position")
        );
    }

    @Test
    void constructorEast() {
        Carrack c = new Carrack(Compass.EAST, new Position(4, 2));

        assertAll("Constructor EAST",
                () -> assertEquals(3, c.getPositions().size(),
                        "Error: expected size 3"),
                () -> assertEquals(2, c.getPositions().get(0).getColumn(),
                        "Error: wrong column for first position"),
                () -> assertEquals(4, c.getPositions().get(0).getRow(),
                        "Error: wrong row for first position"),
                () -> assertEquals(4, c.getPositions().get(1).getRow(),
                        "Error: wrong row for second position"),
                () -> assertEquals(4, c.getPositions().get(2).getRow(),
                        "Error: wrong row for third position")
        );
    }

    @Test
    void constructorWest() {
        Carrack c = new Carrack(Compass.WEST, new Position(5, 5));

        assertAll("Constructor WEST",
                () -> assertEquals(3, c.getPositions().size(),
                        "Error: expected size 3"),
                () -> assertEquals(5, c.getPositions().get(0).getRow(),
                        "Error: wrong row"),
                () -> assertEquals(5, c.getPositions().get(0).getColumn(),
                        "Error: wrong column for first position"),
                () -> assertEquals(7, c.getPositions().get(2).getColumn(),
                        "Error: wrong column for third position")
        );
    }

    // ---------------------------------------------------------
    //   CONSTRUCTOR TESTS – VALORES INVÁLIDOS
    // ---------------------------------------------------------

    /**
     * Path: null bearing → AssertionError esperado.
     */
    @Test
    void constructorInvalidBearing() {
        assertThrows(AssertionError.class,
                () -> new Carrack(null, new Position(0, 0)),
                "Expected AssertionError for null bearing"
        );
    }

    // ---------------------------------------------------------
    //   GETSIZE
    // ---------------------------------------------------------

    @Test
    void getSize() {
        assertEquals(3, carrack.getSize(),
                "Error: expected size 3 but got different value");
    }
}
