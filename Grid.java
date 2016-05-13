import javax.swing.JButton;

public class Grid extends JButton {
    public boolean clickable;
    public char letter;

    public Grid(){
        super(" ");
        this.clickable = true;
    }

    public boolean clicked(char a){
        if (clickable){
            this.setText(a+"");
            this.letter = a;
            clickable = false;
            //this.setBackground(Color.yellow);
            return true;
        }
        else return false;
    }
}
