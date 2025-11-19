package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FleetTest {

    private Fleet fleet;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        fleet = new Fleet();
        System.setOut(new PrintStream(outContent)); // capturar stdout
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    // ----------------------------------------------------------------------
    @Test
    void printShips() {
        IShip s1 = new Barge(Compass.NORTH, new Position(0,0));
        IShip s2 = new Barge(Compass.EAST, new Position(1,1));

        Fleet.printShips(List.of(s1,s2));

        String output = outContent.toString();
        assertTrue(output.contains(s1.toString()), "Erro: printShips não imprimiu o primeiro navio");
        assertTrue(output.contains(s2.toString()), "Erro: printShips não imprimiu o segundo navio");
    }

    // ----------------------------------------------------------------------
    @Test
    void getShips() {
        assertTrue(fleet.getShips().isEmpty(),
                "Erro: nova frota devia começar vazia");

        IShip barge = new Barge(Compass.NORTH, new Position(2,2));
        fleet.getShips().add(barge);

        assertEquals(1, fleet.getShips().size(),
                "Erro: getShips não devolveu lista actualizada");
    }

    // ----------------------------------------------------------------------
    @Test
    void addShip() {
        IShip s1 = new Barge(Compass.NORTH, new Position(0,0));

        assertTrue(fleet.addShip(s1), "Erro: addShip falhou ao adicionar navio válido");
        assertEquals(1, fleet.getShips().size(), "Erro: frota não contém o navio adicionado");

        // barco fora do tabuleiro → deve falhar
        IShip invalid = new Barge(Compass.NORTH, new Position(30,30));
        assertFalse(fleet.addShip(invalid), "Erro: addShip não devia aceitar navio fora do tabuleiro");

        // colisão/too-close → deve falhar
        IShip tooClose = new Barge(Compass.NORTH, new Position(0,1));
        assertFalse(fleet.addShip(tooClose), "Erro: addShip devia impedir colocar navios demasiado próximos");
    }

    // ----------------------------------------------------------------------
    @Test
    void getShipsLike() {
        IShip s1 = new Barge(Compass.NORTH, new Position(0,0));
        IShip s2 = new Caravel(Compass.NORTH, new Position(2,2));
        IShip s3 = new Barge(Compass.SOUTH, new Position(4,4));

        fleet.addShip(s1);
        fleet.addShip(s2);
        fleet.addShip(s3);

        List<IShip> barges = fleet.getShipsLike("Barca");
        assertEquals(2, barges.size(), "Erro: getShipsLike devia devolver 2 barcas");

        List<IShip> caravels = fleet.getShipsLike("Caravela");
        assertEquals(1, caravels.size(), "Erro: getShipsLike devia devolver 1 caravela");

        List<IShip> none = fleet.getShipsLike("Submarino");
        assertTrue(none.isEmpty(), "Erro: getShipsLike devia devolver lista vazia");
    }

    // ----------------------------------------------------------------------
    @Test
    void getFloatingShips() {
        IShip s1 = new Barge(Compass.NORTH, new Position(0,0));
        IShip s2 = new Caravel(Compass.NORTH, new Position(5,5));
        IShip s3 = new Barge(Compass.SOUTH, new Position(8,8));

        fleet.addShip(s1);
        fleet.addShip(s2);
        fleet.addShip(s3);

        // afundar um
        s3.getPositions().get(0).shoot();

        List<IShip> flutuantes = fleet.getFloatingShips();

        assertEquals(2, flutuantes.size(),
                "Erro: getFloatingShips devia devolver 2 navios flutuantes");
        assertTrue(flutuantes.contains(s1), "Erro: s1 deveria estar flutuante");
        assertTrue(flutuantes.contains(s2), "Erro: s2 deveria estar flutuante");
        assertFalse(flutuantes.contains(s3), "Erro: s3 não devia estar flutuante");
    }

    // ----------------------------------------------------------------------
    @Test
    void shipAt() {
        IShip s1 = new Barge(Compass.NORTH, new Position(0,0));
        IShip s2 = new Barge(Compass.NORTH, new Position(4,4));

        fleet.addShip(s1);
        fleet.addShip(s2);

        assertEquals(s1, fleet.shipAt(new Position(0,0)),
                "Erro: shipAt devia devolver o navio na posição 0,0");
        assertEquals(s2, fleet.shipAt(new Position(4,4)),
                "Erro: shipAt devia devolver o navio na posição 4,4");
        assertNull(fleet.shipAt(new Position(2,2)),
                "Erro: shipAt devia devolver null para posição sem navio");
    }

    // ----------------------------------------------------------------------
    @Test
    void printStatus() {
        IShip s1 = new Barge(Compass.NORTH, new Position(0,0));
        IShip s2 = new Caravel(Compass.NORTH, new Position(2,2));

        fleet.addShip(s1);
        fleet.addShip(s2);

        fleet.printStatus();

        String output = outContent.toString();

        // verifica se imprimiu navios, flutuantes e categorias
        assertTrue(output.contains("Barca"), "Erro: printStatus devia listar barca");
        assertTrue(output.contains("Caravela"), "Erro: printStatus devia listar caravela");
    }

    // ----------------------------------------------------------------------
    @Test
    void printShipsByCategory() {
        IShip s1 = new Barge(Compass.NORTH, new Position(0,0));
        IShip s2 = new Barge(Compass.NORTH, new Position(3,3));

        fleet.addShip(s1);
        fleet.addShip(s2);

        fleet.printShipsByCategory("Barca");

        String output = outContent.toString();
        assertEquals(2, output.lines().count(),
                "Erro: printShipsByCategory devia imprimir exactamente 2 barcas");
    }

    // ----------------------------------------------------------------------
    @Test
    void printFloatingShips() {
        IShip s1 = new Barge(Compass.NORTH, new Position(0,0));
        IShip s2 = new Barge(Compass.NORTH, new Position(3,3));

        fleet.addShip(s1);
        fleet.addShip(s2);

        // afundar 1
        s2.getPositions().get(0).shoot();

        fleet.printFloatingShips();

        String output = outContent.toString();

        assertTrue(output.contains(s1.toString()), "Erro: s1 devia aparecer como flutuante");
        assertFalse(output.contains(s2.toString()), "Erro: s2 não devia aparecer pois está afundado");
    }

    // ----------------------------------------------------------------------
    @Test
    void printAllShips() {
        IShip s1 = new Barge(Compass.NORTH, new Position(0,0));
        IShip s2 = new Barge(Compass.NORTH, new Position(2,2));

        fleet.addShip(s1);
        fleet.addShip(s2);

        fleet.printAllShips();

        String output = outContent.toString();
        assertTrue(output.contains(s1.toString()), "Erro: printAllShips devia imprimir s1");
        assertTrue(output.contains(s2.toString()), "Erro: printAllShips devia imprimir s2");
    }
}
