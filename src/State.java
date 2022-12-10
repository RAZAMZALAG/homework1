/**
 * 1. פעולה לבדוק אם הגענו למצב מטרה או לא. אם האריח לא נמצא במקומו מחזיר ערך false אם הכל במקום עד איבר אחד לפני הסוף (כי האיבר האחרון הוא אפס לכן לא בודקת אותו) תחזיר trure
 */
public class State {
    Direction direction;
    Tile tile;

    private Board board;

    public State(Board board) {
        this.board = board;

    }

    int indexRow = 0;
    int indexCol = 0;

    public boolean isGoal() {
        Tile[][] tiles = board.getTiles();
        int maxValue = tiles.length * tiles.length;
        int value = 1, i, j;
        for (i = 0; i < tiles.length; i++) {
            for (j = 0; j < tiles.length; j++) {
                if (tiles[i][j].getValueTile() != value) {
                    return false;
                }
                value = (value + 1) % maxValue;
            }
        }
        return true;
    }

    /**
     * לעשות כל אריח לאן הוא יכול ללכת ספציפית
     * נוז הרחבה של כל פעולה, כל פעולה שאפשר לעשות לאריח עם האפס\
     * ערך היוריסטי לכל לוח נותנים מספר האלגוריטם לוקח את הלוח עם המספר הכי קטן (צריך להחליט איזה לוח לקחת) הוא לוקח לוח ומרחיב אותו (מבצע את האופציות)
     * לוקח את המינימום מבין הלוחות (המספרים על הלוחות) וזה הלוח שהוא מרחיב. יכול לחזור אחורה
     * ליצור מערך של פעולות
     */
    public Action[] actions() {
        int numRowCol = board.getCounter();
        int indexRowZero = board.getIndexRowZero();
        int indexColZero = board.getIndexColZero();

        if (indexRowZero == 0) {
            if (indexColZero == 0) {
                Action[] possibleDirection = new Action[2];
                indexRow = indexRowZero + 1;
                indexCol = indexColZero;
                possibleDirection[0] = new Action(board.getTiles()[indexRow][indexCol], direction.up);
                indexRow = indexRowZero;
                indexCol = indexColZero + 1;
                possibleDirection[1] = new Action(board.getTiles()[indexRow][indexCol], direction.left);
            }
            if (indexColZero == numRowCol) {
                Action[] possibleDirection = new Action[2];
                indexRow = indexRowZero + 1;
                indexCol = numRowCol;
                possibleDirection[0] = new Action(board.getTiles()[indexRow][indexCol], direction.up);
                indexRow = indexRowZero;
                indexCol = numRowCol - 1;
                possibleDirection[1] = new Action(board.getTiles()[indexRow][indexCol], direction.right);
            } else {
                Action[] possibleDirection = new Action[3];
                indexRow = indexRowZero + 1;
                indexCol = indexColZero;
                possibleDirection[0] = new Action(board.getTiles()[indexRow][indexCol], direction.up);
                indexRow = indexRowZero;
                indexCol = indexColZero - 1;
                possibleDirection[1] = new Action(board.getTiles()[indexRow][indexCol], direction.right);
                indexRow = indexRowZero;
                indexCol = indexColZero + 1;
                possibleDirection[2] = new Action(board.getTiles()[indexRow][indexCol], direction.left);
            }
        }
        if (indexRowZero == numRowCol) {
            if (indexColZero == 0) {
                Action[] possibleDirection = new Action[2];
                indexRow = indexRowZero - 1;
                indexCol = indexColZero;
                possibleDirection[0] = new Action(board.getTiles()[indexRow][indexCol], direction.down);
                indexRow = indexRowZero;
                indexCol = indexColZero + 1;
                possibleDirection[1] = new Action(board.getTiles()[indexRow][indexCol], direction.left);
            }
            if (indexColZero == numRowCol) {
                Action[] possibleDirection = new Action[2];
                indexRow = indexRowZero - 1;
                indexCol = indexColZero;
                possibleDirection[0] = new Action(board.getTiles()[indexRow][indexCol], direction.down);
                indexRow = indexRowZero;
                indexCol = indexColZero - 1;
                possibleDirection[1] = new Action(board.getTiles()[indexRow][indexCol], direction.right);
            } else {
                Action[] possibleDirection = new Action[3];
                indexRow = indexRowZero - 1;
                indexCol = indexColZero;
                possibleDirection[0] = new Action(board.getTiles()[indexRow][indexCol], direction.down);
                indexRow = indexRowZero;
                indexCol = indexColZero - 1;
                possibleDirection[1] = new Action(board.getTiles()[indexRow][indexCol], direction.right);
                indexRow = indexRowZero;
                indexCol = indexColZero + 1;
                possibleDirection[2] = new Action(board.getTiles()[indexRow][indexCol], direction.left);
            }
        } else if (indexRowZero != 0 && indexRowZero != numRowCol) {
            if (indexColZero == 0) {
                Action[] possibleDirection = new Action[3];
                indexRow = indexRowZero + 1;
                indexCol = indexColZero;
                possibleDirection[0] = new Action(board.getTiles()[indexRow][indexCol], direction.up);
                indexRow = indexRowZero - 1;
                indexCol = indexColZero;
                possibleDirection[1] = new Action(board.getTiles()[indexRow][indexCol], direction.down);
                indexRow = indexRowZero;
                indexCol = indexColZero + 1;
                possibleDirection[2] = new Action(board.getTiles()[indexRow][indexCol], direction.left);
            }
            if (indexColZero == numRowCol) {
                Action[] possibleDirection = new Action[3];
                indexRow = indexRowZero + 1;
                indexCol = indexColZero;
                possibleDirection[0] = new Action(board.getTiles()[indexRow][indexCol], direction.up);
                indexRow = indexRowZero - 1;
                indexCol = indexColZero;
                possibleDirection[1] = new Action(board.getTiles()[indexRow][indexCol], direction.down);
                indexRow = indexRowZero;
                indexCol = indexColZero - 1;
                possibleDirection[2] = new Action(board.getTiles()[indexRow][indexCol], direction.right);
            } else {
                Action[] possibleDirection = new Action[4];
                indexRow = indexRowZero + 1;
                indexCol = indexColZero;
                possibleDirection[0] = new Action(board.getTiles()[indexRow][indexCol], direction.up);
                indexRow = indexRowZero - 1;
                indexCol = indexColZero;
                possibleDirection[1] = new Action(board.getTiles()[indexRow][indexCol], direction.down);
                indexRow = indexRowZero;
                indexCol = indexColZero - 1;
                possibleDirection[2] = new Action(board.getTiles()[indexRow][indexCol], direction.right);
                indexRow = indexRowZero;
                indexCol = indexColZero + 1;
                possibleDirection[3] = new Action(board.getTiles()[indexRow][indexCol], direction.left);
            }
        }
        return actions();

    }


   public State result(Action a) {
        int numRowCol = board.getCounter();
        int indexRowZero = board.getIndexRowZero();
        int indexColZero = board.getIndexColZero();
        Tile zeroValueTile = tile.setValueTile(0);

        if (a.getDirection() == direction.up) {
            tile = board.getTileFromIndex(indexRowZero+1, indexColZero);
            board.setTileAtIndex(this.tile, indexRowZero,indexColZero);
            board.setTileAtIndex( zeroValueTile, indexRowZero+1, indexColZero);

        }
        if (a.getDirection() == direction.down) {
            tile = board.getTileFromIndex(indexRowZero-1, indexColZero);
            board.setTileAtIndex(this.tile, indexRowZero,indexColZero);
            board.setTileAtIndex(zeroValueTile, indexRowZero-1, indexColZero);

        }
        if (a.getDirection() == direction.left) {
            tile = board.getTileFromIndex(indexRowZero, indexColZero+1);
            board.setTileAtIndex(this.tile, indexRowZero,indexColZero);
            board.setTileAtIndex(zeroValueTile, indexRowZero, indexColZero+1);
        }
        if (a.getDirection() == direction.right) {
            tile = board.getTileFromIndex(indexRowZero, indexColZero-1);
            board.setTileAtIndex(this.tile, indexRowZero,indexColZero);
            board.setTileAtIndex(zeroValueTile, indexRowZero, indexColZero-1);
        }
        return new State(board);
    }



        @Override
        public boolean equals(Object other) {
            if (!(other instanceof State)) {
                return false;
            }
            State state = (State) other;
            return board.equals(state.board);
        }

        @Override
        public int hashCode() {
            return board.hashCode();
        }


}
