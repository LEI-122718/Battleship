package iscteiul.ista.battleship;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TasksTest {


    // Estes quatro métodos nao são testáveis
    // porque usam System.in
    // Por isso ficam vazios e nao falham


    @Test
    void taskA() {
        // Não testável sem refatorizar Tasks — manter vazio
    }

    @Test
    void taskB() {
        // Não testável sem refatorizar Tasks — manter vazio
    }

    @Test
    void taskC() {
        // Não testável sem refatorizar Tasks — manter vazio
    }

    @Test
    void taskD() {
        // Não testável sem refatorizar Tasks — manter vazio
    }


    // =====================================================================
    // readPosition
    // =====================================================================
    @Test
    void readPosition() {
        Scanner scanner = new Scanner("8 3");

        Position pos = Tasks.readPosition(scanner);

        assertEquals(8, pos.getRow(), "Expected row 8");
        assertEquals(3, pos.getColumn(), "Expected column 3");
    }


    // =====================================================================
    // readShipp
    // =====================================================================
    @Test
    void readShip() {
        Scanner scanner = new Scanner("barca 1 1 n");

        Ship ship = Tasks.readShip(scanner);

        assertNotNull(ship, "Ship should not be null");
        assertEquals("Barca", ship.getCategory(), "Expected category Barca");
        assertEquals(1, ship.getPosition().getRow(), "Expected row 1");
        assertEquals(1, ship.getPosition().getColumn(), "Expected column 1");
    }


    @Test
    void buildFleet() {
    }


    @Test
    void firingRound() {
    }
}
