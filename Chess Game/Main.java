/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.Scanner;
import java.util.ArrayList;

public class Main
{
	public static void main(String[] args) {
	    Scanner input = new Scanner(System.in);
		System.out.println("Welcome to \u001B[1mChess Battle!\u001B[0m This is a two-player chess game with the same rules as typical chess. Note that special moves like pawn \npromotion and \"en passant\" are enabled, but not castling.");
		
		ChessPiece[][] board = new ChessPiece[8][8];
		Player player1 = new Player("Player 1", 1);
		Player player2 = new Player("Player 2", 2);
		int currentRound = 1;
		
		System.out.println("\nOkay, now let's choose who's who!\n"); 
		
		initiatePlayer(player1);
		System.out.println();
		
		initiatePlayer(player2);
		System.out.println();
		
		assignPieces(player1, player2, board);
		
		printBoard(board);
		
		while (true) {
		    player1.takeTurn(player2, currentRound, board);
		    
		    player2.takeTurn(player1, currentRound, board);
		    
		    currentRound++;
	    }
	}
	public static void initiatePlayer(Player player) { //this is individual for potential future computer version
	    Scanner input = new Scanner(System.in);
	    
	    System.out.print("\u001B[1m" + player.getName() + "\u001B[0m, please enter \u001B[1m\u001B[4m" + player.getNumber() + "\u001B[0m: ");
		int playerConfirmation = input.nextInt();
		while (playerConfirmation != player.getNumber()) {
		    System.out.println("\u001B[1m\u001B[31mSorry, invalid input! Please try again.\u001B[0m");
		    System.out.print("Player " + player.getNumber() + ", please enter \u001B[1m\u001B[4m" + player.getNumber() + "\u001B[0m: ");
		    playerConfirmation = input.nextInt();
		}
		System.out.print("\nWelcome \u001B[1m\u001B[4m" + player.getName() + "\u001B[0m! Would you like to make a custom name (Y/N)? ");
		String nameResponse = input.next();
		while (!(nameResponse.equalsIgnoreCase("Y") || nameResponse.equalsIgnoreCase("N") 
		    || nameResponse.equalsIgnoreCase("Yes") || nameResponse.equalsIgnoreCase("No"))) {
		    System.out.println("\n\u001B[1m\u001B[31mSorry, invalid input! Please try again.\u001B[0m");
		    System.out.print("Would you like to make a custom name (Y/N)? ");
		    nameResponse = input.next();
		}
		if (nameResponse.equalsIgnoreCase("Y") || nameResponse.equalsIgnoreCase("Yes")) {
		    System.out.print("Great! Please enter your desired name: ");
		    player.setName(input.next());
		    System.out.println("\u001B[1m\u001B[32mNice choice! Welcome \u001B[0m\u001B[1m\u001B[4m" + player.getName() + "\u001B[0m");
		}
		else {
		    System.out.println("\u001B[1m\u001B[32mOkay! We'll stick with \u001B[0m\u001B[1m\u001B[4m" + player.getName() + "\u001B[0m");
		} 
	}
	
	public static void assignPieces(Player player1, Player player2, ChessPiece[][] board) {
	    //Player 1
	    for (char col = 'A'; col <= 'H'; col++) {
	        player1.getPiecesInPlay().add(new Pawn('\u265F', col + "2", 1));
	    }
	    player1.getPiecesInPlay().add(new Knight('\u265E', "B1", 1));
	    player1.getPiecesInPlay().add(new Knight('\u265E', "G1", 1));
	    player1.getPiecesInPlay().add(new Bishop('\u265D', "C1", 1));
	    player1.getPiecesInPlay().add(new Bishop('\u265D', "F1", 1));
	    player1.getPiecesInPlay().add(new Rook('\u265C', "A1", 1));
	    player1.getPiecesInPlay().add(new Rook('\u265C', "H1", 1));
	    player1.getPiecesInPlay().add(new Queen('\u265B', "D1", 1));
	    player1.getPiecesInPlay().add(new King('\u265A', "E1", 1));
	    
	    //Player 2
	    for (char col = 'A'; col <= 'H'; col++) {
	        player2.getPiecesInPlay().add(new Pawn('\u2659', col + "7", 2));
	    }
	    player2.getPiecesInPlay().add(new Knight('\u2658', "B8", 2));
	    player2.getPiecesInPlay().add(new Knight('\u2658', "G8", 2));
	    player2.getPiecesInPlay().add(new Bishop('\u2657', "C8", 2));
	    player2.getPiecesInPlay().add(new Bishop('\u2657', "F8", 2));
	    player2.getPiecesInPlay().add(new Rook('\u2656', "A8", 2));
	    player2.getPiecesInPlay().add(new Rook('\u2656', "H8", 2));
	    player2.getPiecesInPlay().add(new Queen('\u2655', "D8", 2));
	    player2.getPiecesInPlay().add(new King('\u2654', "E8", 2));
	    
	    //Board
	    board[0][0] = player2.getPiecesInPlay().get(12);
	    board[0][1] = player2.getPiecesInPlay().get(8);
	    board[0][2] = player2.getPiecesInPlay().get(10);
	    board[0][3] = player2.getPiecesInPlay().get(14);
	    board[0][4] = player2.getPiecesInPlay().get(15);
	    board[0][5] = player2.getPiecesInPlay().get(11);
	    board[0][6] = player2.getPiecesInPlay().get(9);
	    board[0][7] = player2.getPiecesInPlay().get(13);
	    for (int col = 0; col < 8; col++) {
	        board[1][col] = player2.getPiecesInPlay().get(col);
	    }
	    for (int row = 2; row < 6; row++) {
	        for (int col = 0; col < 8; col++) {
	            board[row][col] = new ChessPiece(0, ' ', (char)('A' + col) + "" + (8 - row), 0);  
	        }
	    }
	    for (int col = 0; col < 8; col++) {
	        board[6][col] = player1.getPiecesInPlay().get(col);
	    }
	    board[7][0] = player1.getPiecesInPlay().get(12);
	    board[7][1] = player1.getPiecesInPlay().get(8);
	    board[7][2] = player1.getPiecesInPlay().get(10);
	    board[7][3] = player1.getPiecesInPlay().get(14);
	    board[7][4] = player1.getPiecesInPlay().get(15);
	    board[7][5] = player1.getPiecesInPlay().get(11);
	    board[7][6] = player1.getPiecesInPlay().get(9);
	    board[7][7] = player1.getPiecesInPlay().get(13);
	}
	
	public static void printBoard(ChessPiece[][] board) {
	    for (int row = 0; row < 8; row++) {
	        int col = 0;
	        if (row % 2 == 0) 
	            System.out.println("    \u001B[1m\u001B[31m----\u001B[0m " + " \u001B[1m---- " + " \u001B[31m----\u001B[0m " + " \u001B[1m---- " + " \u001B[31m----\u001B[0m " + " \u001B[1m---- " + " \u001B[31m----\u001B[0m " + " \u001B[1m---- \u001B[0m");
	        else
	            System.out.println("    \u001B[1m---- " + " \u001B[31m----\u001B[0m " + " \u001B[1m---- " + " \u001B[31m----\u001B[0m " + " \u001B[1m---- " + " \u001B[31m----\u001B[0m " + " \u001B[1m---- " + " \u001B[31m---- \u001B[0m");
	        System.out.print(" " + (8 - row) + " ");
	        for (int j = 0; j < 16; j++) {
	            if (j % 2 != 0) {
	                    System.out.print("\u001B[0m " + board[row][col].getSymbol() + "  ");
	                    col++;
	            }
	            if (j == 0 || j == 1 || j == 4 || j == 5 || j == 8 || j == 9 || j == 12 || j == 13) {
	                if (row % 2 == 0)
	                    System.out.print("\u001B[31m");
	            }
	            else if (j == 2 || j == 3 || j == 6 || j == 7 || j == 10 || j == 11 || j == 14 || j == 15) {
	                if (row % 2 != 0)
	                    System.out.print("\u001B[31m");
	            }
	            System.out.print("\u001B[1m|");
	            System.out.print("\u001B[0m");
	        }
	        System.out.println();
	        if (row % 2 == 0) 
	            System.out.println("    \u001B[1m\u001B[31m----\u001B[0m " + " \u001B[1m---- " + " \u001B[31m----\u001B[0m " + " \u001B[1m---- " + " \u001B[31m----\u001B[0m " + " \u001B[1m---- " + " \u001B[31m----\u001B[0m " + " \u001B[1m---- \u001B[0m");
	        else
	            System.out.println("    \u001B[1m---- " + " \u001B[31m----\u001B[0m " + " \u001B[1m---- " + " \u001B[31m----\u001B[0m " + " \u001B[1m---- " + " \u001B[31m----\u001B[0m " + " \u001B[1m---- " + " \u001B[31m---- \u001B[0m");
	    }
	    System.out.println("     A     B     C     D      E     F     G     H\n");
	}
	
	public static ChessPiece choosePiece(Player player) {
	    Scanner input = new Scanner(System.in);
	    System.out.print("Okay, \u001B[1m\u001B[4m" + player.getName() + "\u001B[0m! ");
	    System.out.print("Which piece type would you like to move (1 - pawn, 2 - knight, 3 - bishop, 4 - rook, 5 - queen, 6 - king): ");
	    int pieceType = input.nextInt();
	    while (pieceType < 1 || pieceType > 6) {
	        System.out.println("\u001B[1m\u001B[31mError. Invalid piece type. Please try again.\u001B[0m\n");
	        System.out.print("Which piece would you like to move (1 - pawn, 2 - knight, 3 - bishop, 4 - rook, 5 - queen, 6 - king): ");
	        pieceType = input.nextInt();
	    }
	    
	    ArrayList<ChessPiece> foundPieces = new ArrayList<ChessPiece>();
	    switch (pieceType) {
	        case 1: findPieceByType(1, player.getPiecesInPlay(), foundPieces);
	                if (foundPieces.size() < 1) {
	                    System.out.println("\u001B[1m\u001B[31mSorry, no pawns found! Please select a different piece type.\u001B[0m\n");
	                    choosePiece(player);
	                } 
	                System.out.print("\nYou have " + ((foundPieces.size() == 1) ? "a pawn" : "pawns") + " at ");
	                for (int i = 0; i < foundPieces.size(); i++) {
	                    if (i == foundPieces.size() - 1) 
	                        System.out.println(foundPieces.get(i).getCurrentPosition() + ". ");
	                    else
	                        System.out.print(foundPieces.get(i).getCurrentPosition() + ", ");
	                }
	                System.out.print("Please enter the position of the piece you wish to play (e.g. A3): \u001B[1m");
	                String piecePos = input.next();
	                System.out.print("\u001B[0m");
	                int foundPieceIndex = findPieceByPosition(piecePos, foundPieces);
	                while (foundPieceIndex == -1) {
	                    System.out.println("\u001B[1m\u001B[31mError: invalid pawn position. Please try again.\u001B[0m\n");
	                    System.out.print("Please enter the position of the piece you wish to play (e.g. A3): \u001B[1m");
	                    piecePos = input.next();
	                    System.out.print("\u001B[0m");
	                    foundPieceIndex = findPieceByPosition(piecePos, foundPieces);
	                }
	                System.out.println("\u001B[1m\u001B[32mYou've chosen to move the pawn at " + foundPieces.get(foundPieceIndex).getCurrentPosition() + ".\u001B[0m");
	                return foundPieces.get(foundPieceIndex);
	                
	        case 2: findPieceByType(2, player.getPiecesInPlay(), foundPieces);
	                if (foundPieces.size() < 1) {
	                    System.out.println("\u001B[1m\u001B[31mSorry, no knights found! Please select a different piece type.\u001B[0m\n");
	                    choosePiece(player);
	                }
	                System.out.print("\nYou have " + ((foundPieces.size() == 1) ? "a knight" : "knights") + " at ");
	                for (int i = 0; i < foundPieces.size(); i++) {
	                    if (i == foundPieces.size() - 1) 
	                        System.out.println(foundPieces.get(i).getCurrentPosition() + ". ");
	                    else
	                        System.out.print(foundPieces.get(i).getCurrentPosition() + ", ");
	                }
	                System.out.print("Please enter the position of the piece you wish to play (e.g. A3): \u001B[1m");
	                piecePos = input.next();
	                System.out.print("\u001B[0m");
	                foundPieceIndex = findPieceByPosition(piecePos, foundPieces);
	                while (foundPieceIndex == -1) {
	                    System.out.println("\u001B[1m\u001B[31mError: invalid knight position. Please try again.\u001B[0m\n");
	                    System.out.print("Please enter the position of the piece you wish to play (e.g. A3): \u001B[1m");
	                    piecePos = input.next();
	                    System.out.print("\u001B[0m");
	                    foundPieceIndex = findPieceByPosition(piecePos, foundPieces);
	                }
	                System.out.println("\u001B[1m\u001B[32mYou've chosen to move the knight at " + foundPieces.get(foundPieceIndex).getCurrentPosition() + ".\u001B[0m");
	                return foundPieces.get(foundPieceIndex);
	                
	        case 3: findPieceByType(3, player.getPiecesInPlay(), foundPieces);
	                if (foundPieces.size() < 1) {
	                    System.out.println("\u001B[1m\u001B[31mSorry, no bishops found! Please select a different piece type.\u001B[0m\n");
	                    choosePiece(player);
	                }
	                System.out.print("\nYou have " + ((foundPieces.size() == 1) ? "a bishop" : "bishops") + " at ");
	                for (int i = 0; i < foundPieces.size(); i++) {
	                    if (i == foundPieces.size() - 1) 
	                        System.out.println(foundPieces.get(i).getCurrentPosition() + ". ");
	                    else
	                        System.out.print(foundPieces.get(i).getCurrentPosition() + ", ");
	                }
	                System.out.print("Please enter the position of the piece you wish to play (e.g. A3): \u001B[1m");
	                piecePos = input.next();
	                System.out.print("\u001B[0m");
	                foundPieceIndex = findPieceByPosition(piecePos, foundPieces);
	                while (foundPieceIndex == -1) {
	                    System.out.println("\u001B[1m\u001B[31mError: invalid bishop position. Please try again.\u001B[0m\n");
	                    System.out.print("Please enter the position of the piece you wish to play (e.g. A3): \u001B[1m");
	                    piecePos = input.next();
	                    System.out.print("\u001B[0m");
	                    foundPieceIndex = findPieceByPosition(piecePos, foundPieces);
	                }
	                System.out.println("\u001B[1m\u001B[32mYou've chosen to move the bishop at " + foundPieces.get(foundPieceIndex).getCurrentPosition() + ".\u001B[0m");
	                return foundPieces.get(foundPieceIndex);
	        
	        case 4: findPieceByType(4, player.getPiecesInPlay(), foundPieces);
	                if (foundPieces.size() < 1) {
	                    System.out.println("\u001B[1m\u001B[31mSorry, no rooks found! Please select a different piece type.\u001B[0m\n");
	                    choosePiece(player);
	                }
	                System.out.print("\nYou have " + ((foundPieces.size() == 1) ? "a rook" : "rooks") + " at ");
	                for (int i = 0; i < foundPieces.size(); i++) {
	                    if (i == foundPieces.size() - 1) 
	                        System.out.println(foundPieces.get(i).getCurrentPosition() + ". ");
	                    else
	                        System.out.print(foundPieces.get(i).getCurrentPosition() + ", ");
	                }
	                System.out.print("Please enter the position of the piece you wish to play (e.g. A3): \u001B[1m");
	                piecePos = input.next();
	                System.out.print("\u001B[0m");
	                foundPieceIndex = findPieceByPosition(piecePos, foundPieces);
	                while (foundPieceIndex == -1) {
	                    System.out.println("\u001B[1m\u001B[31mError: invalid rook position. Please try again.\u001B[0m\n");
	                    System.out.print("Please enter the position of the piece you wish to play (e.g. A3): \u001B[1m");
	                    piecePos = input.next();
	                    System.out.print("\u001B[0m");
	                    foundPieceIndex = findPieceByPosition(piecePos, foundPieces);
	                }
	                System.out.println("\u001B[1m\u001B[32mYou've chosen to move the rook at " + foundPieces.get(foundPieceIndex).getCurrentPosition() + ".\u001B[0m");
	                return foundPieces.get(foundPieceIndex);
	                
	        case 5: findPieceByType(5, player.getPiecesInPlay(), foundPieces);
	                if (foundPieces.size() < 1) {
	                    System.out.println("\u001B[1m\u001B[31mSorry, no queens found! Please select a different piece type.\u001B[0m\n");
	                    choosePiece(player);
	                }
	                System.out.print("\nYou have " + ((foundPieces.size() == 1) ? "a queen" : "queens") + " at ");
	                for (int i = 0; i < foundPieces.size(); i++) {
	                    if (i == foundPieces.size() - 1) 
	                        System.out.println(foundPieces.get(i).getCurrentPosition() + ". ");
	                    else
	                        System.out.print(foundPieces.get(i).getCurrentPosition() + ", ");
	                }
	                System.out.print("Please enter the position of the piece you wish to play (e.g. A3): \u001B[1m");
	                piecePos = input.next();
	                System.out.print("\u001B[0m");
	                foundPieceIndex = findPieceByPosition(piecePos, foundPieces);
	                while (foundPieceIndex == -1) {
	                    System.out.println("\u001B[1m\u001B[31mError: invalid queen position. Please try again.\u001B[0m\n");
	                    System.out.print("Please enter the position of the piece you wish to play (e.g. A3): \u001B[1m");
	                    piecePos = input.next();
	                    System.out.print("\u001B[0m");
	                    foundPieceIndex = findPieceByPosition(piecePos, foundPieces);
	                }
	                System.out.println("\u001B[1m\u001B[32mYou've chosen to move the queen at " + foundPieces.get(foundPieceIndex).getCurrentPosition() + ".\u001B[0m");
	                return foundPieces.get(foundPieceIndex);
	                
	        case 6: findPieceByType(6, player.getPiecesInPlay(), foundPieces);
	                System.out.println("\n\u001B[1m\u001B[32mYou've chosen to move your king at " + foundPieces.get(0).getCurrentPosition() + ".\u001B[0m");
	                return foundPieces.get(0);
	                
	        default: return new ChessPiece(0, ' ', "00", 0); 
	    }
	}
	
	public static void findPieceByType(int type, ArrayList<ChessPiece> pieceList, ArrayList<ChessPiece> foundPieces) {
	    for (int i = 0; i < pieceList.size(); i++) {
	        if (pieceList.get(i).getType() == type)
	            foundPieces.add(pieceList.get(i));
	    }
	}
	
	public static int findPieceIndexByType(int type, ArrayList<ChessPiece> pieceList) {
	    for (int i = 0; i < pieceList.size(); i++) {
	        if (pieceList.get(i).getType() == type)
	            return i;
	    }
	    return -1;
	}
	
	public static int findPieceByPosition(String position, ArrayList<ChessPiece> pieceList) {
	    for (int i = 0; i < pieceList.size(); i++) {
	        if (pieceList.get(i).getCurrentPosition().equalsIgnoreCase(position))
	            return i;
	    }
	    return -1;
	}
}
