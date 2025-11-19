package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    private Position posUnderTest;

    @BeforeEach
    void setUp() {
        // Posição base usada na maioria dos testes
        posUnderTest = new Position(3, 7);
    }

    @Test
    void getRow() {
        int row = posUnderTest.getRow();

        assertEquals(3, row,
                "Error: expected row 3 but got " + row);
    }

    @Test
    void getColumn() {
        int col = posUnderTest.getColumn();

        assertEquals(7, col,
                "Error: expected column 7 but got " + col);
    }

    @Test
    void testHashCode() {
        Position p1 = new Position(2, 5);
        Position p2 = new Position(2, 5); // mesmas coords → deve ser igual

        int hash1 = p1.hashCode();
        int hash2 = p2.hashCode();

        assertEquals(hash1, hash2,
                "Error: expected equal hash codes for equal positions but got "
                        + hash1 + " and " + hash2);
    }

    @Test
    void testEquals() {
        Position sameRef = posUnderTest;                 // mesmo objecto
        Position sameValues = new Position(3, 7);        // mesmas coordenadas
        Position differentRow = new Position(4, 7);      // linha diferente
        Position differentCol = new Position(3, 8);      // coluna diferente
        String notAPosition = "not a position";

        assertAll("Error: equals() did not behave as expected",
                () -> assertTrue(posUnderTest.equals(sameRef),
                        "Error: expected position to be equal to itself (same reference)"),
                () -> assertTrue(posUnderTest.equals(sameValues),
                        "Error: expected positions with same row/column to be equal"),
                () -> assertFalse(posUnderTest.equals(differentRow),
                        "Error: expected positions with different rows to be not equal"),
                () -> assertFalse(posUnderTest.equals(differentCol),
                        "Error: expected positions with different columns to be not equal"),
                () -> assertFalse(posUnderTest.equals(notAPosition),
                        "Error: expected equals to return false when comparing with non-IPosition object")
        );
    }

    @Test
    void isAdjacentTo() {
        Position same = new Position(3, 7);       // (0,0) → adjacente
        Position up = new Position(2, 7);         // (-1,0) → adjacente
        Position right = new Position(3, 8);      // (0,+1) → adjacente
        Position diag = new Position(4, 8);       // (+1,+1) → adjacente
        Position farRow = new Position(5, 7);     // (+2,0) → NÃO adjacente
        Position farCol = new Position(3, 9);     // (0,+2) → NÃO adjacente
        Position farBoth = new Position(6, 10);   // (+3,+3) → NÃO adjacente

        boolean sameAdj = posUnderTest.isAdjacentTo(same);
        boolean upAdj = posUnderTest.isAdjacentTo(up);
        boolean rightAdj = posUnderTest.isAdjacentTo(right);
        boolean diagAdj = posUnderTest.isAdjacentTo(diag);
        boolean farRowAdj = posUnderTest.isAdjacentTo(farRow);
        boolean farColAdj = posUnderTest.isAdjacentTo(farCol);
        boolean farBothAdj = posUnderTest.isAdjacentTo(farBoth);

        assertAll("Error: isAdjacentTo() did not behave as expected",
                () -> assertTrue(sameAdj,
                        "Error: expected same position to be considered adjacent (distance 0)"),
                () -> assertTrue(upAdj,
                        "Error: expected position one row away to be adjacent"),
                () -> assertTrue(rightAdj,
                        "Error: expected position one column away to be adjacent"),
                () -> assertTrue(diagAdj,
                        "Error: expected diagonal neighbour (1 row, 1 column) to be adjacent"),
                () -> assertFalse(farRowAdj,
                        "Error: expected position with row difference > 1 not to be adjacent"),
                () -> assertFalse(farColAdj,
                        "Error: expected position with column difference > 1 not to be adjacent"),
                () -> assertFalse(farBothAdj,
                        "Error: expected position far in both row and column not to be adjacent")
        );
    }

    @Test
    void occupy() {
        // Inicialmente não ocupada
        assertFalse(posUnderTest.isOccupied(),
                "Error: expected position to be not occupied before calling occupy()");

        posUnderTest.occupy();

        assertTrue(posUnderTest.isOccupied(),
                "Error: expected position to be occupied after calling occupy() but isOccupied() returned false");
    }

    @Test
    void shoot() {
        // Inicialmente não atingida
        assertFalse(posUnderTest.isHit(),
                "Error: expected position to be not hit before calling shoot()");

        posUnderTest.shoot();

        assertTrue(posUnderTest.isHit(),
                "Error: expected position to be hit after calling shoot() but isHit() returned false");
    }

    @Test
    void isOccupied() {
        // Verifica estado inicial
        assertFalse(posUnderTest.isOccupied(),
                "Error: expected position to be not occupied initially but isOccupied() returned true");

        // Depois de ocupar → true
        posUnderTest.occupy();

        assertTrue(posUnderTest.isOccupied(),
                "Error: expected position to be occupied after occupy() but isOccupied() returned false");
    }

    @Test
    void isHit() {
        // Verifica estado inicial
        assertFalse(posUnderTest.isHit(),
                "Error: expected position to be not hit initially but isHit() returned true");

        // Depois de disparar → true
        posUnderTest.shoot();

        assertTrue(posUnderTest.isHit(),
                "Error: expected position to be hit after shoot() but isHit() returned false");
    }

    @Test
    void testToString() {
        String result = posUnderTest.toString();
        String expected = "Linha = 3 Coluna = 7";

        assertEquals(expected, result,
                "Error: expected toString \"" + expected + "\" but got \"" + result + "\"");
    }
}
