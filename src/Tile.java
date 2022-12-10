public class Tile {
    int valueTile;
    Tile(int value) {
        valueTile = value;
        System.out.print(value);
    }


    public int getValueTile() {
        return valueTile;

    }

    public Tile setValueTile(int valueTile) {
        this.valueTile = valueTile;

            return null;
    }
}
/*
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) other;
        return value == tile.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}
*/