public class Node {
    private State currState;
    private Action actionDone;
    private Node parent;

    Node( Node parent, State currState){
        this.currState = currState;
        this.actionDone = currState.getAction();
        this.parent = parent;

    }

    public Node[] expand(){
        if (currState.isGoal()){
            return new Node[0];
        }
        Action [] actions = currState.actions();
        Action[] newActionArray = new Action[actions.length-1];
        int counterNewArray = 0;
        if (actionDone != null) {
            Direction toAvoid = getOppositeDirection(actionDone);
            for (int i = 0; i < actions.length; i++) {
                if (actions[i].getDirection() != toAvoid) {
                    newActionArray[counterNewArray] = actions[i];
                    counterNewArray++;
                }
            }
        }
        State [] states = new State[counterNewArray];
        Node [] nodes = new Node[states.length];

        for (int i = 0; i < states.length; i++) {
            states[i] = currState.result(newActionArray[i]);
            nodes[i] = new Node(this, states[i]);
        }
        return nodes;
    }

    private Direction getOppositeDirection(Action action){
       if (action.getDirection() == Direction.right){
           return Direction.left;
       }
       else if (action.getDirection() == Direction.left){
            return Direction.right;
        }
       else if (action.getDirection() == Direction.up){
           return Direction.down;
       }
       else {
           return Direction.up;
       }
    }

        private int abs(int a) {
        if (a < 0) {
            return a * -1;
        }
        return a;
    }

        public int heuristicValue() {

        Tile [][] board = currState.getCurrentBoard().getBoard();
        int size = currState.getSize();
        int [] zeroValueTile = currState.findNumber(board,0);
        int counter = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j< size; j++) {
                if (board[i][j].getValueTile() == 0) {
                    continue;
                }
                //Integer.valueOf(cols[j]).intValue()
                int i1 = (board[i][j].getValueTile() - 1) / board.length;
                int i2;
                if (board[i][j].getValueTile() % board.length == 0) {
                    i2 = board.length - 1;
                } else {
                    i2 = board[i][j].getValueTile() % board.length;
                }
                int heuristic = i1 + i2 - i - j;
                counter += abs(heuristic);
            }
        }
        return counter;
    }


// get
    public Node getParent() {
        return this.parent;
    }

    public State getCurrState() {
        return currState;
    }
    public State getState() {
        return this.currState;
    }

    public Action getAction() {
        return actionDone;
    }
}






//public class Node {
//
//    private Vertex vertex;
//    Board board;
//    Tile tile;
//    State state;
//
//    private Vertex[] expand() {
//        vertex.stateFather = state;
//        Vertex[] allVertex = new Vertex[state.actions().length];
//        for (int i = 0; i < state.actions().length; i++) {
//            vertex.action = state.actions()[i];
//            vertex.stateSun = state.result(vertex.action);
//            allVertex[i]= this.vertex;
//        }
//        return expand();
//    }
//

//

//}
//
//
