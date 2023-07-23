import io
import numpy as np
import sympy as smp
import json
import base64
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D



def plot_graph_2d(args: str):
    args_dict = json.loads(args)
    expression = args_dict["expression"]
    precision = args_dict["precision"]
    variable = args_dict["variable"]
    v = smp.symbols(variable)
    sympified_expression = smp.sympify(expression)
    lambdified_expression = smp.lambdify(v, sympified_expression, "numpy")
    start_bound = args_dict["start_bound"]
    end_bound = args_dict["end_bound"]
    nb_points = int(end_bound - start_bound) * precision
    x_axe = np.linspace(start_bound, end_bound, nb_points)
    plt.clf()
    p = plt.plot(x_axe, lambdified_expression(x_axe))
    plt.xlabel(variable)
    plt.ylabel('f('+variable+')')
    f = io.BytesIO()
    plt.savefig(f, format="png")
    f.seek(0)
    encoded_image = base64.b64encode(f.read()).decode()
    #print(encoded_image)
    return encoded_image


def plot_graph_3d(args: str):
    args_dict = json.loads(args)
    variables = args_dict["variables"].split(",")
    symp_variables = [smp.Symbol(var) for var in variables]
    expression = args_dict["expression"]
    precision = args_dict["precision"]
    sympified_expression = smp.sympify(expression)
    start_bound_x = args_dict["start_bound_x"]
    end_bound_x = args_dict["end_bound_x"]
    start_bound_y = args_dict["start_bound_y"]
    end_bound_y = args_dict["end_bound_y"]
    nb_points_x, nb_points_y = int(end_bound_x - start_bound_x) * precision, int(
        end_bound_y - start_bound_y) * precision
    x_axe, y_axe = np.meshgrid(np.linspace(start_bound_x, end_bound_x, nb_points_x),
                                       np.linspace(start_bound_y, end_bound_y, nb_points_y))
    Z = np.array([[float(sympified_expression.evalf(subs={symp_variables[0]: i, symp_variables[1]: j})) for i, j in zip(x_row, y_row)] for x_row, y_row in zip(x_axe, y_axe)])
    plt.clf()
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')

    ax.plot_surface(x_axe, y_axe, Z)
    ax.set_xlabel(variables[0])
    ax.set_ylabel(variables[1])
    ax.set_zlabel('f(' + variables[0] + ',' + variables[1] + ')')
    f = io.BytesIO()
    plt.savefig(f, format='jpeg')
    f.seek(0)
    encoded_image = base64.b64encode(f.read()).decode()
    return encoded_image

    # print(plot_graph_2d('{"expression" : "ln(theta)" , "start_bound" : 1,"end_bound" :2000,"precision" : 100 , "variable" '
    #           ': "theta"}'))


#print(plot_graph_3d('{"expression" : "(x^2) + (y^2)" , "start_bound_x" : -2,"end_bound_x" :2,'
            #   '"start_bound_y" : -2,"end_bound_y" :2,"precision" : 10 , "variables" : "x,y"}'))
