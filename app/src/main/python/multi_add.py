import json
import sympy as smp


def multiplication(args: str) -> str:
    args_dict = json.loads(args)
    mat1 = args_dict["mat1"]
    mat2 = args_dict["mat2"]
    rows1 = args_dict["rows1"]
    cols1 = args_dict["cols1"]
    rows2 = args_dict["rows2"]
    cols2 = args_dict["cols2"]
    row1 = mat1.split("&")

    matrix1 = []
    for r in row1:
        a = r.split(",")
        b = [float(aa) for aa in a]
        matrix1.append(b)
    row2 = mat2.split("&")

    matrix2 = []
    for r in row2:
        a = r.split(",")
        b = [float(aa) for aa in a]
        matrix2.append(b)
    result = [[0 for j in range(cols2)] for i in range(rows1)]
    for i in range(rows1):
        for j in range(cols2):
            for k in range(cols1):
                result[i][j] += matrix1[i][k] * matrix2[k][j]
    return smp.latex(smp.Matrix(result))


def addition(args: str) -> str:
    args_dict = json.loads(args)
    mat1 = args_dict["mat1"]
    mat2 = args_dict["mat2"]
    rows1 = args_dict["rows1"]
    cols1 = args_dict["cols1"]
    row1 = mat1.split("&")

    matrix1 = []
    for r in row1:
        a = r.split(",")
        b = [float(aa) for aa in a]
        matrix1.append(b)
    row2 = mat2.split("&")

    matrix2 = []
    for r in row2:
        a = r.split(",")
        b = [float(aa) for aa in a]
        matrix2.append(b)
    result = [[0 for j in range(cols1)] for i in range(rows1)]
    for i in range(rows1):
        for j in range(cols1):
            result[i][j] = matrix1[i][j] + matrix2[i][j]
    return smp.latex(smp.Matrix(result))


def transpose(args : str) -> str:
    args_dict = json.loads(args)
    mat1 = args_dict["mat1"]
    rows = args_dict["rows"]
    cols = args_dict["cols"]
    row1 = mat1.split("&")

    matrix1 = []
    for r in row1:
        a = r.split(",")
        b = [float(aa) for aa in a]
        matrix1.append(b)
    result = [[0 for j in range(rows)] for i in range(cols)]
    for i in range(rows):
        for j in range(cols):
            result[j][i] = matrix1[i][j]
    return smp.latex(smp.Matrix(result))

print(multiplication('{"mat1" : "1,0,0&0,1,0&1,0,1","mat2" : "1,0,0&0,1,0&1,0,2","rows1" : 3,"cols1" : 3,"rows2" : 3,'
                     '"cols2" : 3}'))
print(addition('{"mat1" : "1,0,0&0,1,0&1,0,1","mat2" : "1,0,0&0,1,0&1,0,2","rows1" : 3,"cols1" : 3}'))
print(transpose('{"mat1" : "1,0,0&0,1,0&1,0,2","rows" : 3,"cols" : 3}'))

