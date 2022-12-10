import java.util.Arrays;

public class Board {
    public Tile[][] getBoard() {
        return board;
    }

    private Tile[][] board;

    Board(Tile[][] newBoard){
        this.board=newBoard;
    }
    Board(String boardString) {
//        "7 5 4|0 3 2|8 6 1"

        String[] firstArr = boardString.split("\\|", -1);
        System.out.println("firstArr" + Arrays.toString(firstArr));

        int size = firstArr.length;


        String[][] stringMatrix = new String[size][];
        for (int i = 0; i < firstArr.length; i++) {
            stringMatrix[i] = firstArr[i].split("\\ ", -1);
//            System.out.println("stringMatrix[ " + i + "] " + Arrays.toString(stringMatrix[i]));
//
        }

        Tile[][] newBoard = new Tile[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                int val = Integer.parseInt(stringMatrix[i][j]);
                newBoard[i][j] = new Tile(val);
            }
        }

        this.board=newBoard;


//        System.out.println("firstArr" + Arrays.toString(newBoard));
//
//        System.out.println("newBoard2" + newBoard);
//        for (int i = 0; i < firstArr.length; i++) {
//            System.out.println("newBoard2[ " + i + "] " + Arrays.toString(newBoard[i]));
//        }


    }
    public String toString() {
        String str = "board mat ";
        for (int i = 0; i < this.board.length; i++) {
            str += "\n";
            str += Arrays.toString(board[i]);
            }
        return  str;
    }
}


