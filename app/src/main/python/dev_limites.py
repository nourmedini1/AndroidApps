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
  #  print(parse_expr((origin),
 #                    transformations=(standard_transformations +
  #                                    (implicit_multiplication_application,))))
    return parse_expr((origin),
                      transformations=(standard_transformations +
                                       (implicit_multiplication_application,)))



def taylor_series(args: str) -> str:
    args_dict = json.loads(args)
    variable = args_dict["variable"]
    v = smp.Symbol(variable)
    expression = parse(args_dict["expression"])
    sympified_expression = smp.sympify(expression)
    latex_expression = smp.latex(sympified_expression)+" = "
    order = args_dict["order"]
    near = args_dict["near"]
    if near.isnumeric() :
        near = float(near)
    else :
        near = smp.sympify(near)
    series = smp.series(smp.simplify(sympified_expression), v, near, n=order)
    return latex_expression+" =\\\\ "+smp.latex(series)

#print(taylor_series('{"expression" : "(sin(x)*ln(x))/exp(x)","variable" : "x","near" : "0" ,"order" : 4 }'))
