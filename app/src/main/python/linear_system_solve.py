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



def linear_system_solve(args: str) -> str:
    begin = "\\begin{cases} "
    end = "\\end{cases}"
    seperator = "\\\\ "
    args_dict = json.loads(args)
    teqs = args_dict["eqs"].split(",")
    eqs = [parsed(eq) for eq in teqs]
    print(eqs)
    number_var = args_dict["number_var"].split(",")
    li = " ".join(number_var)
    sec = args_dict["second_members"].split(",")
    second_members = [float(s) for s in sec]
    vars_used = smp.symbols(li)
    sympified_eqs = [smp.Eq(smp.parse_expr(eqs[i]), second_members[i]) for i in range(len(eqs))]
    latex_eqs = [smp.latex(eq) for eq in sympified_eqs]
    result_latex = ""
    for i in range(len(second_members) - 1):
        result_latex += latex_eqs[i] + seperator
    result_latex_final = begin + result_latex + latex_eqs[-1] + end
    results = list(smp.linsolve(sympified_eqs, tuple(vars_used)))
    result_solve = [smp.latex(vars_used[i]) + " = " + smp.latex(results[0][i]) for i in range(len(results[0]))]
    final_latex = ""
    for el in result_solve:
        final_latex += el + seperator
    print("done")
    return result_latex_final + "&" + final_latex


#print(linear_system_solve(
  #  '{"number_var" : 3 , "eqs" : "2*x0 +2*x1,1*x0 + 4*x1,x0 + 3*x2","second_members" : "8,8,4"}'))
