package tn1.popo.Hopfully.JSONHandlers;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONHandlerComplex {
    public String JSONPolar(double real , double imag) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("real",real);
        jsonObject.put("imag",imag);
        return jsonObject.toString();
    }
    public String JSONComplexOps(double real1 , double imag1,double real2 , double imag2, String op) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("real1",real1);
        jsonObject.put("imag1",imag1);
        jsonObject.put("real2",real2);
        jsonObject.put("imag2",imag2);
        jsonObject.put("op",op);
        return jsonObject.toString();
    }
    public String JSONLatexize(double real , double imag) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("real",real);
        jsonObject.put("imag",imag);
        return jsonObject.toString();
    }
}
