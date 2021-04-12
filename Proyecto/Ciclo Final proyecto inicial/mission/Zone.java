package mission;
import crates.*;
import shapes.*;
import javax.swing.JOptionPane;
/**
 * Una zona que representa una matriz dada
 * 
 * @author Cristian Castellanos- Diego Gonzalez 
 * @version 27/03/2021
 */
public class Zone
{
    //Posicion de la zona
    int x;
    int y;
    //Medidas de la zona
    int fila;
    int columna;
    int max;//Altura maxima de la zona
    int min;//Coordenada en el eje Y mas baja
    //Valores de la zona
    int[][] heigths;
    boolean isVisible;
    //Valores de las vistas de la zona
    int[] sideValues;
    int[] frontValues;
    //Vistas de la zona
    View[] sideViews;
    View[] frontViews;
    UpView upView;
    
    /**
     * Crea una zona en la posicion dada con tres vistas frontal, lateral y superior con las medidad dadas
     * @param x - Posicion de la zona en el eje x
     * @param y - Posicion de la zona en el eje y
     * @param fila- Numero de filas de la zona a crear
     * @param columna- Numero de columnas de la zona a crear
     */
    public Zone(int x, int y, int fila, int columna){
        this.x = x;
        this.y = y;
        this.fila = fila;
        this.columna =  columna;
        int grew = new Rectangle().getSize(); 
        upView = new UpView(x+(fila+columna+4)*grew,y+grew,fila,columna);
        sideViews = new View[columna];
        frontViews = new View[fila];
        heigths = new int[fila][columna];
        for(int i = 0;i<columna;i++){
            View view =  new View(x+grew*(columna+2),y,columna);
            sideViews[i] = new View(x+grew*(columna+2),y,columna);
        }

        for(int i = 0;i<fila;i++){
            frontViews[i] = new View(x,y,fila);
        }
    }
    
    /**
     * Crea una zona en la posicion dada, con tres vistas frontal, lateral y superior basandose en una matriz
     * @param x - Posicion de la zona en el eje x
     * @param y - Posicion de la zona en el eje y
     * @param heigths - Matriz que representa la bodega
     */
    public Zone(int x,int y, int[][] heigths){
        this(x,y,heigths.length,heigths[0].length);
        this.heigths = heigths;
        for(int i =0;i<fila;i++){
            for(int j =0;j<columna;j++){
                int crates = heigths[i][j]; 
                for(int k=0;k<crates;k++){store(i,j,"Normal");}
            }
        }
    }

    /**
     * Función encargada de retornar el arreglo de vistas de lado
     * @return arreglo de vistas de lado
     */
    protected View[] getSideViews(){
        return this.sideViews;
    }

    /**
     * Función encargada de retornar el arreglo de vistas del frente
     * @return arreglo de vistas del frente
     */
    protected View[] getFrontView(){
        return this.frontViews;
    }

    /**
     * Función encargada de retornar la vista de arriba 
     * @return vista de arriba 
     */
    protected UpView getUpView(){
        return this.upView;
    }

    /**
     * Función encargada de cambiar la vista de lado por la nueva vista
     * @param newView
     */
    protected void setSideView(View[] newView){
        this.sideViews = newView;
    }
    
    /**
     * Función encargada de cambiar la vista de frente por la nueva vista
     * @param newView
     */
    protected void setFrontView(View[] newView){
        this.frontViews = newView;
    }

    /**
     * Función encargada de cambiar la vista de arriba por la nueva vista
     * @param newView
     */
    protected void setUpView(UpView newView){
        this.upView = newView;
    }
    
    /**
     * Refresca la zona, teniendo en cuenta la altura de la zona baja la imagen
     */
    protected void refresh(){
        int grew = new Rectangle().getSize();
        max = getMaxZone();
        min = getMinZone();
        for(View view:sideViews){
            int move = (min - view.getMaxStack().getMin());
            view.moveVertical(move);
            view.setMax(max);
        }
        for(View view:frontViews){
            int move = (min - view.getMaxStack().getMin());
            view.moveVertical(move);
            view.setMax(max);
        }
        int move = grew*(max-upView.getMax());
        upView.moveVertical(move);
        upView.setMax(max);
 
    }
    
    /**
     * Función encargada de hacer visible la zona
     */
    public void makeVisible(){
        isVisible = true;
        for(View view:sideViews){view.makeVisible();}
        for(View view:frontViews){view.makeVisible();}
        upView.makeVisible();
    }
    
    /**
     *´Función encargada de hacer invisible la zona 
     */
    public void makeInvisible(){
        for(View view:sideViews){view.makeInvisible();}
        for(View view:frontViews){view.makeInvisible();}
        upView.makeInvisible();
        isVisible = false;
    }
    
    /**
     * Mueva las vistas de la zona una distancia d
     * @param d- Distancia a mover las vistas verticalmente
     */
    public void moveVertical(int d){
        for(View view:sideViews){view.moveVertical(d);}
        for(View view:frontViews){view.moveVertical(d);}
        upView.moveVertical(d);
        y += d;
    }
    
    /**
     * Mueve las vistas de la zona una distancia d
     * @param d- Distancia a mover las vistas horizontalmente
    */
    public void moveHorizontal(int d){
        for(View view:sideViews){view.moveHorizontal(d);}
        for(View view:frontViews){view.moveHorizontal(d);}
        upView.moveHorizontal(d);
        x += d;
    }
    
    /**
     * Calcula los valores maximos de una matriz y lo retorna en un arreglo
     * @param matriz - Matriz a la cual se le saca los maximos
     * @return - Retorna un arreglo con los maximos de la matriz por fila
     */
    public int[] maxRow(int[][] matriz){
        int[] maximos = new int[matriz.length];
        int i = 0;
        for(int[] array:matriz){
            maximos[i]=maxArray(array);
            i++;
        }
        return maximos;
    }
    
    /**
     * Calcula el valor maximo de un arreglo
     * @param array - Arreglo del cual se quiere saber el valor mayor
     * @return - Entero que represneta le valor mayor del arreglo
     */
    public int maxArray(int[] array){
        int max = 0;
        for(int i:array){max = (max<i)?i:max;}
        return max;
    }
    
    /**
     * Calcula los valores maximos de una matriz
     * @param matriz - Matriz a la cual se le saca los maximos
     * @return Retorna un arreglo con los maximos por columna
     */
    public int[] maxColumns(int[][] matriz){
        int[] maximos = new int[matriz[0].length];
        for(int i = 0;i<matriz[0].length;i++){maximos[i] = maxArray(getColumns(matriz,i));}
        return maximos;
    }


     /**
     * Función generada para revisar que el tipo de caja que quiera el usuario
     * esté entre las que se pueden crear
     * @param type_Check
     * @return --> boolean answer
     */
    private boolean checkerTypes(String type_Check){
        boolean answer = false;
        String[] Types = new String[]{"Normal","Delicate","Rebel","Frost","Safe","Heavy"};
        for(String type: Types){
            if(type_Check.equals(type)){
                return true;
            }
        }

        return answer;
    }

    /**
     * Agrega a las vistas una nueva caja en la posicion indicada
     * @param row - Fila de la pila en la que se agrega la caja
     * @param column - Columna de la pila en la que se agrega la caja 
     */
    public void store(int row, int column, String type){
        if(upView.canStore(row, column, type)){
            sideViews[column].store(row,type);
            frontViews[row].store(column,type);
            upView.store(row,column,type);
            heigths[row][column]++;
        }
        else{
        }
        //condicional desde upview para revisar si fue posible insertar la caja
        refresh();
    }
    
    /**
     * Función creada para insertar cajas en una posición determinada de acuerdo a la posición y que 
     * tipo de caja quiere que sea
     * @param crate
     * @param type
     */
    public void store(int[] crate, String type){
        store(crate[0],crate[1], type);
    }

    /**
     * Roba de la zona la caja en la posicion indicada
     * @param row - Fila en donde esta contenida la caja
     * @param column - Columna en donde esta contenida la caja
     */
    public void stealZ(int row, int column){
        sideViews[column].steal(row);
        frontViews[row].steal(column);
        Crate crate = upView.steal(row,column);
        if(crate != null){
            if(heigths[row][column] > 0){
                heigths[row][column] --;
            }
            else{
                JOptionPane.showMessageDialog(null, "La pila de cajas en esta posición se encuentra vacía", "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
        refresh();
    }
    
    /**
     * Retorna la columna de la matriz señalada
     * @param matriz - Matriz de la cual se quiere saber la columna
     * @param i - Numero de la columna a obtener
     * @return - Arreglo que representa la columna de la matriz en la posicion señalada
     */
    public int[] getColumns(int[][] matriz, int i){
        int[] array = new int[matriz.length];
        for(int j =0;j<matriz.length;j++){array[j] = matriz[j][i];}
        return array;
    }
    
    /**
     * Calcula cual es la vista con mayor altura de un arreglo de vistas y retorna esta vista
     * @param views - Arreglo de vistas
     * @return - Altura maxima del arreglo de vistas
     */
    protected View getMaxViews(View[] views){
        int max = 0;
        int cont = 0;
        int i=0;
        for(View view:views){
            if(view.getMaxStack().getMax()>max){
                max = view.getMaxStack().getMax();
                i = cont;
            }
            cont ++;
            
        }
        return views[i];
    }
    
    /**
     * Calcula la altura mas alta de una lista de vistas
     * @param views - Arreglo de vistas
     * @return - Altura max grande del arreglo de vistas
     */
    protected int getMaxHeigthsView(View[] views){
        return getMaxViews(views).getMax();
    }
    
    /**
     * Retorna la posicion Y mas baja de una lista de vistas
     * @param views - Arreglo de vistas
     * @return - Posicion y mas baja
     */
    protected int getMinPositionMaxView(View[] views){return getMaxViews(views).getMin();}
    
    /**
     * Calcula la altura de la zona teniendo en cuenta sus vistas
     * @return - Altura maxima de la zona
     */
    protected int getMaxZone(){
        if(getMaxHeigthsView(sideViews) > getMaxHeigthsView(frontViews) ){return getMaxHeigthsView(sideViews);}
        else{return getMaxHeigthsView(frontViews);}
    }
    
    protected int getMinZone(){
        if(getMinPositionMaxView(sideViews) > getMinPositionMaxView(frontViews) ){return getMinPositionMaxView(sideViews);}
        else{return getMinPositionMaxView(frontViews);}
    }
    
    public int[][] getHeigths(){return this.heigths;}

    public int[][] makeMaxUp(){
        int[][] maxUp = new int[heigths.length][heigths[0].length];
        for(int i=0;i<heigths.length;i++){
            for(int j=0;j<heigths[0].length;j++){
                if(heigths[i][j]>0){
                    maxUp[i][j] = 1;
                }
                else{
                    maxUp[i][j] = 0;
                }
            }
        }
        return maxUp;
    }
}
