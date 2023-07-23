package tn1.popo.Hopfully.JSONHandlers;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONHandlerEigenV {
    public String eigenVJSON(String coefficients) throws JSONException {
        JSONObject object = new JSONObject();
        String toSend = coefficients.substring(0,coefficients.length()-1);
        return toSend ;
    }

}
