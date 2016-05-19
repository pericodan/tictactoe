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

public class TicTacToe {
    static Grid a[][] = new Grid[3][3];
    static Random r = new Random();

    public static void main(String[] args){
		final JFrame mainFrame = new JFrame("LightsOut");					    //the frame for the game
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel(new GridLayout(3,3));
		mainFrame.setSize(new Dimension(300, 300));
		mainFrame.getContentPane().add(mainPanel);
		mainFrame.setVisible(true);

        int i=0, j=0;

        for(i=0; i<3; i++){
			for(j=0; j<3; j++){
				a[i][j] = new Grid();
				mainPanel.add(a[i][j]);
				a[i][j].addActionListener(new ActionListener(){
				  public void actionPerformed(ActionEvent e){
				  	Grid a = (Grid)e.getSource();
                    if(a.clicked('O')){
                        if(checkIfWinner('O')){
                            JOptionPane b = new JOptionPane();
    						b.showMessageDialog(mainFrame, "You Won");			//this will show the win dialog box
    						System.exit(1);
                        }
                        if(checkIfDraw()){
                            JOptionPane b = new JOptionPane();
    						b.showMessageDialog(mainFrame, "Draw");
    						System.exit(1);
                        }
                        turnOfAI();
                        if(checkIfWinner('X')){
                            JOptionPane b = new JOptionPane();
    						b.showMessageDialog(mainFrame, "You Lose");			//this will show the win dialog box
    						System.exit(1);
                        }
                        if(checkIfDraw()){
                            JOptionPane b = new JOptionPane();
    						b.showMessageDialog(mainFrame, "Draw");
    						System.exit(1);
                        }
                    }
				  }
				});
			}
		}
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

        State root = new State(temp);
        a[root.x][root.y].clicked('X');
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
}
