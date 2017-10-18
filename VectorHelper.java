/***************************************************************************************************
 *  TP IGL : Extended Operations On Vectors.
 *  Link to github repository : https://github.com/afr0ck/VectorHelper
 *  This software is distributed as public domain. Feel free to copy, modify and redistribute.
 *  Copyleft @ Karim Manaouil (fk_manaouil@esi.dz) / Mohand Ameziane Belazouz (fm_belazouz@esi.dz)
 *  Ecole Supérieur D'Informatique (ESI ex INI), Oued Semar, Algeirs.
 **************************************************************************************************/

import jdk.nashorn.internal.runtime.ECMAException;
import java.util.*;
import java.io.*;

public class VectorHelper implements Vector {

    /***************************************************************************************************/

    /* Classe utilisé pour retourner les valeurs de min et max */
    public class InnerVect {
        private Integer Max;
        private Integer Min;

        public void setMax(int Max){
            this.Max = new Integer(Max);
        }

        public void setMin(int Min){
            this.Min = new Integer(Min);
        }

        public int getMax(){
            return Max;
        }

        public int getMin(){
            return Min;
        }
    }

    /***************************************************************************************************/

    public interface Expr {
        public int exper(int val);
    }

    /***************************************************************************************************/

    /*  Calcule de minimum et maximum : Retourne un objet de type InnerVect */
    public InnerVect calcMinMax(ArrayList<Integer> vector) throws Exception {
        if(vector.size() == 0)
            throw new Exception(){
                @Override
                public String getMessage() {
                    return "Vecteur est vide";
                }
            };

        InnerVect MinMaxHelper = this.new InnerVect();

        int max = vector.get(0);
        int min = vector.get(0);

        for(int i = 1; i < vector.size(); i++){
            if(vector.get(i) > max)
                max = vector.get(i);

            if(vector.get(i) < min)
                min = vector.get(i);
        }

        MinMaxHelper.setMax(max);
        MinMaxHelper.setMin(min);

        return MinMaxHelper;
    }

    /***************************************************************************************************/

    /* Methode pour implementer n'importe quelle expression arithmitique sur les elements du vecteur */
    public void calcFormule(ArrayList<Integer> vector, Expr expression) throws Exception {
        if(vector.size() == 0)
            throw new Exception(){
                @Override
                public String getMessage() {
                    return "Vecteur est vide";
                }
            };

        for(int i = 0; i < vector.size(); i++)
            vector.set(i, expression.exper(vector.get(i)));
    }

    /***************************************************************************************************/

    public interface VectorHelperIterator extends Iterator<Integer> {}

    /* Methode pour afficher les elements du vecteur */
    public void showVect(ArrayList<Integer> vector) {

         class VectIter implements VectorHelperIterator {
            private int index = 0;

            @Override
            public boolean hasNext() {
                index++;
                return index <= vector.size();
            }

            @Override
            public Integer next() {
                return vector.get(index - 1);
            }

            public int getIndex(){
                return index - 1;
            }
        }

        VectIter iter = new VectIter();

        System.out.print("vect = [");

         while(iter.hasNext()) {
             System.out.print(iter.next());
             if(iter.getIndex() != vector.size() - 1)
                System.out.print(", ");
         }
        System.out.println("]");
    }

    /***************************************************************************************************/

    public static void main(String ... args){
        VectorHelper instVect = new VectorHelper();
        ArrayList<Integer> vect = new ArrayList<Integer>();

        vect.add(new Integer(2));
        vect.add(new Integer(10));
        vect.add(new Integer(3));
        vect.add(new Integer(5));
        vect.add(new Integer(-2));
        vect.add(new Integer(15));

        /***************************************************************************************************/

        try {

            VectorHelper.InnerVect minmax = instVect.calcMinMax(vect);
            System.out.println("Max: " + minmax.getMax() +" Min: " + minmax.getMin());

            instVect.calcFormule(vect, new VectorHelper.Expr(){
                @Override
                public int exper(int val) { /* Définir l'exepression arithmitique */
                    return val - 1;
                }
            });

            instVect.showVect(vect);

        } catch(Exception e) {
            e.printStackTrace();
        }

        /***************************************************************************************************/
    }
}
