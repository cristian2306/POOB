public class Dicese
{
    // instance variables - replace the example below with your own
    private Dice[] array;
    

    /**
     * Constructor for objects of class Dicese
     */
    public Dicese(int n)
    {
        // initialise instance variables
       
        
        array = new Dice [n];
        for(int i=0;i<n;i++){
            array[i] = new Dice();
            array[i].moveHorizontal(120*i);
        }
        makeVisible();
    }
    
    public void reset(){
        for(int i =0;i<=array.length-1;i++){
            array[i].roll();
        }
    }
    
    public void play2(int times){
        for(int i=1;i<=times;i++){
            reset();
        }
    }
    
    public boolean isWinningState(){
        boolean safe = true;
        for(int i =0;i<=1;i++)
            if(array[i].getValue() == array[i+1].getValue()){
               safe = true && safe;}
            else{
                 safe = false && safe;
            }
        return safe;
    }
    
    public void makeVisible(){
        for(int i =0;i<=array.length-1;i++){
            array[i].makeVisible();
        }
    }
    
    public void makeInvisible(){
        for(int i =0;i<=array.length-1;i++){
            array[i].makeInvisible();
        }
    }
    
    public void move(int horizontal, int vertical){
        makeInvisible();
        for(int i =0;i<=array.length-1;i++){
            array[i].moveHorizontal(horizontal);
            array[i].moveVertical(vertical);
        }
        makeVisible();
        
    }
}
