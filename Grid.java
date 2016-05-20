import javax.swing.JButton;
import java.awt.*;
import javax.swing.*;

public class Grid extends JButton {
    public boolean clickable;
    public char letter;
    
    public Grid(){
        super(" ");
        this.clickable = true;
        this.letter = 'N';
        this.setBackground(Color.BLACK);
    }

    public boolean clicked(char a){
    	ImageIcon imgx = new ImageIcon("x.jpg");
        ImageIcon imgo = new ImageIcon("o.jpg");

        if (clickable){
            this.setText(a+"");

            if (a == 'X'){
            	this.setIcon(imgx);
            }
            else {
            	this.setIcon(imgo);
            }
            this.letter = a;
            clickable = false;
            //this.setBackground(Color.yellow);
            return true;
        }
        else return false;
    }

    public void restart(){
    	ImageIcon imgn = new ImageIcon("n.png");
        this.clickable = true;
        this.letter = 'N';
        this.setText(" ");
        this.setIcon(imgn);
       

    }
}
