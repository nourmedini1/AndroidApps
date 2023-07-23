package tn1.popo.Hopfully.JSONHandlers;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONHandlerMatOps {
    public String Multi(int cols1 , int rows1 , int cols2 , int rows2 , String mat1 , String mat2) throws JSONException {
        JSONObject object = new JSONObject();
        String tomat1 = mat1.substring(0,mat1.length()-1);
        String tomat2 = mat2.substring(0,mat2.length()-1);
        object.put("mat1",tomat1);
        object.put("mat2",tomat2);
        object.put("cols1",cols1);
        object.put("rows1",rows1);
        object.put("cols2",cols2);
        object.put("rows2",rows2);
        return object.toString();
    }
    public String Add(int cols1 , int rows1 , String mat1 , String mat2) throws JSONException {
        JSONObject object = new JSONObject();
        String tomat1 = mat1.substring(0,mat1.length()-1);
        String tomat2 = mat2.substring(0,mat2.length()-1);
        object.put("mat1",tomat1);
        object.put("mat2",tomat2);
        object.put("cols1",cols1);
        object.put("rows1",rows1);

        return object.toString();
    }
    public String Trans(int cols , int rows , String mat1 ) throws JSONException {
        JSONObject object = new JSONObject();
        String tomat1 = mat1.substring(0,mat1.length()-1);
        object.put("mat1",tomat1);
        object.put("cols",cols);
        object.put("rows",rows);

        return object.toString();
    }




}
