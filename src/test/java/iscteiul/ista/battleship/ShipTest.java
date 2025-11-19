package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    private Ship ship;
    private Position basePos;

    @BeforeEach
    void setUp() {
        basePos = new Position(5, 5);
        ship = new Barge(Compass.NORTH, basePos);
    }

    @AfterEach
    void tearDown() {
        ship = null;
        basePos = null;
    }

    @Test
    void buildShip() {
        Ship s1 = Ship.buildShip("barca", Compass.SOUTH, new Position(1, 1));
        assertNotNull(s1, "Erro: buildShip deveria criar uma Barge.");
        assertTrue(s1 instanceof Barge, "Erro: buildShip deveria retornar Barge.");

        Ship s2 = Ship.buildShip("submarino", Compass.NORTH, new Position(0, 0));
        assertNull(s2, "Erro: tipos inválidos devem retornar null.");
    }

    @Test
    void getCategory() {
        assertEquals("Barca", ship.getCategory(),
                "Erro: categoria esperada é 'Barca'.");
    }

    @Test
    void getPositions() {
        assertNotNull(ship.getPositions(), "Erro: lista de posições não deve ser null.");
        assertEquals(1, ship.getPositions().size(),
                "Erro: Barge deve ter exatamente 1 posição.");
    }

    @Test
    void getPosition() {
        assertEquals(basePos.getRow(), ship.getPosition().getRow(),
                "Erro: linha da posição inicial incorrecta.");
        assertEquals(basePos.getColumn(), ship.getPosition().getColumn(),
                "Erro: coluna da posição inicial incorrecta.");
    }

    @Test
    void getBearing() {
        assertEquals(Compass.NORTH, ship.getBearing(),
                "Erro: bearing inicial incorrecto.");
    }

    @Test
    void stillFloating() {
        assertTrue(ship.stillFloating(),
                "Erro: Barge inicialmente deveria estar a flutuar.");

        ship.getPositions().get(0).shoot();
        assertFalse(ship.stillFloating(),
                "Erro: Barge atingida deveria deixar de flutuar.");
    }

    @Test
    void getTopMostPos() {
        assertEquals(5, ship.getTopMostPos(),
                "Erro: topo da Barge deve ser sua única linha.");
    }

    @Test
    void getBottomMostPos() {
        assertEquals(5, ship.getBottomMostPos(),
                "Erro: fundo da Barge deve ser sua única linha.");
    }

    @Test
    void getLeftMostPos() {
        assertEquals(5, ship.getLeftMostPos(),
                "Erro: coluna mais à esquerda deve ser 5.");
    }

    @Test
    void getRightMostPos() {
        assertEquals(5, ship.getRightMostPos(),
                "Erro: coluna mais à direita deve ser 5.");
    }

    @Test
    void occupies() {
        Position p1 = new Position(5, 5);
        assertTrue(ship.occupies(p1),
                "Erro: Barge deveria ocupar (5,5).");

        Position p2 = new Position(4, 4);
        assertFalse(ship.occupies(p2),
                "Erro: Barge não deveria ocupar (4,4).");
    }

    @Test
    void tooCloseTo() {
        Ship other = new Barge(Compass.NORTH, new Position(4, 4)); // adjacente

        assertTrue(ship.tooCloseTo(other),
                "Erro: barcos adjacentes deveriam estar demasiado perto.");

        Ship far = new Barge(Compass.NORTH, new Position(10, 10));
        assertFalse(ship.tooCloseTo(far),
                "Erro: barcos distantes não deveriam estar demasiado perto.");
    }

    @Test
    void testTooCloseTo() {
        Position adj = new Position(4, 4);
        assertTrue(ship.tooCloseTo(adj),
                "Erro: posição adjacente deveria ser considerada demasiado próxima.");

        Position far = new Position(10, 10);
        assertFalse(ship.tooCloseTo(far),
                "Erro: posição distante não deveria ser considerada demasiado próxima.");
    }

    @Test
    void shoot() {
        Position target = new Position(5, 5);
        ship.shoot(target);

        assertTrue(ship.getPositions().get(0).isHit(),
                "Erro: posição deveria ter sido marcada como atingida.");

        Position miss = new Position(0, 0);
        ship.shoot(miss); // não deve atingir nada
        assertTrue(ship.getPositions().get(0).isHit(),
                "Erro: só a posição correspondente deveria estar atingida.");
    }

    @Test
    void testToString() {
        String expected = "[Barca n " + basePos + "]";
        assertEquals(expected, ship.toString(),
                "Erro: toString() não corresponde ao esperado.");
    }
}
