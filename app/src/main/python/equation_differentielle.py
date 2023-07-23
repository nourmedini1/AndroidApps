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
  #                   transformations=(standard_transformations +
  #                                    (implicit_multiplication_application,))))
    return parse_expr((origin),
                      transformations=(standard_transformations +
                                       (implicit_multiplication_application,)))




def first_order_diff_eq(args: str) -> str:
    args_dict = json.loads(args)
    variable = args_dict["variable"]
    v = smp.Symbol(variable)

    f = smp.Function("f")

    f_coefficient = parse(args_dict["f_coefficient"])

    f_sympified = smp.sympify(f_coefficient)

    f_prime_coefficient = parse(args_dict["f_prime_coefficient"])

    f_prime_sympified = smp.sympify(f_prime_coefficient)

    constant = parse(args_dict["constant"])

    second_member = parse(args_dict["second_member"])

    second_member_sympified = smp.sympify(second_member)

    init_conditions_dict = {}

    if args_dict["ic_f"] != "":

        ic_f = args_dict["ic_f"].split(",")

        init_conditions_dict[f(ic_f[0])] = ic_f[1]

    if args_dict["ic_f_prime"] != "":

        ic_f_prime = args_dict["ic_f_prime"].split(",")

        init_conditions_dict[f(v).diff(v).subs(v, ic_f_prime[0])] = ic_f_prime[1]

    eq_diff = smp.Eq(f_sympified * f(v) + f_prime_sympified * f(v).diff(v) + smp.sympify(constant)
                     , second_member_sympified)

    latex_eq_diff = smp.latex(eq_diff)

    result = smp.dsolve(eq_diff, ics=init_conditions_dict).simplify()

    return latex_eq_diff + "\\\\,\\\\" + smp.latex(result)



def second_order_diff_eq(args: str) -> str:
    args_dict = json.loads(args)
    variable = args_dict["variable"]
    v = smp.Symbol(variable)
    f = smp.Function("f")
    f_coefficient = parse(args_dict["f_coefficient"])
    f_sympified = smp.sympify(f_coefficient)
    f_prime_coefficient = parse(args_dict["f_prime_coefficient"])
    f_prime_sympified = smp.sympify(f_prime_coefficient)
    f_second_coefficient = parse(args_dict["f_second_coefficient"])
    f_second_sympified = smp.sympify(f_second_coefficient)
    constant = parse(args_dict["constant"])
    second_member = parse(args_dict["second_member"])
    second_member_sympified = smp.sympify(second_member)
    init_conditions_dict = {}
    if args_dict["ic_f"] != "":
        ic_f = args_dict["ic_f"].split(",")
        init_conditions_dict[f(ic_f[0])] = ic_f[1]
    if args_dict["ic_f_prime"] != "":
        ic_f_prime = args_dict["ic_f_prime"].split(",")
        init_conditions_dict[f(v).diff(v).subs(v, ic_f_prime[0])] = ic_f_prime[1]
    if args_dict["ic_f_second"] != "":
        ic_f_second = args_dict["ic_f_second"].split(",")
        init_conditions_dict[f(v).diff(v, v).subs(v, ic_f_second[0])] = ic_f_second[1]
    eq_diff = smp.Eq(f_sympified * f(v) + f_prime_sympified * f(v).diff(v) +
                     f_second_sympified * f(v).diff(v, v) + smp.sympify(constant)
                     , second_member_sympified)
    latex_eq_diff = smp.latex(eq_diff)
    result = smp.dsolve(eq_diff, ics=init_conditions_dict).simplify()
    return latex_eq_diff + "\\\\,\\\\" + smp.latex(result)



def third_order_diff_eq(args: str) -> str:
    args_dict = json.loads(args)
    variable = args_dict["variable"]
    v = smp.Symbol(variable)
    f = smp.Function("f")
    f_coefficient = parse(args_dict["f_coefficient"])
    f_sympified = smp.sympify(f_coefficient)
    f_prime_coefficient = parse(args_dict["f_prime_coefficient"])
    f_prime_sympified = smp.sympify(f_prime_coefficient)
    f_second_coefficient = parse(args_dict["f_second_coefficient"])
    f_second_sympified = smp.sympify(f_second_coefficient)
    f_third_coefficient = parse(args_dict["f_third_coefficient"])
    f_third_sympified = smp.sympify(f_third_coefficient)
    constant = parse(args_dict["constant"])
    second_member = parse(args_dict["second_member"])
    second_member_sympified = smp.sympify(second_member)
    init_conditions_dict = {}
    if args_dict["ic_f"] != "":
        ic_f = args_dict["ic_f"].split(",")
        init_conditions_dict[f(ic_f[0])] = ic_f[1]
    if args_dict["ic_f_prime"] != "":
        ic_f_prime = args_dict["ic_f_prime"].split(",")
        init_conditions_dict[f(v).diff(v).subs(v, ic_f_prime[0])] = ic_f_prime[1]
    if args_dict["ic_f_second"] != "":
        ic_f_second = args_dict["ic_f_second"].split(",")
        init_conditions_dict[f(v).diff(v, v).subs(v, ic_f_second[0])] = ic_f_second[1]
    if args_dict["ic_f_third"] != "":
        ic_f_third = args_dict["ic_f_third"].split(",")
        init_conditions_dict[f(v).diff(v, v, v).subs(v, ic_f_third[0])] = ic_f_third[1]
    eq_diff = smp.Eq(f_sympified * f(v) + f_prime_sympified * f(v).diff(v) +
                     f_second_sympified * f(v).diff(v, v) + f_third_sympified * f(v).diff(v, v, v) + smp.sympify(
        constant)
                     , second_member_sympified)
    latex_eq_diff = smp.latex(eq_diff)
    result = smp.dsolve(eq_diff, ics=init_conditions_dict).simplify()
    return latex_eq_diff + "\\\\,\\\\" + smp.latex(result)



#print(first_order_diff_eq('{"f_coefficient":"x",'
         #                 '"f_prime_coefficient":"1",'
         #                 '"constant":"0",'
         #                 '"variable":"x",'
           #                '"second_member":"x^3",'
             #              '"ic_f":"0,1",'
               #            '"ic_f_prime":""}'))

 #print(second_order_diff_eq('{"f_coefficient":"1",'
   #                         '"f_prime_coefficient":"0",'
     #                       '"f_second_coefficient":"1",'
       #                     '"constant":"0",'
         #                   '"variable":"x", '
         #                   '"second_member":"1",'
          #                  '"ic_f":"0,0","ic_f_prime":"0,0",'
           #                 '"ic_f_second":""}'))

 #print(third_order_diff_eq('{"f_coefficient":"1",'
   #                        '"f_prime_coefficient":"0",'
     #                      '"f_second_coefficient":"0",'
       #                    '"f_third_coefficient":"1",'
         #                  '"constant":"0", '
           #                '"variable":"x", '
             #              '"second_member":"1",'
              #             '"ic_f":"",'
                #           '"ic_f_prime":"",'
                  #         '"ic_f_second":"",'
                    #       '"ic_f_third":""}'))
