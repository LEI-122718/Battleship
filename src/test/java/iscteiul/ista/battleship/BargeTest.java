/**
 * Test class for class Barge.
 * Author: ${user.name}
 * Date: 2025-11-19 14:32
 *
 * Cyclomatic Complexity:
 * - constructor: 1
 * - getSize(): 1
 */

package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BargeTest {

    private Barge barge;

    @BeforeEach
    void setUp() {
        // Instância criada com rota e posição fixa
        barge = new Barge(Compass.NORTH, new Position(3, 5));
    }

    @AfterEach
    void tearDown() {
        // Libertar instância para evitar acumulação de estado entre testes
        barge = null;
    }

    /**
     * CC = 1 → apenas 1 teste necessário.
     * Testa se o construtor inicializa corretamente os valores.
     */
    @Test
    void constructor() {
        assertAll("Constructor Path",
                () -> assertEquals(1, barge.getPositions().size(),
                        "Error: constructor should initialize exactly 1 position"),
                () -> assertEquals(3, barge.getPositions().get(0).getRow(),
                        "Error: expected row 3 but got different value"),
                () -> assertEquals(5, barge.getPositions().get(0).getColumn(),
                        "Error: expected column 5 but got different value")
        );
    }

    /**
     * CC = 1 → apenas 1 teste obrigatório.
     */
    @Test
    void getSize() {
        Integer expected = 1;
        Integer actual = barge.getSize();

        assertEquals(expected, actual,
                "Error: expected size 1 but got " + actual);
    }
}
