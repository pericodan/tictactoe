import java.util.ArrayList;

public class State{
    public boolean isMaximization;
    public int alpha;
    public int beta;
    public int x;
    public int y;
    public int v;
    public char a[][];
    public static char opponentSymbol;
    public static char playerSymbol;

    public State(char a[][], char o, char p){                                   //initial constructor
        this.alpha = -1000;
        this.beta = 1000;
        this.v = -1000;
        this.a = a;
        this.opponentSymbol = o;
        this.playerSymbol = p;
        ArrayList<State> successors = this.getSuccessors(this.opponentSymbol);
        for(int i=0; i<successors.size(); i++){
            if(this.v<successors.get(i).v){
                this.v = successors.get(i).v;
                this.x = successors.get(i).x;                                   //to get the x and y coordinate of the best move
                this.y = successors.get(i).y;
            }
            if(this.v>=this.beta){
                break;
            }
            this.alpha = Math.max(this.alpha, this.v);
        }
    }
    public State(char a[][], int ap, int bt, boolean max, int x, int y){        //the succeeding constructor
        this.alpha = ap;
        this.beta = bt;
        this.x = x;
        this.y =y;
        this.a = a;
        if(isUtility()){}                                                       //to check if it is already a utility node
        else if(max){                                                           //if it is a max node
            v = -1000;
            ArrayList<State> successors = this.getSuccessors(this.opponentSymbol);
            for(int i=0; i<successors.size(); i++){
                this.v = Math.max(this.v, successors.get(i).v);
                if(this.v>=this.beta){
                    break;
                }
                this.alpha = Math.max(this.alpha, this.v);
            }
        }
        else{                                                                   //if it is a minimum node
            v = 1000;
            ArrayList<State> successors = this.getSuccessors(this.playerSymbol);
            for(int i=0; i<successors.size(); i++){
                this.v = Math.min(this.v, successors.get(i).v);
                if(this.v<=this.alpha){
                    break;
                }
                this.beta = Math.min(this.beta, this.v);
            }
        }
    }
    public ArrayList<State> getSuccessors(char c){                              //this function will get all the successors of a state
        ArrayList<State> s = new ArrayList<State>();
		int i, j;
		for(i=0; i<3; i++){
			for(j=0; j<3; j++){
				if(this.a[i][j]=='N'){
                    char[][] tempA = new char[3][3];
                    for(int x=0; x<3; x++){
            			for(int y=0; y<3; y++){
                            if(i==x && j==y) tempA[x][y]=c;
            				else tempA[x][y]=this.a[x][y];
            			}
            		}
                    if(c==this.opponentSymbol){
                        s.add(new State(tempA, this.alpha, this.beta, false, i, j)); //min node
                    }
                    else{
                        s.add(new State(tempA, this.alpha, this.beta, true, i, j)); //max node
                    }
                }
			}
		}
        return s;
    }
    public boolean isUtility(){                                                 //this function will return true if the state is a utility and change the value of this.v
        char letter = this.opponentSymbol;
        int i=0, j=0, checker1=0, checker2=0;                                   //to check if the utility is 1
        for(i=0; i<3; i++){
            for(j=0; j<3; j++){
                if(this.a[i][j] == letter)
                    checker1++;
                if(this.a[j][i] == letter)
                    checker2++;
            }
            if(checker1==3 || checker2==3){
                this.v = 1;
                return true;
            }
            checker1=0;
            checker2=0;
        }
        if(this.a[0][0]==letter && this.a[1][1]==letter && this.a[2][2]==letter) {
            this.v = 1;
            return true;
        }
        else if(this.a[0][2]==letter && this.a[1][1]==letter && this.a[2][0]==letter) {
            this.v = 1;
            return true;
        }

        letter = this.playerSymbol;
        checker1=0;
        checker2=0;

        for(i=0; i<3; i++){                                                     //to check if the utility is -1
            for(j=0; j<3; j++){
                if(this.a[i][j] == letter)
                    checker1++;
                if(this.a[j][i] == letter)
                    checker2++;
            }
            if(checker1==3 || checker2==3){
                this.v = -1;
                return true;
            }
            checker1=0;
            checker2=0;
        }
        if(this.a[0][0]==letter && this.a[1][1]==letter && this.a[2][2]==letter) {
            this.v = -1;
            return true;
        }
        else if(this.a[0][2]==letter && this.a[1][1]==letter && this.a[2][0]==letter) {
            this.v = -1;
            return true;
        }

        for(i=0; i<3; i++){                                                     //to check if it is not yet utility
            for(j=0; j<3; j++){
                if(this.a[i][j]=='N')
                    return false;
            }
        }
        this.v = 0;                                                             //the state is draw
        return true;
    }
}
