package tn1.popo.Hopfully.JSONHandlers;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONHandlerIntegral {

    public String simplePrimitiveJSON(String expression, String variable) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("expression",expression);
        object.put("variable",variable);
        return object.toString();
    }
    public String doublePrimitiveJSON(String expression, String variables) throws JSONException {
        JSONObject object = new JSONObject();
        String vars = variables.substring(0,variables.length()-1);
        object.put("expression",expression);
        object.put("variables",vars);
        return object.toString();
    }
    public String triplePrimitiveJSON(String expression, String variables) throws JSONException {
        JSONObject object = new JSONObject();
        String vars = variables.substring(0,variables.length()-1);
        object.put("expression",expression);
        object.put("variables",vars);
        return object.toString();
    }
    public String simpleIntegralJSON(String expression , String variable,
                              String up_lim , String low_lim
                               ) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("expression",expression);
        object.put("variable",variable);
        object.put("upper_limit",up_lim);
        object.put("lower_limit",low_lim);
        return object.toString();
    }
    public String doubleIntegralJSON(String expression , String variables,String x_limits,String y_limits) throws JSONException {
        JSONObject object = new JSONObject();
        String vars = variables.substring(0,variables.length()-1);
        String x = x_limits.substring(0,x_limits.length()-1);
        String y = y_limits.substring(0,y_limits.length()-1);
        object.put("expression",expression);
        object.put("variables",vars);
        object.put("x_limits",x);
        object.put("y_limits",y);
        return object.toString();
    }
    public String tripleIntegralJSON(String expression , String variables,String x_limits,String y_limits,String z_limits) throws JSONException {
        JSONObject object = new JSONObject();
        String vars = variables.substring(0,variables.length()-1);
        String x = x_limits.substring(0,x_limits.length()-1);
        String y = y_limits.substring(0,y_limits.length()-1);
        String z = z_limits.substring(0,z_limits.length()-1);
        object.put("expression",expression);
        object.put("variables",vars);
        object.put("x_limits",x);
        object.put("y_limits",y);
        object.put("z_limits",z);
        return object.toString();
    }


}
