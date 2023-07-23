import json
import sympy as smp


def complex_ops(args: str) -> str:
    args_dict = json.loads(args)
    real1 = args_dict["real1"]
    imag1 = args_dict["imag1"]
    real2 = args_dict["real2"]
    imag2 = args_dict["imag2"]
    op = args_dict["op"]
    if op == "*":
        product_real = real1 * real2 - imag1 * imag2
        product_imaginary = real1 * imag2 + imag1 * real2
        exp = complex(product_real, product_imaginary)
        z = product_real + product_imaginary * smp.I
        theta = smp.arg(z)
        r = smp.Abs(z)
        res = smp.latex(r * smp.exp(smp.I * theta))
        if product_imaginary < 0 :
           product_imaginary = -1*product_imaginary
           return smp.latex(product_real - smp.I*product_imaginary)+"&"+res
        else :
            return smp.latex(product_real + smp.I*product_imaginary)+"&"+res

    if op == "-":
        product_real = real1 - real2
        product_imaginary = imag1 - imag2
        exp = complex(product_real, product_imaginary)
        z = product_real + product_imaginary * smp.I
        theta = smp.arg(z)
        r = smp.Abs(z)
        res = smp.latex(r * smp.exp(smp.I * theta))
        if product_imaginary < 0 :
           product_imaginary = -1*product_imaginary
           return smp.latex(product_real - smp.I*product_imaginary)+"&"+res
        else :
           return smp.latex(product_real + smp.I*product_imaginary)+"&"+res
    if op == "+":
        product_real = real1 + real2
        product_imaginary = imag1 + imag2
        exp = complex(product_real, product_imaginary)
        z = product_real + product_imaginary * smp.I
        theta = smp.arg(z)
        r = smp.Abs(z)
        res = smp.latex(r * smp.exp(smp.I * theta))
        if product_imaginary < 0 :
           product_imaginary = -1*product_imaginary
           return smp.latex(product_real - smp.I*product_imaginary)+"&"+res
        else :
            return smp.latex(product_real + smp.I*product_imaginary)+"&"+res

def polar(args: str) -> str:
    args_dict = json.loads(args)
    real = args_dict["real"]
    imag = args_dict["imag"]
    z = real + imag * smp.I
    theta = smp.arg(z)
    r = smp.Abs(z)
    res = smp.latex(r * smp.exp(smp.I * theta))
    return res


def latexize(args: str) -> str:
    args_dict = json.loads(args)
    real = args_dict["real"]
    imag = args_dict["imag"]
    if imag < 0 :
       imag = imag*(-1)
       return smp.latex(real - smp.I*imag )
    else :
       return smp.latex(real + smp.I*imag )






print(complex_ops('{"real1" : 1 , "real2" : 1 , "imag1" : 1 , "imag2" : 1 , "op" : "*"}'))
print(polar('{"real" : 1 , "imag" : 1 }'))
print(latexize('{"real" : 1 , "imag" : 1}'))
