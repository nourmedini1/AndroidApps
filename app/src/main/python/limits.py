import sympy as smp
import json
from sympy.parsing.sympy_parser import (parse_expr, standard_transformations, implicit_multiplication_application)


def parsed(origin: str) -> str:
    result = list(origin)
    for i in range(len(result) - 1):
        if result[i].isdigit() and result[i + 1].isalpha():
            result.insert(i + 1, "*")
    return "".join(result)


def parse(origin: str) -> str:
    t = list(origin)
    for i in range(len(t)):
        if t[i] == "^":
            t[i] = "**"
    origin = "".join(t)
    print(parse_expr((origin),
                     transformations=(standard_transformations +
                                      (implicit_multiplication_application,))))
    return parse_expr((origin),
                      transformations=(standard_transformations +
                                       (implicit_multiplication_application,)))



def calculate_limit_single_variable(args: str) -> str:
    args_dict = json.loads(args)
    expression = parse(args_dict["expression"])
    bound = smp.sympify(args_dict["bound"])
    sympified_expression = smp.simplify(smp.sympify(expression))
    variable = args_dict["variable"]
    sign = args_dict["sign"]
    v = smp.Symbol(variable)
    limit = smp.Limit(sympified_expression, v, bound, sign)
    result = limit.doit().evalf()
    latex_limit = smp.latex(limit)
    return latex_limit + " = " + smp.latex(result)


def calculate_limit_two_variables(args: str) -> str:
    args_dict = json.loads(args)
    expression = parse(args_dict["expression"])
    bound_1 = smp.sympify(args_dict["bound_1"])
    bound_2 = smp.sympify(args_dict["bound_2"])
    sympified_expression = smp.simplify(smp.sympify(expression))
    variables = args_dict["variables"].split(",")
    symp_variables = [smp.Symbol(var) for var in variables]
    sign_1 = args_dict["sign_1"]
    sign_2 = args_dict["sign_2"]
    limit = smp.Limit(sympified_expression, tuple(symp_variables), (bound_1, bound_2), sign_1)
    result = sympified_expression.limit(symp_variables[0], bound_1, sign_1).limit(symp_variables[1], bound_2,
                                                                                  sign_2).evalf()
    latex_limit = smp.latex(limit)
    return latex_limit + " = " + smp.latex(result)


def calculate_limit_three_variables(args: str) -> str:
    args_dict = json.loads(args)
    expression = parse(args_dict["expression"])
    bound_1 = smp.sympify(args_dict["bound_1"])
    bound_2 = smp.sympify(args_dict["bound_2"])
    bound_3 = smp.sympify(args_dict["bound_3"])
    sympified_expression = smp.simplify(smp.sympify(expression))
    variables = args_dict["variables"].split(",")
    symp_variables = [smp.Symbol(var) for var in variables]
    sign_1 = args_dict["sign_1"]
    sign_2 = args_dict["sign_2"]
    sign_3 = args_dict["sign_3"]
    limit = smp.Limit(sympified_expression, tuple(symp_variables), (bound_1, bound_2, bound_3), sign_1)
    result = sympified_expression.limit(symp_variables[0], bound_1, sign_1) \
        .limit(symp_variables[1], bound_2, sign_2) \
        .limit(symp_variables[2], bound_3, sign_3).evalf()
    latex_limit = smp.latex(limit)
    return latex_limit + " = " + smp.latex(result)


#print(calculate_limit_single_variable('{"expression" : "ln(x)" , "variable" : "x" , "bound" : "oo","sign" : "-"}'))
#print(calculate_limit_two_variables('{"expression" : "ln(theta)+alpha" , "variables" : "theta,alpha" ,'
  #                                  ' "bound_1" : "-oo","bound_2" : "1","sign_1" : "-","sign_2" : "-"}'))
#print(calculate_limit_three_variables('{"expression" : "ln(theta)+alpha + r + k" , "variables" : "theta,alpha,r" ,'
#                                    ' "bound_1" : "0","bound_2" : "1","bound_3" : "1","sign_1" : "+","sign_2" : "-","sign_3" : "+"}'))
