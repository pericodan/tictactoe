import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Container;
import java.io.*;
import java.util.Random;
import java.awt.Dimension;
import java.awt.*;
import javax.swing.*;


public class TicTacToe {
    static Grid a[][] = new Grid[3][3];
    static Grid b[][] = new Grid[1][2];
    static Random r = new Random();
    static boolean endOfGame = false;
    static char playerSymbol;
    static char opponentSymbol;
    static JFrame cFrame = new JFrame("Tic Tac Toe");	

    public static void main(String[] args){

		final JFrame mainFrame = new JFrame("Tic Tac Toe");					    //the frame for the game
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel(new GridLayout(3,3));
		mainPanel.setBackground(Color.BLACK);
		mainFrame.pack();
		mainFrame.setSize(new Dimension(300, 300));
		mainFrame.add(mainPanel);					
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
        int i=0, j=0;

        for(i=0; i<3; i++){
			for(j=0; j<3; j++){
				a[i][j] = new Grid();
				mainPanel.add(a[i][j]);
				a[i][j].addActionListener(new ActionListener(){
				  public void actionPerformed(ActionEvent e){
				  	Grid a = (Grid)e.getSource();
                    if(a.clicked(playerSymbol)){
                        if(checkIfWinner(playerSymbol)){
                            JOptionPane b = new JOptionPane();
    						b.showMessageDialog(mainFrame, "You Won");			//this will show the win dialog box
    						reinitializeGrids();
    						chooserWindow();
                            endOfGame = true;
                        }
                        if(checkIfDraw()){
                            JOptionPane b = new JOptionPane();
    						b.showMessageDialog(mainFrame, "Draw");
    						reinitializeGrids();
    						chooserWindow();
                            endOfGame = true;
                        }
                        if(!endOfGame){
                            turnOfAI();
                            if(checkIfWinner(opponentSymbol)){
                                JOptionPane b = new JOptionPane();
        						b.showMessageDialog(mainFrame, "You Lose");		//this will show the loose dialog box
        						reinitializeGrids();
        						chooserWindow();
                            }
                            if(checkIfDraw()){
                                JOptionPane b = new JOptionPane();
        						b.showMessageDialog(mainFrame, "Draw");
        						reinitializeGrids();
        						chooserWindow();	
                            }

                        }
                        else{
                            endOfGame = false;
                        }
                    }
				  }
				});
			}
		}

		chooserWindow();
    }

    public static void chooserWindow(){
			
		JPanel choosePanel = new JPanel(new GridLayout(1,2));
		choosePanel.setBackground(Color.BLACK);
		cFrame.add(choosePanel);
		cFrame.pack();
		cFrame.setSize(new Dimension(300, 300));					
		cFrame.setLocationRelativeTo(null);
		cFrame.setVisible(true);
		int i;

		ImageIcon imgx = new ImageIcon("x.jpg");
        ImageIcon imgo = new ImageIcon("o.jpg");

		for(i=0; i<2; i++){

			b[0][i] = new Grid();

			choosePanel.add(b[0][i]);

			b[0][i].addActionListener(new ActionListener(){
				@Override
				  public void actionPerformed(ActionEvent e){
				  	System.out.println(" ");

				  	Grid a = (Grid)e.getSource();
				  	buttonPress(a);

				  }
			});
		}

		for (i=0;i<2;i++){
			if (i == 0){
				b[0][i].letter = 'O';
				b[0][i].setIcon(imgo);
			}

			if(i == 1){
				b[0][i].letter = 'X';
				b[0][i].setIcon(imgx);
			}
		}

		cFrame.pack();
		cFrame.setSize(new Dimension(300, 300));					
		cFrame.setLocationRelativeTo(null);
		cFrame.setVisible(true);
	}

	public static void buttonPress(Grid button){

		if (button.letter == 'X'){
			playerSymbol = 'X';
			opponentSymbol = 'O';
		}
		else {
			playerSymbol = 'O';
			opponentSymbol = 'X';
		}
		cFrame.setVisible(false);
	}	
    

    public static void turnOfAI(){
        /*while(true){
            if(a[r.nextInt(3)][r.nextInt(3)].clicked('X'))
                break;
        }*/
        char temp[][] = new char[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                temp[i][j] = a[i][j].letter;
            }
        }

        State root = new State(temp, opponentSymbol, playerSymbol);
        a[root.x][root.y].clicked(opponentSymbol);
    }

    public static boolean checkIfWinner(char letter){
        int i=0, j=0, checker1=0, checker2=0;
        for(i=0; i<3; i++){
            for(j=0; j<3; j++){
                if(a[i][j].letter == letter)
                    checker1++;
                if(a[j][i].letter == letter)
                    checker2++;
            }
            if(checker1==3 || checker2==3){
                return true;
            }
            checker1=0;
            checker2=0;
        }
        if(a[0][0].letter==letter && a[1][1].letter==letter && a[2][2].letter==letter) return true;
        else if(a[0][2].letter==letter && a[1][1].letter==letter && a[2][0].letter==letter) return true;
        else return false;
    }

    public static boolean checkIfDraw(){
        int i=0, j=0, checker1=0, checker2=0;
        for(i=0; i<3; i++){
            for(j=0; j<3; j++){
                if(a[i][j].clickable)
                    return false;
            }
        }

        return true;
    }

    public static void reinitializeGrids(){
        int i=0, j=0;
        for(i=0; i<3; i++){
            for(j=0; j<3; j++){
                a[i][j].restart();
            }
        }
    }
}
