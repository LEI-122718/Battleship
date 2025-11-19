package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrigateTest {

    private IPosition startPos;
    private Frigate frigate;

    @BeforeEach
    void setUp() {
        startPos = new Position(0, 0);
        frigate = new Frigate(Compass.NORTH, startPos);
    }

    @AfterEach
    void tearDown() {
        frigate = null;
    }

    @Test
    void testGetSize() {
        assertEquals(4, frigate.getSize(), "Erro: getSize() devia devolver 4");
    }

    @Test
    void testConstructorNorthPositions() {
        Frigate f = new Frigate(Compass.NORTH, new Position(1, 1));
        assertEquals(4, f.getPositions().size(), "Erro: NORTH devia ter 4 posições");
        for (int i = 0; i < 4; i++) {
            assertEquals(1 + i, f.getPositions().get(i).getRow(),
                    "Erro: linha da posição não corresponde para NORTH");
            assertEquals(1, f.getPositions().get(i).getColumn(),
                    "Erro: coluna da posição não corresponde para NORTH");
        }
    }

    @Test
    void testConstructorSouthPositions() {
        Frigate f = new Frigate(Compass.SOUTH, new Position(2, 2));
        assertEquals(4, f.getPositions().size(), "Erro: SOUTH devia ter 4 posições");
        for (int i = 0; i < 4; i++) {
            assertEquals(2 + i, f.getPositions().get(i).getRow(),
                    "Erro: linha da posição não corresponde para SOUTH");
            assertEquals(2, f.getPositions().get(i).getColumn(),
                    "Erro: coluna da posição não corresponde para SOUTH");
        }
    }

    @Test
    void testConstructorEastPositions() {
        Frigate f = new Frigate(Compass.EAST, new Position(3, 3));
        assertEquals(4, f.getPositions().size(), "Erro: EAST devia ter 4 posições");
        for (int i = 0; i < 4; i++) {
            assertEquals(3, f.getPositions().get(i).getRow(),
                    "Erro: linha da posição não corresponde para EAST");
            assertEquals(3 + i, f.getPositions().get(i).getColumn(),
                    "Erro: coluna da posição não corresponde para EAST");
        }
    }

    @Test
    void testConstructorWestPositions() {
        Frigate f = new Frigate(Compass.WEST, new Position(4, 4));
        assertEquals(4, f.getPositions().size(), "Erro: WEST devia ter 4 posições");
        for (int i = 0; i < 4; i++) {
            assertEquals(4, f.getPositions().get(i).getRow(),
                    "Erro: linha da posição não corresponde para WEST");
            assertEquals(4 + i, f.getPositions().get(i).getColumn(),
                    "Erro: coluna da posição não corresponde para WEST");
        }
    }

    @Test
    void testInvalidBearingThrows() {
        IPosition pos = new Position(0, 0);
        assertThrows(AssertionError.class, () -> new Frigate(null, pos),
                "Erro: devia lançar AssertionError para bearing null devido ao assert no Ship");
    }

    @Test
    void testStillFloatingAndShoot() {
        Frigate f = new Frigate(Compass.NORTH, new Position(0, 0));
        assertTrue(f.stillFloating(), "Erro: fragata deveria estar flutuando no início");

        // Dispara em todas as posições
        for (IPosition p : f.getPositions()) {
            f.shoot(p);
        }
        assertFalse(f.stillFloating(), "Erro: fragata não deveria estar flutuando depois de receber tiros em todas as posições");
    }

    @Test
    void testOccupies() {
        Frigate f = new Frigate(Compass.EAST, new Position(0, 0));
        for (IPosition p : f.getPositions()) {
            assertTrue(f.occupies(p), "Erro: fragata devia ocupar a posição " + p);
        }
        assertFalse(f.occupies(new Position(10, 10)), "Erro: fragata não devia ocupar posição não atribuída");
    }
}
