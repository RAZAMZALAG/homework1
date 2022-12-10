public class Main {
    public static void main(String[] args) {
        Tile b = new Tile(6);
        String[] boards = { "7 5 4|0 3 2|8 1 6"};
        Board c = new Board(boards[0]);

    }



        int successCounter = 0;
        for (String boardString : boards) {
            boolean success = searchOnce(boardString);
            if (success) {
                successCounter++;
            }
        }
        System.out.println("Able to solve " + successCounter + " ot of " + boards.length + " games.");
    }

    private static boolean searchOnce(String boardString) {
        Search search = new Search();
        Thread t = new Thread(() -> search.search(boardString));
        t.start();
        try {
            t.join(60000);
        } catch (InterruptedException ex) {
        }
        boolean succeeded = false;
        if (t.isAlive()) {
            t.stop();
            System.out.println("Timout occurred...");
        } else {
            int status = search.getStatus();
            switch (status) {
                case Search.SOLVED:
                    System.out.println("Solution length: " + search.getResult().size());
                    System.out.println(search.getResult());
                    succeeded = true;
                    break;
                case Search.UNSOLVABLE:
                    System.out.println("Unsolvable game...");
                    break;
                case Search.OUT_OF_MEMORY:
                    System.out.println("Out of memory while searching...");
                    break;
            }
        }
        System.out.println("Number of expanded nodes: " + search.getExpandedNodes());
        System.out.println("----------------------------------------------------------------------");
        return succeeded;
    }
}
