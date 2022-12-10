public class Node {

    private Vertex vertex;
    Board board;
    Tile tile;
    State state;

    private Vertex[] expand() {
        vertex.stateFather = state;
        Vertex[] allVertex = new Vertex[state.actions().length];
        for (int i = 0; i < state.actions().length; i++) {
            vertex.action = state.actions()[i];
            vertex.stateSun = state.result(vertex.action);
            allVertex[i]= this.vertex;
        }
        return expand();
    }

    private int abs(int a) {
        if (a < 0) {
            return a * -1;
        }
        return a;
    }

    private int heuristicValue() {
        this.board = board;
        Tile zeroValueTile = tile.setValueTile(0);

        int counter = 0;
        for (int i = 0; i < board.getTiles().length; i++) {
            for (int j = 0; j < board.getTiles().length; j++) {
                if (board.getTileFromIndex(i, j) == zeroValueTile) {
                    continue;
                }
                //Integer.valueOf(cols[j]).intValue()
                int i1 = (board.getTiles()[i][j] - 1) / board.getTiles().length;
                int i2;
                if (board.getTiles()[i][j] % board.getTiles().length == 0) {
                    i2 = board.getTiles().length - 1;
                } else {
                    i2 = board.getTiles()[i][j] % board.getTiles().length;
                }
                int heuristic = i1 + i2 - i - j;
                counter += abs(heuristic);
            }
        }
        return counter;
    }
}


