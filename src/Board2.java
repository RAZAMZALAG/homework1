public class Board2 {
    private int indexColZero = -1;
    private int indexRowZero = -1;

    public int getIndexColZero() {
        return indexColZero;
    }

    public int getIndexRowZero() {
        return indexRowZero;
    }

    public int getIndexTileRow() {
        return indexTileRow;
    }

    public int getIndexTileCol() {
        return indexTileCol;
    }

    public void setTileAtIndex(Tile tile, int indexRow, int indexCol) {
        this.tiles[indexRow][indexCol] = tile;
    }

    public Tile getTileFromIndex(int indexRow, int indexCol) {
        Tile tile = this.tiles[indexRow][indexCol];
        return tile;
    }

    private Tile[][] tiles = null;
    private int indexTileRow;
    private int indexTileCol;
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    Board2(String boards) {
        String[] rows = boards.split("[|]");
        int counter = rows.length, value;
        tiles = new Tile[counter][counter];
        String rowStr;
        String[] cols;

        for (int i = 0; i < counter; i++) {
            cols = rows[i].split(" ");
            if (cols.length != counter) {
                tiles = null;
                return;
            }
            for (int j = 0; j < counter; j++) {
                value = Integer.valueOf(cols[j]).intValue();
                tiles[i][j] = new Tile(value);
                System.out.println(tiles[i][j]);
                indexTileRow = i;
                indexTileCol = j;
                if (value == 0) {
                    indexColZero = j;
                    indexRowZero = i;
                    System.out.println("zero" +indexRowZero +"  - "+ indexColZero);
                }
                System.out.print( value );
            }
            System.out.println();
        }

    }
}
/**


    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}
 /*/
/**public class Board {
    private int indexColZero = -1;
    private int indexRowZero = -1;
    Tile tile;

    public int getIndexColZero() {
        return indexColZero;
    }

    public int getIndexRowZero() {
        return indexRowZero;
    }

    public int getIndexTileRow() {
        return indexTileRow;
    }

    public int getIndexTileCol() {
        return indexTileCol;
    }
    public void setTileAtIndex(Tile tile, int indexRow, int indexCol ){
        this.tiles[indexRow][indexCol]=tile;
    }

    public Tile getTileFromIndex(int indexRow, int indexCol){
        Tile tile = this.tiles[indexRow][indexCol];
        return tile;
    }

    private Tile[][] tiles = null;
    private int indexTileRow;
    private  int indexTileCol;
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    Board(String boards){
        int counter=1;
        for (int i = 0; i < boards.length(); i++){
            if (boards.charAt(i) == '|' ){
                counter++;
            }
            System.out.println(counter);
        }

        int indexTileRow = 0;
        int indexTileCol = 0;
        int[][] Tiles = new int [counter][counter];
        for (int i = 0; i < boards.length(); i++){
            if (boards.charAt(i) == ' '){
                continue;
            }
            if (boards.charAt(i) =='|'){
                this.indexTileRow++;
                this.indexTileCol = 0;
            }
            int val = Integer.valueOf(Tiles[indexTileRow][indexTileCol]).intValue();
            tile.getValueTile();
            setTileAtIndex(boards.charAt(i) ,indexTileRow, indexTileCol);
            tile.valueTile() =  Integer.valueOf(boards.charAt(i)).intValue();
            tiles[indexTileCol][indexTileRow]= Tile.valueTile();
        }

        int indexRowZero = -1;
        int indexColZero = -1;
        for(int i=0; i< tiles.length; i++){
            for(int j=0; j<tiles[0].length; j++){
                if(tiles[i][j]==0){
                    indexRowZero=i;
                    indexColZero=j;
                    break;
                }
            }
        }
        System.out.println(tiles);
    }
}
/*/