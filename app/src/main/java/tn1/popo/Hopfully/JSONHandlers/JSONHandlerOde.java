package tn1.popo.Hopfully.JSONHandlers;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONHandlerOde {

    public String firstOrderOdeJSON(String fCoeff , String fPrimCoeff , String constant,
                             String variable ,
                             String secondMember,
                             String icf ,
                             String icfp
                              ) throws JSONException {
        if(!icf.equals(""))
            icf = icf.substring(0,icf.length()-1) ;
        if(!icfp.equals(""))
            icfp = icfp.substring(0,icfp.length()-1) ;
        JSONObject object = new JSONObject();
        object.put("f_coefficient",fCoeff);
        object.put("f_prime_coefficient",fPrimCoeff);
        object.put("constant",constant);
        object.put("second_member",secondMember);
        object.put("ic_f",icf);
        object.put("ic_f_prime",icfp);
        object.put("variable",variable);
        return object.toString();
    }
    public String secondOrderOdeJSON(String fCoeff , String fPrimCoeff ,String fsCoeff, String constant,
                             String variable ,
                             String secondMember,
                             String icf ,
                             String icfp ,
                              String icfs
                              ) throws JSONException {
        if(!icf.equals(""))
            icf = icf.substring(0,icf.length()-1) ;
        if(!icfp.equals(""))
            icfp = icfp.substring(0,icfp.length()-1) ;
        if(!icfs.equals(""))
            icfs = icfs.substring(0,icfs.length()-1) ;
        JSONObject object = new JSONObject();
        object.put("f_coefficient",fCoeff);
        object.put("f_prime_coefficient",fPrimCoeff);
        object.put("f_second_coefficient",fsCoeff);
        object.put("constant",constant);
        object.put("second_member",secondMember);
        object.put("ic_f",icf);
        object.put("ic_f_prime",icfp);
        object.put("ic_f_second",icfs);
        object.put("variable",variable);
        return object.toString();
    }
    public String thirdOrderOdeJSON(String fCoeff , String fPrimCoeff ,String fscoeff,String ftcoeff, String constant,
                              String variable ,
                              String secondMember,
                              String icf ,
                              String icfp ,
                              String icfs,
                              String icft
                              ) throws JSONException {
        if(!icf.equals(""))
            icf = icf.substring(0,icf.length()-1) ;
        if(!icfp.equals(""))
            icfp = icfp.substring(0,icfp.length()-1) ;
        if(!icfs.equals(""))
            icfs = icfs.substring(0,icfs.length()-1) ;
        if(!icft.equals(""))
             icft = icft.substring(0,icft.length()-1);
        JSONObject object = new JSONObject();
        object.put("f_coefficient",fCoeff);
        object.put("f_prime_coefficient",fPrimCoeff);
        object.put("f_second_coefficient",fscoeff);
        object.put("f_third_coefficient",ftcoeff);
        object.put("constant",constant);
        object.put("second_member",secondMember);
        object.put("ic_f",icf);
        object.put("ic_f_prime",icfp);
        object.put("ic_f_second",icfs);
        object.put("ic_f_third",icft);
        object.put("variable",variable);
        return object.toString();
    }
    String plotFirstOrderOdeJSON(String fCoeff , String fPrimCoeff , String constant,
                             String variable ,
                             String secondMember,
                             String icf ,
                             String icfp
                              ) throws JSONException {
        if(!icf.equals(""))
            icf = icf.substring(0,icf.length()-1) ;
        if(!icfp.equals(""))
            icfp = icfp.substring(0,icfp.length()-1) ;
        JSONObject object = new JSONObject();
        object.put("f_coefficient",fCoeff);
        object.put("f_prime_coefficient",fPrimCoeff);
        object.put("constant",constant);
        object.put("second_member",secondMember);
        object.put("ic_f",icf);
        object.put("ic_f_prime",icfp);
        object.put("variable",variable);
        return object.toString();
    }




}
