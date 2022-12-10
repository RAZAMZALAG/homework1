public class Tile {
    int val;
    Tile(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return Integer.toString(this.val);
    }

    public int getValueTile() {
        return val;

    }

//    public Tile setValueTile(int val) {
//        this.val = val;
//
//            return null;
//    }
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