import sympy as smp
import json
import numpy as np
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





def simple_primitive(args: str) -> str:
   print(1)
   args_dict = json.loads(args)
   print(2)
   expression = parse(args_dict["expression"])
   print(3)
   variable = args_dict["variable"]
   print(4)
   v = smp.Symbol(variable)
   print(5)
   sympified_expression = smp.sympify(expression)
   print(6)
   before_integration_latex = smp.latex(smp.Integral(sympified_expression, v)) + " = "
   print(7)
   integral = smp.sympify(smp.integrate(sympified_expression, v))
   print(8)
   result_latex = smp.latex(integral)
   print(9)
   return before_integration_latex + result_latex + " +c"


def double_primitive(args: str) -> str:
    args_dict = json.loads(args)
    expression = parse(args_dict["expression"])
    variables = args_dict["variables"].split(",")
    symp_variables = [smp.Symbol(var) for var in variables]
    sympified_expression = smp.sympify(expression)
    before_integration_latex = smp.latex(
        smp.Integral(smp.Integral(sympified_expression, symp_variables[0]), symp_variables[1])) + " = "
    integral = smp.sympify(smp.integrate(sympified_expression, symp_variables[0], symp_variables[1]))
    result_latex = smp.latex(integral)
    print("double primitive done")
    return before_integration_latex + result_latex + " +c"


def triple_primitive(args: str) -> str:
    args_dict = json.loads(args)
    expression = parse(args_dict["expression"])
    variables = args_dict["variables"].split(",")
    symp_variables = [smp.Symbol(var) for var in variables]
    sympified_expression = smp.sympify(expression)
    before_int_lat = smp.latex(
        smp.Integral(smp.Integral(smp.Integral(sympified_expression, symp_variables[0]), symp_variables[1]),symp_variables[2])) + " = "


    integral = smp.sympify(smp.integrate(sympified_expression, symp_variables[0], symp_variables[1], symp_variables[2]))
    result_latex = smp.latex(integral)
    print("triple primitive done")
    return before_int_lat + result_latex + " +c"

def simple_integral(args: str) -> str:
    args_dict = json.loads(args)
    expression = parse(args_dict["expression"])
    variable = args_dict["variable"]
    v = smp.Symbol(variable)
    func = smp.simplify(smp.sympify(expression))
    low_lim = args_dict["lower_limit"]
    upp_lim = args_dict["upper_limit"]
    if low_lim.isnumeric():
        low_lim = float(low_lim)
    if upp_lim.isnumeric():
        upp_lim = float(upp_lim)
    latex_expression = smp.Integral(func, (v, low_lim, upp_lim))
    result = smp.integrate(func, (v, low_lim, upp_lim))
    return smp.latex(latex_expression) + " = " + smp.latex(result)

def simple_integral_depricated(args: str) -> str:
    args_dict = json.loads(args)
    expression = parse(args_dict["expression"])
    lower_limit = float(args_dict["lower_limit"])
    upper_limit = float(args_dict["upper_limit"])
    precision_x = int(args_dict["precision_x"])
    precision_result = int(args_dict["precision_result"])
    variable = args_dict["variable"]
    v = smp.Symbol(variable)
    func = smp.simplify(smp.sympify(expression))
    final_result = smp.Integral(func, (v, lower_limit, upper_limit))
    if lower_limit == upper_limit:
        return str(0)
    number_of_values = int((upper_limit - lower_limit) * precision_x)
    x_axe = np.linspace(lower_limit, upper_limit, number_of_values)
    result = 0
    for value in range(len(x_axe) - 1):
        dx = x_axe[value + 1] - x_axe[value]
        try:
            y = func.subs(v, x_axe[value]).evalf(precision_result)
            ydy = func.subs(v, x_axe[value + 1]).evalf(precision_result)
            result += (y + ydy) / 2 * dx
        except ZeroDivisionError:
            continue
    return smp.latex(final_result) + " = " + smp.latex(result)



def double_integral(args: str) -> str:
    args_dict = json.loads(args)
    expression = parse(args_dict["expression"])
    variables = args_dict["variables"].split(",")
    symp_variables = [smp.Symbol(var) for var in variables]
    func = smp.simplify(smp.sympify(expression))
    x_limits = args_dict["x_limits"].split(",")
    y_limits = args_dict["y_limits"].split(",")
    (xa, xb), (ya, yb) = x_limits, y_limits
    if xa.isnumeric() :
        xa = float(xa)
    if xb.isnumeric() :
        xb = float(xb)
    if ya.isnumeric() :
        ya = float(ya)
    if yb.isnumeric():
        yb = float(yb)
    latex_expression = smp.Integral(smp.Integral(func, (symp_variables[0], xa, xb)), (symp_variables[1], ya, yb))
    result = smp.integrate(func, (symp_variables[0], xa, xb), (symp_variables[1], ya, yb))
    return smp.latex(latex_expression) + " = " + smp.latex(result)


def triple_integral(args: str) -> str:
    args_dict = json.loads(args)
    expression = parse(args_dict["expression"])
    variables = args_dict["variables"].split(",")
    symp_variables = [smp.Symbol(var) for var in variables]
    func = smp.simplify(smp.sympify(expression))
    x_limits = args_dict["x_limits"].split(",")
    y_limits = args_dict["y_limits"].split(",")
    z_limits = args_dict["z_limits"].split(",")
    (xa, xb), (ya, yb), (za, zb) = x_limits, y_limits, z_limits
    if xa.isnumeric() :
        xa = float(xa)
    if xb.isnumeric() :
        xb = float(xb)
    if ya.isnumeric() :
        ya = float(ya)
    if yb.isnumeric():
        yb = float(yb)
    if za.isnumeric() :
        za = float(za)
    if zb.isnumeric() :
        zb = float(zb)
    latex_expression = smp.Integral(
        smp.Integral(smp.Integral(func, (symp_variables[0], xa, xb)), (symp_variables[1], ya, yb)),
        (symp_variables[2], za, zb))
    result = smp.integrate(func, (symp_variables[0], xa, xb), (symp_variables[1], ya, yb), (symp_variables[2], za, zb))
    return smp.latex(latex_expression) + " = " + smp.latex(result)


#print(simple_primitive('{"expression" : "1/x","variable": "x"}'))
#print(double_primitive('{"expression" : "((1/x) + y)","variables" : "x,y"}'))
#print(triple_primitive('{"expression" : "((1/x) +y + z)","variables" : "x,y,z"}'))
#print(simple_integral('{"expression":"sin(x)/x","lower_limit":"-1","upper_limit":"1",'
                    #  '"variable" : "x"}'))
#print(double_integral(
  #  '{"expression" : "exp(-((x^2) + (y^2)))","x_limits" : "-oo,oo" , "y_limits" : "-oo,oo" ,"variables" : "x,y"}'))
#print(triple_integral(('{"expression" : "exp(-((x^2) + (y^2) + (z^2)))","x_limits" : "-oo,oo" ,'
             #          ' "y_limits" : "-oo,oo","z_limits" : "-oo,oo","variables" : "x,y,z"}')))
