package tictactoe;
import java.util.Scanner;

public class Main {
    static final  int inputLength = 9;
    static final int gridHeight = 5;

    static void printLine(){
        System.out.print("---------\n");
    }
    static boolean playerWon(StringBuilder gameBuilder,char player){
        return  (gameBuilder.charAt(0) == player && gameBuilder.charAt(1) == player && gameBuilder.charAt(2) == player) ||
                (gameBuilder.charAt(3) == player && gameBuilder.charAt(4) == player && gameBuilder.charAt(5) == player) ||
                (gameBuilder.charAt(6) == player && gameBuilder.charAt(7) == player && gameBuilder.charAt(8) == player) ||
                (gameBuilder.charAt(0) == player && gameBuilder.charAt(3) == player && gameBuilder.charAt(6) == player) ||
                (gameBuilder.charAt(1) == player && gameBuilder.charAt(4) == player && gameBuilder.charAt(7) == player) ||
                (gameBuilder.charAt(2) == player && gameBuilder.charAt(5) == player && gameBuilder.charAt(8) == player) ||
                (gameBuilder.charAt(0) == player && gameBuilder.charAt(4) == player && gameBuilder.charAt(8) == player) ||
                (gameBuilder.charAt(2) == player && gameBuilder.charAt(4) == player && gameBuilder.charAt(6) == player) ;
    }
        static void playGame(StringBuilder gameBuilder,int[] end){

        int k=0,X=0,O=0;
        boolean xWin = false,oWin = false,notFinished= false;
        for(int i=0;i<gridHeight;i++){
            if(i==0 || i==gridHeight-1){
                printLine();
                continue;
            }
            for(int j=0;j<inputLength;j++){
                if(j%2!=0){
                    System.out.print(" ");
                }
                else if(j==0 ){
                    System.out.print("|");
                }else if (j==inputLength-1){
                    System.out.print("|\n");
                }
                else{
                    System.out.print(gameBuilder.charAt(k));
                    k++;
                }
            }
        }
         if (playerWon(gameBuilder,'X') && !playerWon(gameBuilder,'O')){
            System.out.println("X wins");
            end[0]=1;
        }
        else if (!playerWon(gameBuilder,'X') && playerWon(gameBuilder,'O')){
            System.out.println("O wins");
            end[0]=1;
        }

    }
    static void playerMove(StringBuilder gameBuilder,int move[],char symbol){
        playerChoose(move);
        int k=0;
        boolean choice = false;

        while(!choice){
            k=0;
            for(int i=1;i<=3;i++){
                for(int j=1;j<=3;j++,k++){
                    if(move[0]==i && move[1]==j){
                        if(gameBuilder.charAt(k)=='X' || gameBuilder.charAt(k)=='O'){
                            System.out.println("This cell is occupied! Choose another one!");
                            playerChoose(move);
                        }
                        else {
                            gameBuilder.setCharAt(k,symbol);
                            choice = true;
                        }
                    }
                }
            }
        }
    }
    static void playerChoose(int move[]){

        boolean choice = false,isDigit = false;
        while(!choice){
            isDigit=false;
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if(line.length()<2)
                continue;
            for(int i=0;i<line.length();i++){
                if(Character.isWhitespace(line.charAt(i)))
                    continue;
                if(!Character.isDigit(line.charAt(i)) )
                    isDigit=true;
            }
            if(isDigit){
                System.out.println("You should enter numbers!");
                continue;
            }

            char moveChar[] = new char[2];
            String str[] = line.split(" ");
            move[0] = Integer.parseInt(str[0]);
            move[1] = Integer.parseInt(str[1]);

            if(move[0]<1 || move[0]>3 || move[1]<1 || move[1]>3 ){
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            choice=true;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        StringBuilder gameBuilder = new StringBuilder("         ");

        int end[] = new int[1];
        int move[] = new int[2];

            playGame(gameBuilder,end);
            for(int i=0;i<9;i++){
                if(i%2==0){
                    playerMove(gameBuilder,move,'X');
                    playGame(gameBuilder,end);
                    if(end[0]==1 )
                        break;
                }
                else{
                    playerMove(gameBuilder,move,'O');
                    playGame(gameBuilder,end);
                    if(end[0]==1)
                        break;
                }
                if(i==8){
                    System.out.println("Draw");
                }

            }
    }
}