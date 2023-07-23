import json
import sympy as smp
from fractions import Fraction
import numpy as np


def eigenvector(args: str) -> str:
    mat = args
    rows = mat.split("&")
    matrix = []
    for r in rows:
        a = r.split(",")
        b = [float(aa) for aa in a]
        matrix.append(b)
    nparr = np.array(matrix)
    eigen_value, eigen_vector = np.linalg.eig(nparr)
    eiga = []
    for el in eigen_value:
        eiga.append(el)
    eigav = []
    for row in eigen_vector:
        e = []
        for el in row:
            e.append(el)
        eigav.append(e)
    latex_eigen_value = smp.latex(smp.Matrix(eiga))
    latex_eigen_vector = smp.latex(smp.Matrix(eigav))
    print(latex_eigen_value+"ù"+latex_eigen_vector)
    return latex_eigen_value + "ù" + latex_eigen_vector


#print(eigenvector("0,2&2,3"))
