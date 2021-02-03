public class Dice

{
    // instance variables - replace the example below with your own
    private int value;
    private boolean isVisible;
    private Rectangle Line;
    private Rectangle Font;
    private Circle [][] Values;
    
    
    private void One (){
        Values[0][0]= new Circle();
        Values[0][0].makeVisible();
        Values[0][0].changeColor("black");
        Values[0][0].moveVertical(117);
        Values[0][0].moveHorizontal(111);
    }
    
    private void Two(){
        Values[1][0] = new Circle();
        Values[1][1] = new Circle();
        Values[1][0].changeColor("black");
        Values[1][1].changeColor("black");
        Values[1][0].changeSize(25);
        Values[1][0].moveVertical(95);
        Values[1][0].moveHorizontal(113);
        Values[1][1].changeSize(25);
        Values[1][1].moveVertical(140);
        Values[1][1].moveHorizontal(113);
        
    }
    
    private void Three() {
        Values[2][0] = new Circle();
        Values[2][1] = new Circle();
        Values[2][2] = new Circle();
        Values[2][0].changeColor("black");
        Values[2][1].changeColor("black");
        Values[2][2].changeColor("black");
        Values[2][0].changeSize(25);
        Values[2][0].moveVertical(85);
        Values[2][0].moveHorizontal(150);
        Values[2][1].changeSize(25);
        Values[2][1].moveVertical(120);
        Values[2][1].moveHorizontal(113);
        Values[2][2].changeSize(25);
        Values[2][2].moveVertical(152);
        Values[2][2].moveHorizontal(78);       
    }
    
    private void Four() {
        Values[3][0] = new Circle();
        Values[3][1] = new Circle();
        Values[3][2] = new Circle();
        Values[3][3] = new Circle();
        Values[3][0].changeColor("black");
        Values[3][1].changeColor("black");
        Values[3][2].changeColor("black");
        Values[3][3].changeColor("black");
        Values[3][0].changeSize(25);
        Values[3][0].moveVertical(87);
        Values[3][0].moveHorizontal(150);
        Values[3][1].changeSize(25);
        Values[3][1].moveVertical(150);
        Values[3][1].moveHorizontal(150);
        Values[3][2].changeSize(25);
        Values[3][2].moveVertical(151);
        Values[3][2].moveHorizontal(78);
        Values[3][3].changeSize(25);
        Values[3][3].moveVertical(87);
        Values[3][3].moveHorizontal(78);            
    }
    
    private void Five(){
        Values[4][0] = new Circle();
        Values[4][1] = new Circle();
        Values[4][2] = new Circle();
        Values[4][3] = new Circle();
        Values[4][4] = new Circle();
        Values[4][0].changeColor("black");
        Values[4][1].changeColor("black");
        Values[4][2].changeColor("black");
        Values[4][3].changeColor("black");
        Values[4][4].changeColor("black");
        Values[4][0].changeSize(25);
        Values[4][0].moveVertical(87);
        Values[4][0].moveHorizontal(150);
        Values[4][1].changeSize(25);
        Values[4][1].moveVertical(150);
        Values[4][1].moveHorizontal(150);
        Values[4][2].changeSize(25);
        Values[4][2].moveVertical(151);
        Values[4][2].moveHorizontal(78);
        Values[4][3].changeSize(25);
        Values[4][3].moveVertical(87);
        Values[4][3].moveHorizontal(78);
        Values[4][4].changeSize(25);
        Values[4][4].moveVertical(115);
        Values[4][4].moveHorizontal(115);   
    }
    
    private void Six(){
        Values[5][0] = new Circle();
        Values[5][1] = new Circle();
        Values[5][2] = new Circle();
        Values[5][3] = new Circle();
        Values[5][4] = new Circle();
        Values[5][5] = new Circle();
        Values[5][0].changeColor("black");
        Values[5][01].changeColor("black");
        Values[5][2].changeColor("black");
        Values[5][3].changeColor("black");
        Values[5][4].changeColor("black");
        Values[5][5].changeColor("black");
        Values[5][0].changeSize(22);
        Values[5][0].moveVertical(83);
        Values[5][0].moveHorizontal(150);
        Values[5][1].changeSize(22);
        Values[5][1].moveVertical(158);
        Values[5][1].moveHorizontal(150);
        Values[5][2].changeSize(22);
        Values[5][2].moveVertical(158);
        Values[5][2].moveHorizontal(78);
        Values[5][3].changeSize(22);
        Values[5][3].moveVertical(83);
        Values[5][3].moveHorizontal(78);
        Values[5][4].changeSize(22);
        Values[5][4].moveVertical(120);
        Values[5][4].moveHorizontal(150);
        Values[5][5].changeSize(22);
        Values[5][5].moveVertical(120);
        Values[5][5].moveHorizontal(78);
    }
    
    /*Constructor for objects of class Dice
     */
    public Dice()
    {
        Values = new Circle [6][6];
        One();
        Two();
        Three();
        Four();
        Five();
        Six();
        isVisible = true;
        Line = new Rectangle();
        Font = new Rectangle();
        Line.makeVisible();
        Font.makeVisible();
        Font.changeColor("black");
        Font.changeSize(113,113);
        Font.moveHorizontal(20);
        Font.moveVertical(75);
        Line.changeColor("white");
        Line.changeSize(99,99);
        Line.moveHorizontal(27);
        Line.moveVertical(82);
        roll();
        
    }
    
    public void roll(){
        makeInvisible();
        value = (int)(Math.floor(Math.random()*6+1));
    }
    
    public int getValue(){
        return value;
    }
    
    /**
     * Make this dice visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        int i;
        isVisible = true;
        Font.makeVisible();
        Line.makeVisible();
        if(value == 1){
            Values[0][0].makeVisible();
        }
        else if(value == 2){
            for(i=0;i<2;i++){
                Values[1][i].makeVisible();
            }
        }
        else if(value == 3){
            for(i=0;i<3;i++){
                Values[2][i].makeVisible();
            }
        }
        else if(value == 4){
            for(i=0;i<4;i++){
                Values[3][i].makeVisible();
            }
        }
        else if(value == 5){
            for(i=0;i<5;i++){
                Values[4][i].makeVisible();
            }
                }
        else if(value == 6){
            for(i=0;i<6;i++){
                Values[5][i].makeVisible();
            }
        }  
    }
    
    /**
     * Make this dice invisible. If it was already invisible, do nothing.
     */
    
    public void makeInvisible(){
        int i;
        int j;
        for(i=0;i<6;i++){
            for(j = 0;j<=i;j++){
                 Values[i][j].makeInvisible();
            }
        }
        Line.makeInvisible();
        Font.makeInvisible();
        isVisible = false;
    }
  
    public void moveHorizontal(int cant){
        int i;
        int j;
        makeInvisible();
        Line.moveHorizontal(cant);
        Font.moveHorizontal(cant);
        for(i=0;i<6;i++){
            for(j = 0;j<=i;j++){
                 Values[i][j].moveHorizontal(cant);
            }
        }
        
    }
    
    public void moveVertical(int cant){
        int i;
        int j;
        makeInvisible();
        Line.moveVertical(cant);
        Font.moveVertical(cant);
        for(i=0;i<6;i++){
            for(j = 0;j<=i;j++){
                 Values[i][j].moveVertical(cant);
            }
        }
        
    }
    
    public void changeColor(String color){
        makeInvisible();
        Line.changeColor(color);
        makeVisible();        
    }
}
