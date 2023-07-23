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
   # print(parse_expr((origin),
   #                  transformations=(standard_transformations +
#                                      (implicit_multiplication_application,))))
    return parse_expr((origin),
                      transformations=(standard_transformations +
                                       (implicit_multiplication_application,)))



def one_dimentional_derivative(args: str) -> str:
    args_dict = json.loads(args)
    order = int(args_dict["order"])
    variable = args_dict["variable"]
    v = smp.Symbol(variable)
    sympified_expression = smp.sympify(parse(args_dict["expression"]))
    derivative = smp.diff(sympified_expression, v, order)
    latex_expression = smp.latex(smp.simplify(derivative))
    if (order > 1):
        latex_der = "\\frac{\mathrm{d}^" + str(order) + "f}{\mathrm{d}^" + str(order) + smp.latex(v) + " } = "
    else:
        latex_der = "\\frac{\mathrm{d}f}{\mathrm{d}" + smp.latex(v) + " } = "
    derivative_result = latex_der + latex_expression
    return derivative_result


def one_dimentional_derivative_value(args: str) -> str:
    args_dict = json.loads(args)
    order = int(args_dict["order"])
    expression = parse(args_dict["expression"])
    value_for_x = (args_dict["derive_in"])
    variable = args_dict["variable"]
    v = smp.Symbol(variable)
    sympified_expression = smp.sympify(expression)
    if value_for_x.isnumeric():
        value_for_x = float(value_for_x)
    if (order > 1):
        latex_der = "\\frac{\mathrm{d}^" + str(order) + "f}{\mathrm{d}^" + str(order) + smp.latex(v) + " } = "
    else:
        latex_der = "\\frac{\mathrm{d}f}{\mathrm{d}" + smp.latex(v) + " } = "
    derivative_value = smp.diff(sympified_expression, v) \
        .subs([(v, value_for_x)])
    return latex_der + smp.latex(derivative_value)


def partial_derivative_on_x(args: str) -> str:
    args_dict = json.loads(args)
    order = int(args_dict["order"])
    expression = parse(args_dict["expression"])
    variable = args_dict["variable"]
    v = smp.Symbol(variable)
    sympified_expression = smp.sympify(expression)
    derivative = smp.diff(sympified_expression, v, order)
    latex_expression = smp.latex(smp.simplify(derivative))
    if (order > 1):
        der_latex = "\\frac{\partial^" + str(order) + "f}{\partial^" + str(order) + smp.latex(v) + "} = "
    else:
        der_latex = "\\frac{\partial f}{\partial " + smp.latex(v) + "} = "
    derivative_result = der_latex + latex_expression
    return derivative_result



def partial_derivative_value(args : str) -> str :
    args_dict = json.loads(args)
    expression = parse(args_dict["expression"])
    order = args_dict["order"]
    derive_in = (args_dict["derive_in"])
    precision = int(args_dict["precision"])
    variable = args_dict["variable"]
    v = smp.Symbol(variable)
    func = smp.simplify(smp.sympify(expression))
    if derive_in.isnumeric():
        value_for_x = float(derive_in)
    derivative = smp.diff(func,v,order,).subs(v,derive_in).evalf(precision)
    if (order > 1):
        der_latex = "\\frac{\partial^" + str(order) + "f}{\partial^" + str(order) + smp.latex(v) + "} = "
    else:
        der_latex = "\\frac{\partial f}{\partial " + smp.latex(v) + "} = "
    result = derivative.evalf(precision)
    return der_latex + smp.latex(result)


#print(one_dimentional_derivative('{"expression" : "ln(theta)" , "order" : 2,"variable" : "theta" }'))
#print(one_dimentional_derivative_value(
 #   '{"expression" : "ln(theta)", "order" : 1 ,"derive_in" : "0","variable" : "theta"}'))
#print(partial_derivative_value(
 #   '{"expression" : "ln(theta) + y" , "derive_in" : "0" ,"precision" : 10,"variable" : "theta","order" : 1}'))
#print(partial_derivative_on_x('{"expression" : "z*ln(theta) + y + z^2" , "order" : 2,"variable":"theta" }'))
