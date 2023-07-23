package tn1.popo.Hopfully.JSONHandlers;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONHandlerLimits {
    public String limitSingleVariableJSON(String expression ,String variable,String sign,String bound) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("expression",expression);
        object.put("variable",variable);
        object.put("bound",bound);
        object.put("sign",sign);
        return object.toString();
    }
    public String limitDoubleVariableJSON(String expression ,
                                   String variables,String sign_1,String bound_1,
                                   String sign_2,String bound_2) throws JSONException {
        JSONObject object = new JSONObject();
        String vars = variables.substring(0,variables.length()-1);
        object.put("expression",expression);
        object.put("variables",vars);
        object.put("bound_1",bound_1);
        object.put("bound_2",bound_2);
        object.put("sign_1",sign_1);
        object.put("sign_2",sign_2);
        return object.toString();
    }
    public String limitTripleVariableJSON(String expression ,
                                  String variables,String sign_1,String bound_1,
                                  String sign_2,String bound_2,
                                  String sign_3,
                                  String bound_3) throws JSONException {
        JSONObject object = new JSONObject();
        String vars = variables.substring(0,variables.length()-1);
        object.put("expression",expression);
        object.put("variables",vars);
        object.put("bound_1",bound_1);
        object.put("bound_2",bound_2);
        object.put("bound_3",bound_3);
        object.put("sign_1",sign_1);
        object.put("sign_2",sign_2);
        object.put("sign_3",sign_3);
        return object.toString();
    }
}
