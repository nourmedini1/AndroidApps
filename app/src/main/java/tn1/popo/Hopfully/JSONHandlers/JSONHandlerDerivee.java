package tn1.popo.Hopfully.JSONHandlers;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONHandlerDerivee {

    public String oneDimentionalDerivativeJSON(String expression, int order, String variable) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("expression", expression);
        object.put("order", order);
        object.put("variable", variable);
        return object.toString();
    }

    public String oneDimentionalDerivativeValueJSON(String expression, int order, String variable, String derive_in) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("expression", expression);
        object.put("order", order);
        object.put("variable", variable);
        object.put("derive_in", derive_in);
        return object.toString();
    }

    public String partialDerivativeJSON(String expression, int order, String variable) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("expression", expression);
        object.put("order", order);
        object.put("variable", variable);
        return object.toString();
    }

    public String partialDerivativeValueJSON(String expression, int order, String variable,
                                      String derive_in,
                                      int precision) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("expression", expression);
        object.put("order", order);
        object.put("variable", variable);
        object.put("derive_in", derive_in);
        object.put("precision", precision);
        return object.toString();
    }
}
