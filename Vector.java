import java.util.ArrayList;

/**
 * Created by KARIM on 19/10/2017.
 */
public interface Vector {
    public VectorHelper.InnerVect calcMinMax(ArrayList<Integer> vector) throws Exception;
    public void calcFormule(ArrayList<Integer> vector, VectorHelper.Expr expression) throws Exception;
}
