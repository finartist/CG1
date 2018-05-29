package connect_four;

/**
 * @author tsabsch <tim.sabsch@st.ovgu.de>
 * This file was written in the course of the preliminary courses
 * at the faculty of computer sciences at the OVG university
 * Magdeburg in 2014.
 * This project is distributed under the terms of the GNU General Public License (GPL), 
 * it may be used freely as long as the source code including all modifications remains freely available.
 * 
 * Changes:
 * 
 * Originally it was a 'TicTacToe' - template
 * @author gerschmi <gerd.schmidt@st.ovgu.de> changed it to a 'Connect Four' template
 */

public class Game {
	
	static char[] board = new char[42];
	static boolean turnRed = true;
	
	public static void main (String[] args){
		//create Interface
		Board.createBoard();

		
	}
	
	// find if there are four in a row (horizontally, vertically, diagonally)
	static char findFour(int index){
		char player = board[index];
		//vertically
		if(index < 21){
			for(int j = index+7; j < index+3*7+1; j += 7){
				System.out.println(j);
				if(board[j] != player){
					break;
				}
				if(j == index+3*7){
					return player;
				}
			}
		}
		//horizontally
		int row = index-index%7;
		int count = 0;
		for(int k = row; k < row+7; k++){
			if(board[k] == player){
				++count;
			}
			else{
				count = 0;
			}
			if(count == 4){
				return player;
			}
		}
		
		//diagonal rows (can be improved e.g. row have to be 4 places long)
		//diagonal from left to right(top to bottom)
		count = 0;
		  //top-left index in diagonal row
		int rightDiagonal = index%7 < index/7 ? index-(index%7)*8 : index-(index/7)*8;
		System.out.println("Diagonal l->r" + rightDiagonal);
		  //going down the row and count
		for(int h = rightDiagonal; h < 42; h+=8){
			if(board[h] == player){
				++count;
			}
			else{
				count = 0;
			}
			if(count == 4){
				return player;
			}
		}
		
		//diagonal from right to left (top to bottom)
		count = 0;
		  //top-right index in diagonal row
		int leftDiagonal = 6-index%7 < index/7 ? index-(6-index%7)*6 : index-(index/7)*6;
		System.out.println("Diagonal r->l:" + leftDiagonal);
		  //going down the row and count
		for(int m = leftDiagonal; m < 42; m+=6){
			if(board[m] == player){
				++count;
			}
			else{
				count = 0;
			}
			if(count == 4){
				return player;
			}
		}
		
		// no player has 4 in a row
		return  '\u0000';
	}
	
	// TODO Game logic: Connect Four
	protected static void handleAction(int index){
		
		/*
		 * Diese Methode wird immer dann aufgerufen, wenn der User auf ein Feld klickt.
		 * Als Parameter bekommt ihr den Index des Feldes, das angeklickt wurde.
		 * (Es handelt sich also um ein Array).
		 * Das ist die Anordung der Indizes:
		 * 0  | 1  | 2  | 3  | 4  | 5  | 6
		 * 7  | 8  | 9  | 10 | 11 | 12 | 13
		 * 14 | 15 | 16 | 17 | 18 | 19 | 20
		 * 21 | 22 | 23 | 24 | 25 | 26 | 27
		 * 28 | 29 | 30 | 31 | 32 | 33 | 34
		 * 35 | 36 | 37 | 38 | 39 | 40 | 41
		 * 
		 * Eure Aufgabe ist die Logik zu diesem Spiel zu schreiben. Sprich: Was passiert,
		 * wenn ein Spieler ein Feld drueckt. Dazu stehen euch folgende Methoden zur Verfuegung:
		 * Board.drawCircleRed(int index)
		 * Board.drawCircleGreen(int index)
		 * Board.win(String playerName)
		 * Board.tie()
		 * Informationen dazu findet ihr in den Methoden in der Klasse Board.
		 * 
		 * Wem das alles zu einfach ist, der kann gerne selbst eine Ein-/Ausgabe 
		 * (über die Konsole + Scanner) programmieren.
		 */
		
		int column = index%7;
		if(board[column] != '\u0000'){
			System.out.println("Please choose a column, that is not full yet!");
			return;
		}
		
		//find free spot in column and place stone there, save index
		int playerStoneIndex = 0;
		for(int i = 0; i < 6; ++i){
			if(board[(35+column)-i*7] ==  '\u0000'){
				if(turnRed){
					Board.drawCircleRed((35+column)-i*7);
					board[(35+column)-i*7] = 'R';
					turnRed = false;
				}
				else{
					Board.drawCircleGreen((35+column)-i*7);
					board[(35+column)-i*7] = 'G';
					turnRed = true;
				}
				playerStoneIndex = (35+column)-i*7;
				break;
			}
		}
		
		//check if there is a winner
		char win = findFour(playerStoneIndex);
		System.out.println(win);
		
		if(win != '\u0000'){
			Board.win("" + win);
		}
		
		// check for a draw (every row has to be full or improvement: no winning possible anymore)
		boolean fullBoard = false;
		for(int i = 0; i < 7; ++i){
			if(board[i] ==  '\u0000'){
				break;
			}
			if(i == 6){
				fullBoard = true;
			}
		}
		if(fullBoard){
			Board.tie();
			return;
		}
		
		System.out.println(board);
		
	}
}
