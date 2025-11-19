package iscteiul.ista.battleship;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TasksTest {

    @Test
    void taskA() {
        // Não testável usa System.in
    }

    @Test
    void taskB() {
        // Não testável usa System.in
    }

    @Test
    void taskC() {
        // Não testável usa System.in
    }

    @Test
    void taskD() {
        // Não testável usa System.in
    }


    // -------------------------------------------------------------
    // readPosition
    // -------------------------------------------------------------
    @Test
    void readPosition() {
        Scanner sc = new Scanner("8 3");

        Position p = Tasks.readPosition(sc);

        assertEquals(8, p.getRow(), "Error: expected row 8");
        assertEquals(3, p.getColumn(), "Error: expected column 3");
    }


    // -------------------------------------------------------------
    // readShip
    // -------------------------------------------------------------
    @Test
    void readShip() {
        Scanner sc = new Scanner("barca 1 1 n");

        Ship s = Tasks.readShip(sc);

        assertNotNull(s, "Ship should not be null");
        assertEquals("Barca", s.getCategory());
        assertEquals(1, s.getPosition().getRow());
        assertEquals(1, s.getPosition().getColumn());
    }


    // -------------------------------------------------------------
    // buildFleet
    // -------------------------------------------------------------
    @Test
    void buildFleet() {

        StringBuilder sb = new StringBuilder();

        // buildFleet lê FLEET_SIZE + 1 navios
        for (int i = 0; i <= IFleet.FLEET_SIZE; i++) {
            sb.append("barca ").append(i).append(" 0 n ");
        }

        Scanner sc = new Scanner(sb.toString());

        Fleet f = Tasks.buildFleet(sc);

        assertEquals(
                IFleet.FLEET_SIZE + 1,
                f.getShips().size(),
                "Error: buildFleet should add FLEET_SIZE + 1 ships"
        );
    }


    // -------------------------------------------------------------
    // firingRound
    // -------------------------------------------------------------
    @Test
    void firingRound() {

        Fleet fleet = new Fleet();
        Ship barca = Ship.buildShip("barca", Compass.NORTH, new Position(5, 5));
        fleet.getShips().add(barca);

        Game game = new Game(fleet);

        // 3 tiros: 1 acerto, 1 falhado, 1 repetido
        Scanner sc = new Scanner("5 5 0 0 5 5");

        Tasks.firingRound(sc, game);

        assertEquals(1, game.getHits(), "Expected exactly 1 hit");
        assertEquals(1, game.getInvalidShots(), "Expected exactly 1 invalid shot");
        assertEquals(1, game.getRepeatedShots(), "Expected exactly 1 repeated shot");
    }
}
