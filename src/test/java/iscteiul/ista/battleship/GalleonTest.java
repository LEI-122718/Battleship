package iscteiul.ista.battleship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GalleonTest {

    @Test
    void getSize() {
        IPosition pos = new Position(0, 0);

        // criar galeão válido (orientação Norte)
        Galleon galleon = new Galleon(Compass.NORTH, pos);

        assertEquals(5, galleon.getSize(),
                "Erro: getSize() do Galleon devia devolver 5");
    }
}