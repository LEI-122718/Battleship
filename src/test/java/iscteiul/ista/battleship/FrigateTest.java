package iscteiul.ista.battleship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrigateTest {

    @Test
    void getSize() {
        IPosition pos = new Position(0, 0);

        // criar fragata válida (orientação Norte)
        Frigate frigate = new Frigate(Compass.NORTH, pos);

        assertEquals(4, frigate.getSize(),
                "Erro: getSize() da Frigate devia devolver 4");
    }
}
