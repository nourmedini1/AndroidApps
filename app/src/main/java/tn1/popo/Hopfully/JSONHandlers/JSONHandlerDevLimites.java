package tn1.popo.Hopfully.JSONHandlers;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONHandlerDevLimites {

    public String taylorSeriesJSON(String near , int order , String expression , String variable) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("expression",expression);
        object.put("order",order+1);
        object.put("near",near);
        object.put("variable",variable);
        return object.toString();
    }
}
