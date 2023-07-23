package tn1.popo.Hopfully.JSONHandlers;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONHandlerLinearSystem {
    public String linearSystemSolveJSON(String equations , String nb_vars , String second_member) throws JSONException {
        equations = equations.substring(0,equations.length()-1);
        second_member = second_member.substring(0,second_member.length()-1);
        JSONObject object = new JSONObject();
        object.put("eqs",equations);
        object.put("number_var",nb_vars);
        object.put("second_members",second_member);
        return object.toString();
    }
}
