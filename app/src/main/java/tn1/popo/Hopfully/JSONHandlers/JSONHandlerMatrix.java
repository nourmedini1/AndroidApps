package tn1.popo.Hopfully.JSONHandlers;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONHandlerMatrix {
    public String inverseMatrixJSON(int order , String coefficients) throws JSONException {
        JSONObject object = new JSONObject();
        String toSend = coefficients.substring(0,coefficients.length()-1);
        object.put("matrix",toSend);
        object.put("order",order);
        return object.toString();
    }
    public String determinantJSON(int order, String coefficients) throws JSONException {
        JSONObject object = new JSONObject();
        String toSend = coefficients.substring(0,coefficients.length()-1);
        object.put("matrix",toSend);
        object.put("order",order);
        return object.toString();
    }
    public String rankJSON(int order, String coefficients) throws JSONException {
        JSONObject object = new JSONObject();
        String toSend = coefficients.substring(0,coefficients.length()-1);
        object.put("matrix",toSend);
        object.put("order",order);
        return object.toString();
    }


}
