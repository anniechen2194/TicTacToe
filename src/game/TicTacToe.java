package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class TicTacToe {
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
	static ArrayList<Integer> remainPositions = new ArrayList<Integer>();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for (int i = 1; i < 10; i++) {
			remainPositions.add(i);
		}
		
		char [][] gameBoard = {{' ', '|', ' ', '|', ' '}, 
				{'-', '+', '-', '+', '-'}, 
				{' ', '|', ' ', '|', ' '}, 
				{'-', '+', '-', '+', '-'}, 
				{' ', '|', ' ', '|', ' '}};
		
		printGameBoard(gameBoard);
		
		
		
		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter your placement (1-9): ");
			
			int playerPos = scan.nextInt();
			while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
				System.out.println("Position is already taken! Enter another position: ");
				playerPos = scan.nextInt();
			}
			
			
			placePiece(gameBoard, playerPos, "player");
			remainPositions.remove(Integer.valueOf(playerPos));
			
			String result = checkWinner();
			if (result.length() > 0) {
				System.out.println(result);
				break;
			}
			
			Random rand = new Random();
			int index = rand.nextInt(remainPositions.size());
			int size = remainPositions.size();
			
			
			int cpuPos = remainPositions.get(index);
			
			placePiece(gameBoard, cpuPos, "cpu");
			remainPositions.remove(Integer.valueOf(cpuPos));
			
			printGameBoard(gameBoard);
			
			result = checkWinner();
			
			if (result.length() > 0) {
				System.out.println(result);
				break;
			}
		}
		
	}
	
	
	public static void printGameBoard(char[][] gameBoard) {
		for (char[] row: gameBoard) {
			for (char c: row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void placePiece(char[][] gameBoard, int pos, String user) {
		
		char symbol = ' ';
		if (user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		} else if(user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(pos);
		}
		switch(pos) {
			case 1:
				gameBoard[0][0] = symbol;
				break;
			case 2:
				gameBoard[0][2] = symbol;
				break;
			case 3:
				gameBoard[0][4] = symbol;
				break;
			case 4:
				gameBoard[2][0] = symbol;
				break;
			case 5:
				gameBoard[2][2] = symbol;
				break;
			case 6:
				gameBoard[2][4] = symbol;
				break;
			case 7:
				gameBoard[4][0] = symbol;
				break;
			case 8:
				gameBoard[4][2] = symbol;
				break;
			case 9:
				gameBoard[4][4] = symbol;
				break;
		}
	}
	
	public static String checkWinner() {
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List cross1 = Arrays.asList(1, 5, 9);
		List cross2 = Arrays.asList(3, 5, 7);
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);
		
		for (List l : winning) {
			if (playerPositions.containsAll(l)) {
				return "Congratulations you won!";
			} else if (cpuPositions.containsAll(l)) {
				return "CPU wins! Sorry :(";
			} 
		}
		if (playerPositions.size() + cpuPositions.size() == 9) {
			return "Hey no one wins";
		}
		
		return "";
	}
}
