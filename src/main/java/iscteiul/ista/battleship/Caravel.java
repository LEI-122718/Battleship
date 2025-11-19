package iscteiul.ista.battleship;

public class Caravel extends Ship {
    private static final Integer SIZE = 2;
    private static final String NAME = "Caravela";

    /**
     * @param bearing the bearing where the Caravel heads to
     * @param pos     initial point for positioning the Caravel
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

        switch (bearing) {
            case NORTH -> {
                for (int i = 0; i < SIZE; i++) {
                    getPositions().add(new Position(pos.getRow() - i, pos.getColumn()));
                }
            }
            case SOUTH -> {
                for (int i = 0; i < SIZE; i++) {
                    getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
                }
            }
            case EAST -> {
                for (int i = 0; i < SIZE; i++) {
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
                }
            }
            case WEST -> {
                for (int i = 0; i < SIZE; i++) {
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() - i));
                }
            }
            default -> throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }
    }

    @Override
    public Integer getSize() {
        return SIZE;
    }
}
