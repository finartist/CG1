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

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.Arrays;


/*
 * Wer Interesse hat, sich mit Grafik in JAVA auseinanderzusetzen, kann 
 * sich gerne an seinen Tutoren wenden.
 */

public class Board {
	
	private static JButton[] field;
	private static MirroredFrame frame;
	
	private static ImageIcon oGreen;
	private static ImageIcon oRed;
	
	protected static void createBoard() {
		
		field = new JButton[42];
		frame = new MirroredFrame("Connect four");
		
		oGreen = new ImageIcon(Board.class.getResource( "OGreen.png" ));
		oRed = new ImageIcon(Board.class.getResource( "ORed.png" ));
		
		Dimension initSize = new Dimension(100, 100);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(new GridLayout(6, 7));
		for (int i = 0; i < field.length; i++){
			field[i] = new JButton();
			field[i].setPreferredSize(initSize);
			field[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton pressedButton = (JButton) e.getSource();
					Game.handleAction(Arrays.asList(field).indexOf(pressedButton));
					frame.repaint();
				}
			});
			
			frame.add(field[i]);
		}
		frame.pack();
		frame.setVisible(true);
	}
		
	protected static void resizeImages(){
		for (int i = 0; i < field.length; i++) {
			try {
				Image image = ((ImageIcon) field[i].getIcon()).getImage();
				Image resizedImage = image.getScaledInstance(
						field[i].getWidth(), field[i].getHeight(),  java.awt.Image.SCALE_SMOOTH) ;  
				ImageIcon resizedIcon = new ImageIcon(resizedImage);
				
				field[i].setIcon(resizedIcon);
			}catch(NullPointerException e) {}
		}
	}
	
	/********************************************************************************************
	 * VERWENDBARE METHODEN FUER TUTIS
	 ********************************************************************************************/
	
	protected static void drawCircleGreen(int index) {
		/*
		 * fuegt einen gruenen Kreis an der Stelle index ein.
		 */
		
		JButton button = field[index];
		Image image = oGreen.getImage();
		Image resizedImage = image.getScaledInstance(
				button.getWidth(), button.getHeight(),  java.awt.Image.SCALE_SMOOTH) ;  
		oGreen = new ImageIcon(resizedImage);
		button.setIcon(oGreen);	
	}

	protected static void drawCircleRed(int index) {
		/*
		 * fuegt einen roten Kreis an der Stelle index ein.
		 */
		
		JButton button = field[index];
		Image image = oRed.getImage();
		Image resizedImage = image.getScaledInstance(
				button.getWidth(), button.getHeight(),  java.awt.Image.SCALE_SMOOTH) ;  
		oRed = new ImageIcon(resizedImage);
		button.setIcon(oRed);	
	}
	
	protected static void win(String playerName) {
		/*
		 * oeffnet ein Informationsfenster, dass der Spieler playerName
		 * gewonnen hat. Anschließend wird das Fenster geschlossen.
		 */
		
		String winMessage = "Glueckwunsch " + playerName + ", du hast gewonnen!";
		JOptionPane.showMessageDialog(frame, winMessage, "Gewonnen!", JOptionPane.INFORMATION_MESSAGE);
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
	
	protected static void tie(){
		/*
		 * oeffnet ein Informationsfenster, dass ein Unentschieden
		 * gespielt wurde. Anschließend wird das Fenster geschlossen.
		 */
		
		String tieMessage = "Unentschieden!";
		JOptionPane.showMessageDialog(frame, tieMessage, "Unentschieden!", JOptionPane.INFORMATION_MESSAGE);
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
}
