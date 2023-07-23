import sympy as smp
import json

def domain(args  :str) -> str :
    args_dict = json.loads(args)
    expression = args_dict["expression"]
    variable = smp.Symbol(args_dict["variable"])
    func = smp.sympify(expression)
    result = func.domain.as_set()
    return smp.latex(result)

print(domain('{"expression" : "ln(x)","variable" : "x"}'))