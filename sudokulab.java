import java.util.Scanner;
public class sudokulab
{
	private static int slots = 9;
    private static int[][]board = new int[9][9];

    private static void printBoard(){//prints out board
        for(int row=0;row<slots;row++){
            if(row%3==0&&row!=0){
                System.out.println("-------------------");
            }
            for(int col=0;col<slots;col++){
                if(col%3==0&&col!=0){
                    System.out.print("|");
                }
                System.out.print(board[row][col]+" ");
            }
            System.out.println();
        }
    }
    private static int[] checkUnassigned(int r, int c)//if numbers are 0 and keep track of it
    {
        int cunassign = 0;
        for(int row=0;row<slots;row++)
        {
            for(int col=0;col<slots;col++)
            {
                if(board[row][col] == 0)//if sudoku slot is 0 then will change and return correct slot
                {
                    r = row;
                    c = col;
                    cunassign = 1;
                    int[]x = {cunassign, r, c};//returns correct array slot
                    return x;
                }
            }
        }
        int[]x={cunassign, -1, -1};//will go back to check
        return x;
    }
    private static boolean safetoplace(int n, int row, int col)
    //determine whether the solver function is safe to place true or false
    {
        for(int i=0;i<slots;i++){//checks if rows 
            if(board[row][i] == n)
                return false;
        }
        for(int i=0;i<slots;i++){//checks columns
            if(board[i][col] == n)
                return false;
        }
        int row_start = (row/3)*3;//checks within the 3 by 3 grids
        int col_start = (col/3)*3;
        for(int i=row_start;i<row_start+3;i++){
            for(int j=col_start;j<col_start+3;j++){
                if(board[i][j]==n)
                    return false;
            }
        }
        return true;
    }
    private static boolean solveSudoku(){//solves the partially filled board
        int row=0;
        int col=0;
        int[]x=checkUnassigned(row, col);//checks if number in board is a 0
        if(x[0] == 0){//if there is no 0 in sudoku, then it is already solved
            return true;
        }
        row = x[1];
        col = x[2];
        for(int i=1;i<=slots;i++){
            if(safetoplace(i, row, col)){
                board[row][col] = i;
                if(solveSudoku()){//back tracks as it goes back to beginning of the function
                    return true;
                }
                board[row][col]=0;
            }
        }
        return false;
    }
    public static void fill(){//fills out sudoku board by user input
        Scanner input = new Scanner(System.in);
        for(int row=0;row<board.length;row++){
            for(int col = 0; col<board.length;col++){
                System.out.println("\nInput sudoku board: ");
                board[row][col]=input.nextInt();
                printBoard();
            }
        }    
    }
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("\n");
        System.out.print("New board: \n");
        printBoard();
        fill();
        if (solveSudoku()){
            System.out.println("\nSolved Board:");
            printBoard();
        }
        else{
            System.out.println("Invalid as there are no solutions");
        }
    }
}
