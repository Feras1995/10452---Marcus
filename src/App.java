import java.util.HashSet;
import java.util.Scanner;

public class App {
    static HashSet<Character> saveStones = new HashSet<Character>();
    static int rows, columns;
    static char stones[][];
    static boolean visited[][];
    static String moves="";

    // forth right left
    static int[] moveXc = { -1, 0, 0 };
    static int[] moveYc = { 0, +1, -1 };

    static void dfs(int x, int y) {
        visited[x][y] = true;
        //end of path 
        if (stones[x][y] == '#')
            return;

        for (int i = 0; i < 3; i++) {
            int xc = x + moveXc[i];
            int yc = y + moveYc[i];

            if (isValid(xc, yc) && isSaveStone(xc, yc) && !visited(xc, yc)) {
                printDirecton(moveXc[i], moveYc[i]);
                dfs(xc, yc);
            }
        }

    }

    //print dirication based on moveXC moveYC array 
    static void printDirecton(int xc, int yc) {
        String direction = "";
        if (xc == -1 && yc == 0)
            direction = "forth";
        else if (xc == 0 && yc == 1)
            direction = "right";
        else if (xc == 0 && yc == -1)
            direction = "left";
        moves+=direction+" ";
    }

    //check if stone with gaven cordinate are valid stone 
    static boolean isSaveStone(int xc, int yc) {
        char c = stones[xc][yc];
        if (saveStones.contains(c))
            return true;
        return false;
    }

    //to avoid arryindexOutOfBoundException
    static boolean isValid(int xc, int yc) {
        if (xc >= 0 && yc >= 0 && xc < rows && yc < columns)
            return true;
        return false;
    }

    //check if node is visited 
    static boolean visited(int xc, int yc) {
        if (visited[xc][yc])
            return true;
        return false;
    }

    //find @ start position 
    //return {i,j} 
    static int[] startSton() {
        int[] start = new int[2];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                if (stones[i][j] == '@') {
                    start[0] = i;
                    start[1] = j;
                }

        return start;
    }

    public static void main(String[] args) throws Exception {
        //initlize hashset with valid stone character
        saveStones.add('I');
        saveStones.add('E');
        saveStones.add('H');
        saveStones.add('O');
        saveStones.add('V');
        saveStones.add('A');
        saveStones.add('#');

        Scanner sc = new Scanner(System.in);
        //input number of testCases
        int testCases = sc.nextInt();
        sc.nextLine();

        for (int t = 0; t < testCases; t++) {
            // input arry dimention
            rows = sc.nextInt();
            columns = sc.nextInt();
            sc.nextLine();

            //initlize array with valid rows and columns for each test case
            stones = new char[rows][columns];
            visited = new boolean[rows][columns];
            //input array 
            for (int i = 0; i < rows; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < columns; j++) {
                    stones[i][j] = line.charAt(j);
                    visited[i][j] = false;
                }
            }

            // find @ position
            int[] start = startSton();
            //caling dfs 
            dfs(start[0], start[1]);
            //remove white space at the end of moves
            moves=moves.substring(0,moves.length()-1);
            //print output
            System.out.println(moves);
            //reset for next test case 
            moves="";

        }

    }
}
