package iscteiul.ista.battleship;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GalleonTest {

    @Test
    void getSize() {
        IPosition pos = new Position(0, 0);
        Galleon g = new Galleon(Compass.NORTH, pos);

        assertEquals(5, g.getSize(),
                "Erro: getSize() do Galleon devia devolver 5");
    }

    @Test
    void constructorNorthPositions() {
        IPosition origin = new Position(2, 3);
        Galleon g = new Galleon(Compass.NORTH, origin);

        List<IPosition> p = g.getPositions();
        assertEquals(5, p.size(), "Erro: Galleon NORTH devia ter 5 posições");

        // posições esperadas (fillNorth)
        assertTrue(p.contains(new Position(2, 3)), "Erro: falta posição (2,3)");
        assertTrue(p.contains(new Position(2, 4)), "Erro: falta posição (2,4)");
        assertTrue(p.contains(new Position(2, 5)), "Erro: falta posição (2,5)");
        assertTrue(p.contains(new Position(3, 4)), "Erro: falta posição (3,4)");
        assertTrue(p.contains(new Position(4, 4)), "Erro: falta posição (4,4)");
    }

    @Test
    void constructorSouthPositions() {
        IPosition origin = new Position(2, 3);
        Galleon g = new Galleon(Compass.SOUTH, origin);

        List<IPosition> p = g.getPositions();
        assertEquals(5, p.size(), "Erro: Galleon SOUTH devia ter 5 posições");

        // posições esperadas (fillSouth)
        assertTrue(p.contains(new Position(2, 3)), "Erro: falta posição (2,3)");
        assertTrue(p.contains(new Position(3, 3)), "Erro: falta posição (3,3)");
        assertTrue(p.contains(new Position(4, 2)), "Erro: falta posição (4,2)");
        assertTrue(p.contains(new Position(4, 3)), "Erro: falta posição (4,3)");
        assertTrue(p.contains(new Position(4, 4)), "Erro: falta posição (4,4)");
    }

    @Test
    void constructorEastPositions() {
        IPosition origin = new Position(5, 5);
        Galleon g = new Galleon(Compass.EAST, origin);

        List<IPosition> p = g.getPositions();
        assertEquals(5, p.size(), "Erro: Galleon EAST devia ter 5 posições");

        assertTrue(p.contains(new Position(5, 5)), "Erro: falta posição (5,5)");
        assertTrue(p.contains(new Position(6, 3)), "Erro: falta posição (6,3)");
        assertTrue(p.contains(new Position(6, 4)), "Erro: falta posição (6,4)");
        assertTrue(p.contains(new Position(6, 5)), "Erro: falta posição (6,5)");
        assertTrue(p.contains(new Position(7, 5)), "Erro: falta posição (7,5)");
    }


    @Test
    void constructorWestPositions() {
        IPosition origin = new Position(4, 4);
        Galleon g = new Galleon(Compass.WEST, origin);

        List<IPosition> p = g.getPositions();
        assertEquals(5, p.size(), "Erro: Galleon WEST devia ter 5 posições");

        // posições esperadas (fillWest)
        assertTrue(p.contains(new Position(4, 4)), "Erro: falta posição (4,4)");
        assertTrue(p.contains(new Position(5, 4)), "Erro: falta posição (5,4)");
        assertTrue(p.contains(new Position(5, 5)), "Erro: falta posição (5,5)");
        assertTrue(p.contains(new Position(5, 6)), "Erro: falta posição (5,6)");
        assertTrue(p.contains(new Position(6, 4)), "Erro: falta posição (6,4)");
    }

    @Test
    void constructorNullBearingThrows() {
        IPosition origin = new Position(0, 0);

        assertThrows(AssertionError.class,
                () -> new Galleon(null, origin),
                "Erro: esperado AssertionError devido ao assert no construtor de Ship");
    }

    @Test
    void constructorUnknownBearingThrows() {
        IPosition origin = new Position(0, 0);

        assertThrows(IllegalArgumentException.class,
                () -> new Galleon(Compass.UNKNOWN, origin),
                "Erro: esperado IllegalArgumentException para bearing UNKNOWN");
    }
}
