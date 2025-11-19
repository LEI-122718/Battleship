package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    private Ship ship;
    private IPosition pos;

    @BeforeEach
    void setUp() {
        pos = new Position(3, 4);
        ship = new Barge(Compass.NORTH, pos);
    }

    @AfterEach
    void tearDown() {
        ship = null;
        pos = null;
    }

    // -------------------------------------------------------------------
    // buildShip()
    // -------------------------------------------------------------------
    @Test
    void buildShip_AllValidTypes() {
        Position p = new Position(1,1);

        assertTrue(Ship.buildShip("barca", Compass.NORTH, p) instanceof Barge);
        assertTrue(Ship.buildShip("caravela", Compass.NORTH, p) instanceof Caravel);
        assertTrue(Ship.buildShip("nau", Compass.NORTH, p) instanceof Carrack);
        assertTrue(Ship.buildShip("fragata", Compass.NORTH, p) instanceof Frigate);
        assertTrue(Ship.buildShip("galeao", Compass.NORTH, p) instanceof Galleon);
    }

    @Test
    void buildShip_InvalidType_ReturnsNull() {
        assertNull(Ship.buildShip("submarine", Compass.NORTH, new Position(0,0)),
                "Erro: tipo inválido devia devolver null");
    }

    // -------------------------------------------------------------------
    // getCategory()
    // -------------------------------------------------------------------
    @Test
    void getCategory() {
        assertEquals("Barca", ship.getCategory(),
                "Erro: getCategory devolveu categoria errada");
    }

    // -------------------------------------------------------------------
    // getPositions()
    // -------------------------------------------------------------------
    @Test
    void getPositions() {
        List<IPosition> list = ship.getPositions();
        assertEquals(1, list.size());
        assertEquals(3, list.get(0).getRow());
        assertEquals(4, list.get(0).getColumn());
    }

    // -------------------------------------------------------------------
    // getPosition()
    // -------------------------------------------------------------------
    @Test
    void getPosition() {
        assertEquals(pos, ship.getPosition());
    }

    // -------------------------------------------------------------------
    // getBearing()
    // -------------------------------------------------------------------
    @Test
    void getBearing() {
        assertEquals(Compass.NORTH, ship.getBearing());
    }

    // -------------------------------------------------------------------
    // stillFloating()
    // CC = 2 (um ramo do if e o return final)
    // -------------------------------------------------------------------
    @Test
    void stillFloating_WhenNotHit() {
        assertTrue(ship.stillFloating(),
                "Erro: navio intacto devia estar a flutuar");
    }

    @Test
    void stillFloating_WhenFullyHit() {
        ship.getPositions().get(0).shoot();
        assertFalse(ship.stillFloating(),
                "Erro: navio totalmente atingido não devia flutuar");
    }

    // -------------------------------------------------------------------
    // getTopMostPos()
    // -------------------------------------------------------------------
    @Test
    void getTopMostPos() {
        assertEquals(3, ship.getTopMostPos());
    }

    // Barge só tem uma posição; criar teste com Caravel para múltiplos ramos
    @Test
    void getTopMostPos_MultiplePositions() {
        Ship s2 = new Caravel(Compass.SOUTH, new Position(0,0));
        assertEquals(0, s2.getTopMostPos());
    }

    // -------------------------------------------------------------------
    // getBottomMostPos()
    // -------------------------------------------------------------------
    @Test
    void getBottomMostPos() {
        assertEquals(3, ship.getBottomMostPos());
    }

    @Test
    void getBottomMostPos_MultiplePositions() {
        Ship s2 = new Caravel(Compass.SOUTH, new Position(0,0));
        assertEquals(1, s2.getBottomMostPos());
    }

    // -------------------------------------------------------------------
    // getLeftMostPos()
    // -------------------------------------------------------------------
    @Test
    void getLeftMostPos() {
        assertEquals(4, ship.getLeftMostPos());
    }

    @Test
    void getLeftMostPos_MultiplePositions() {
        Ship s2 = new Caravel(Compass.EAST, new Position(5,5));
        assertEquals(5, s2.getLeftMostPos());
    }

    // -------------------------------------------------------------------
    // getRightMostPos()
    // -------------------------------------------------------------------
    @Test
    void getRightMostPos() {
        assertEquals(4, ship.getRightMostPos());
    }

    @Test
    void getRightMostPos_MultiplePositions() {
        Ship s2 = new Caravel(Compass.EAST, new Position(5,5));
        assertEquals(6, s2.getRightMostPos());
    }

    // -------------------------------------------------------------------
    // occupies()
    // CC = 2 (if true / false)
    // -------------------------------------------------------------------
    @Test
    void occupies_True() {
        IPosition same = new Position(3,4);
        assertTrue(ship.occupies(same));
    }

    @Test
    void occupies_False() {
        IPosition other = new Position(10,10);
        assertFalse(ship.occupies(other));
    }

    // -------------------------------------------------------------------
    // tooCloseTo(IPosition)
    // CC = 2 (adjacent vs not adjacent)
    // -------------------------------------------------------------------
    @Test
    void tooCloseTo_PositionAdjacent() {
        IPosition adj = new Position(4,4);
        assertTrue(ship.tooCloseTo(adj),
                "Erro: posição adjacente devia dar tooCloseTo = true");
    }

    @Test
    void tooCloseTo_PositionNotAdjacent() {
        IPosition far = new Position(10,10);
        assertFalse(ship.tooCloseTo(far),
                "Erro: posição distante devia dar tooCloseTo = false");
    }

    // -------------------------------------------------------------------
    // tooCloseTo(IShip)
    // CC = 2 (alguma adjacente / nenhuma adjacente)
    // -------------------------------------------------------------------
    @Test
    void tooCloseTo_ShipTooClose() {
        Ship s2 = new Barge(Compass.NORTH, new Position(4,4));
        assertTrue(ship.tooCloseTo(s2));
    }

    @Test
    void tooCloseTo_ShipNotClose() {
        Ship s2 = new Barge(Compass.NORTH, new Position(10,10));
        assertFalse(ship.tooCloseTo(s2));
    }

    // -------------------------------------------------------------------
    // shoot()
    // CC = 2 (igual / não igual)
    // -------------------------------------------------------------------
    @Test
    void shoot_HitsPosition() {
        ship.shoot(new Position(3,4));
        assertTrue(ship.getPositions().get(0).isHit(),
                "Erro: posição devia ter sido marcada como atingida");
    }

    @Test
    void shoot_DoesNothingIfNotSamePosition() {
        ship.shoot(new Position(9,9));
        assertFalse(ship.getPositions().get(0).isHit(),
                "Erro: posição não devia ser atingida");
    }

    // -------------------------------------------------------------------
    // toString()
    // -------------------------------------------------------------------
    @Test
    void testToString() {
        String expected = "[Barca n " + pos + "]";
        assertEquals(expected, ship.toString());
    }
}
