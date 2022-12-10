import java.util.Arrays;

///**
// * 1. פעולה לבדוק אם הגענו למצב מטרה או לא. אם האריח לא נמצא במקומו מחזיר ערך false אם הכל במקום עד איבר אחד לפני הסוף (כי האיבר האחרון הוא אפס לכן לא בודקת אותו) תחזיר trure
// */
public class State {
    private Board currentBoard;
    //    private Board prevBoard;
    private Action action;
    private int size;

    State(Board currentBoard, Action action) {
        this.action = action;
//        this.prevBoard = prevBoard;
        this.currentBoard = currentBoard;
        this.size = this.currentBoard.getBoard().length;

    }

    private Tile[][] copyBoard(){
        Tile[][] newBoard = new Tile[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newBoard[i][j] = currentBoard.getBoard()[i][j];
            }
        }
        return newBoard;
    }


    public void result (Action newAction){


        Tile[][] newBoard = copyBoard();
        this.currentBoard.getBoard()[0][0]=null;

//        System.out.println("new board: " + Arrays.toString(newBoard[0]));
//        System.out.println("sec board: " + this.currentBoard);

        int [] numLoc = findNumber(newBoard, newAction.getTile().getVal());
        int numCol; int numRow;
        numRow = numLoc[0];
        numCol = numLoc[1];
        //upswach
        Tile temp =   newBoard[numLoc[0]][numLoc[1]];
        newBoard[numRow][numCol] = newBoard[numRow-1][numCol];
        newBoard[numRow-1][numCol] = temp;


        for (int i = 0; i < newBoard.length; i++) {
            System.out.println(Arrays.toString(newBoard[i]));


        }
    }

    public boolean isGoal() {
        Tile[][] board = this.currentBoard.getBoard();
        int size = board.length;
        int value = 1, i, j;
        for (i = 0; i < size - 1; i++) {
            for (j = 0; j < size; j++) {
                if (board[i][j].getValueTile() != i * size + j + 1) {
                    return false;
                }
            }
        }
        // last row
        for (int k = 0; k < size - 1; k++) {
            if (board[size - 1][k].getValueTile() != i * size + k + 1) {
                return false;
            }
        }
        return true;
    }

    public Action[] actions() {
        Tile[][] brd = this.currentBoard.getBoard();
        int[] emptyLoc = findEmpty(brd);
        //System.out.println("empty loc: " + Arrays.toString(emptyLoc));


        Action[] actionsArray = new Action[4];
        int rows = emptyLoc[0];
        int cols = emptyLoc[1];
        int counter = 0;

        //check up
        if (rows < size - 1) {
            Tile belowTile = brd[rows + 1][cols];
            actionsArray[0] = new Action(belowTile, Direction.up);
            counter++;
            //      System.out.println("actionsarray: " + Arrays.toString(actionsArray));
        }
        //check down
        if (rows > 0) {
            Tile upperTile = brd[rows - 1][cols];
            actionsArray[1] = new Action(upperTile, Direction.down);
            counter++;
            //     System.out.println("actionsarray: " + Arrays.toString(actionsArray));
        }
        //check right
        if (cols > 0) {
            Tile leftTile = brd[rows][cols - 1];
            actionsArray[2] = new Action(leftTile, Direction.right);
            counter++;
            //    System.out.println("actionsarray: " + Arrays.toString(actionsArray));
        }
        //check left
        if (cols < size - 1) {
            Tile rightTile = brd[rows][cols + 1];
            actionsArray[3] = new Action(rightTile, Direction.left);
            counter++;
            //      System.out.println("actionsarray: " + Arrays.toString(actionsArray));
        }

        //    System.out.println("actions: " + Arrays.toString(actionsArray));
        Action[] finalArray = new Action[counter];

        int ctr2 = 0;
        for (int i = 0; i < 4; i++) {
            if (actionsArray[i] != null) {
                finalArray[ctr2] = actionsArray[i];
                ctr2++;
            }

        }
        //      System.out.println(Arrays.toString(finalArray));
        return finalArray;
    }
    private int[] findEmpty(Tile[][] brd) {
    return findNumber(brd,0);
    }

    private int[] findNumber(Tile[][] brd,int number) {
        int size = brd.length;
        int[] out = new int[2];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (brd[i][j].getVal() == number) {
                    out[0] = i;
                    out[1] = j;
                }

            }

        }
        return out;
    }


}
//    Direction direction;
//    Tile tile;
//
//    private Board2 board;
//
//    public State(Board2 board) {
//        this.board = board;
//
//    }
//
//    int indexRow = 0;
//    int indexCol = 0;
//

//
//    /**
//     * לעשות כל אריח לאן הוא יכול ללכת ספציפית
//     * נוז הרחבה של כל פעולה, כל פעולה שאפשר לעשות לאריח עם האפס\
//     * ערך היוריסטי לכל לוח נותנים מספר האלגוריטם לוקח את הלוח עם המספר הכי קטן (צריך להחליט איזה לוח לקחת) הוא לוקח לוח ומרחיב אותו (מבצע את האופציות)
//     * לוקח את המינימום מבין הלוחות (המספרים על הלוחות) וזה הלוח שהוא מרחיב. יכול לחזור אחורה
//     * ליצור מערך של פעולות
//     */
//    public Action[] actions() {
//        int numRowCol = board.getCounter();
//        int indexRowZero = board.getIndexRowZero();
//        int indexColZero = board.getIndexColZero();
//
//        if (indexRowZero == 0) {
//            if (indexColZero == 0) {
//                Action[] possibleDirection = new Action[2];
//                indexRow = indexRowZero + 1;
//                indexCol = indexColZero;
//                possibleDirection[0] = new Action(board.getTiles()[indexRow][indexCol], direction.up);
//                indexRow = indexRowZero;
//                indexCol = indexColZero + 1;
//                possibleDirection[1] = new Action(board.getTiles()[indexRow][indexCol], direction.left);
//            }
//            if (indexColZero == numRowCol) {
//                Action[] possibleDirection = new Action[2];
//                indexRow = indexRowZero + 1;
//                indexCol = numRowCol;
//                possibleDirection[0] = new Action(board.getTiles()[indexRow][indexCol], direction.up);
//                indexRow = indexRowZero;
//                indexCol = numRowCol - 1;
//                possibleDirection[1] = new Action(board.getTiles()[indexRow][indexCol], direction.right);
//            } else {
//                Action[] possibleDirection = new Action[3];
//                indexRow = indexRowZero + 1;
//                indexCol = indexColZero;
//                possibleDirection[0] = new Action(board.getTiles()[indexRow][indexCol], direction.up);
//                indexRow = indexRowZero;
//                indexCol = indexColZero - 1;
//                possibleDirection[1] = new Action(board.getTiles()[indexRow][indexCol], direction.right);
//                indexRow = indexRowZero;
//                indexCol = indexColZero + 1;
//                possibleDirection[2] = new Action(board.getTiles()[indexRow][indexCol], direction.left);
//            }
//        }
//        if (indexRowZero == numRowCol) {
//            if (indexColZero == 0) {
//                Action[] possibleDirection = new Action[2];
//                indexRow = indexRowZero - 1;
//                indexCol = indexColZero;
//                possibleDirection[0] = new Action(board.getTiles()[indexRow][indexCol], direction.down);
//                indexRow = indexRowZero;
//                indexCol = indexColZero + 1;
//                possibleDirection[1] = new Action(board.getTiles()[indexRow][indexCol], direction.left);
//            }
//            if (indexColZero == numRowCol) {
//                Action[] possibleDirection = new Action[2];
//                indexRow = indexRowZero - 1;
//                indexCol = indexColZero;
//                possibleDirection[0] = new Action(board.getTiles()[indexRow][indexCol], direction.down);
//                indexRow = indexRowZero;
//                indexCol = indexColZero - 1;
//                possibleDirection[1] = new Action(board.getTiles()[indexRow][indexCol], direction.right);
//            } else {
//                Action[] possibleDirection = new Action[3];
//                indexRow = indexRowZero - 1;
//                indexCol = indexColZero;
//                possibleDirection[0] = new Action(board.getTiles()[indexRow][indexCol], direction.down);
//                indexRow = indexRowZero;
//                indexCol = indexColZero - 1;
//                possibleDirection[1] = new Action(board.getTiles()[indexRow][indexCol], direction.right);
//                indexRow = indexRowZero;
//                indexCol = indexColZero + 1;
//                possibleDirection[2] = new Action(board.getTiles()[indexRow][indexCol], direction.left);
//            }
//        } else if (indexRowZero != 0 && indexRowZero != numRowCol) {
//            if (indexColZero == 0) {
//                Action[] possibleDirection = new Action[3];
//                indexRow = indexRowZero + 1;
//                indexCol = indexColZero;
//                possibleDirection[0] = new Action(board.getTiles()[indexRow][indexCol], direction.up);
//                indexRow = indexRowZero - 1;
//                indexCol = indexColZero;
//                possibleDirection[1] = new Action(board.getTiles()[indexRow][indexCol], direction.down);
//                indexRow = indexRowZero;
//                indexCol = indexColZero + 1;
//                possibleDirection[2] = new Action(board.getTiles()[indexRow][indexCol], direction.left);
//            }
//            if (indexColZero == numRowCol) {
//                Action[] possibleDirection = new Action[3];
//                indexRow = indexRowZero + 1;
//                indexCol = indexColZero;
//                possibleDirection[0] = new Action(board.getTiles()[indexRow][indexCol], direction.up);
//                indexRow = indexRowZero - 1;
//                indexCol = indexColZero;
//                possibleDirection[1] = new Action(board.getTiles()[indexRow][indexCol], direction.down);
//                indexRow = indexRowZero;
//                indexCol = indexColZero - 1;
//                possibleDirection[2] = new Action(board.getTiles()[indexRow][indexCol], direction.right);
//            } else {
//                Action[] possibleDirection = new Action[4];
//                indexRow = indexRowZero + 1;
//                indexCol = indexColZero;
//                possibleDirection[0] = new Action(board.getTiles()[indexRow][indexCol], direction.up);
//                indexRow = indexRowZero - 1;
//                indexCol = indexColZero;
//                possibleDirection[1] = new Action(board.getTiles()[indexRow][indexCol], direction.down);
//                indexRow = indexRowZero;
//                indexCol = indexColZero - 1;
//                possibleDirection[2] = new Action(board.getTiles()[indexRow][indexCol], direction.right);
//                indexRow = indexRowZero;
//                indexCol = indexColZero + 1;
//                possibleDirection[3] = new Action(board.getTiles()[indexRow][indexCol], direction.left);
//            }
//        }
//        return actions();
//
//    }
//
//
//   public State result(Action a) {
//        int numRowCol = board.getCounter();
//        int indexRowZero = board.getIndexRowZero();
//        int indexColZero = board.getIndexColZero();
//        Tile zeroValueTile = tile.setValueTile(0);
//
//        if (a.getDirection() == direction.up) {
//            tile = board.getTileFromIndex(indexRowZero+1, indexColZero);
//            board.setTileAtIndex(this.tile, indexRowZero,indexColZero);
//            board.setTileAtIndex( zeroValueTile, indexRowZero+1, indexColZero);
//
//        }
//        if (a.getDirection() == direction.down) {
//            tile = board.getTileFromIndex(indexRowZero-1, indexColZero);
//            board.setTileAtIndex(this.tile, indexRowZero,indexColZero);
//            board.setTileAtIndex(zeroValueTile, indexRowZero-1, indexColZero);
//
//        }
//        if (a.getDirection() == direction.left) {
//            tile = board.getTileFromIndex(indexRowZero, indexColZero+1);
//            board.setTileAtIndex(this.tile, indexRowZero,indexColZero);
//            board.setTileAtIndex(zeroValueTile, indexRowZero, indexColZero+1);
//        }
//        if (a.getDirection() == direction.right) {
//            tile = board.getTileFromIndex(indexRowZero, indexColZero-1);
//            board.setTileAtIndex(this.tile, indexRowZero,indexColZero);
//            board.setTileAtIndex(zeroValueTile, indexRowZero, indexColZero-1);
//        }
//        return new State(board);
//    }
//
//
//
//        @Override
//        public boolean equals(Object other) {
//            if (!(other instanceof State)) {
//                return false;
//            }
//            State state = (State) other;
//            return board.equals(state.board);
//        }
//
//        @Override
//        public int hashCode() {
//            return board.hashCode();
//        }
//
//
//}
