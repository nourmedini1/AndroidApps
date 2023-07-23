package tn1.popo.Hopfully.JSONHandlers;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONHandlerPlotGraph {
    public String plotGraph2DJSON(String expression  ,String variable, double startBound , double endBound , int precision ) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("expression",expression);
        object.put("variable",variable);
        object.put("start_bound",startBound);
        object.put("end_bound",endBound);
        object.put("precision",precision);
        return object.toString();
    }
    public String plotGraph3DJSON(String expression , String variables , double startBoundX , double endBoundX ,
                           double startBoundY , double endBoundY
                            ) throws JSONException {
        String vars = variables.substring(0,variables.length()-1);
        JSONObject object = new JSONObject();
        object.put("expression",expression);
        object.put("variables",vars);
        object.put("start_bound_x",startBoundX);
        object.put("end_bound_x",endBoundX);
        object.put("start_bound_y",startBoundY);
        object.put("end_bound_y",endBoundY);
        object.put("precision",10);
        return object.toString();
    }

}
