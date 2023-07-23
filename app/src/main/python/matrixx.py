import json
import sympy as smp


def inverse_matrix(args: str) -> str:
    args_dict = json.loads(args)
    mat = args_dict["matrix"]
    rows = mat.split("&")
    matrix = []
    for r in rows:
        a = r.split(",")
        b = [float(aa) for aa in a]
        matrix.append(b)
    n = int(args_dict["order"])

    def checkSimilarity(m):
        for i in range(n):
            for j in range(i + 1, n):
                if m[i] == m[j]:
                    return False
        else:
            return True

    def createElementaryMatrice(s, pos, q):
        m = []
        for i in range(n):
            r = []
            for j in range(n):
                if i == j:
                    r.append(1)
                else:
                    r.append(0)
            m.append(r)
        m[q][pos] = s
        return m

    def fetchPos(m, r):
        for i in range(n):
            if m[i] == r:
                return i

    def createIdentity():
        m = []
        for i in range(n):
            r = []
            for j in range(n):
                if i == j:
                    r.append(1)
                else:
                    r.append(0)
            m.append(r)
        return m

    def checkColumn(m):
        c = []
        for i in range(n):
            for j in range(1):
                c.append(0)

        for i in range(n):
            w = []
            for j in range(n):
                w.append(m[j][i])
            if w == c:
                return False
        else:
            return True

    def checkRow(m):
        c = []
        for i in range(1):
            for j in range(n):
                c.append(0)

        for i in range(n):
            w = []
            for j in range(n):
                w.append(m[i][j])
            if w == c:
                return False
        else:
            return True

    def checkInversibility(m):
        for i in range(n):
            if checkColumn(m) is False or checkRow(m) is False or checkSimilarity(m) is False:
                return False
            else:
                return True

    def permute(m, pos, r):
        test = 1
        i = r
        while i < n - 1 and test == 1:
            j = i + 1
            while j in range(i + 1, n) and test == 1:
                if m[j][pos] != 0:
                    per = []
                    per = m[i]
                    m[i] = m[j]
                    m[j] = per
                    test = 0
                j += 1
            i += 1
        return m

    def permutRes(m, l1, l2):
        per = m[l1]
        m[l1] = m[l2]

        m[l2] = per
        return m

    def calcMIN(u, v):
        if u <= v:
            return u
        else:
            return v

    def multiply(a, b):
        res = []
        for element in range(n):
            li = []
            for elem in range(n):
                li.append(0)
            res.append(li)

        for element in range(n):
            for elem in range(n):
                for k in range(n):
                    res[element][elem] += a[element][k] * b[k][elem]
        return res

    def inverse(m):
        if checkInversibility(m) is False:
            return []
        else:

            id = createIdentity()
            for i in range(n):
                for j in range(n):
                    if i == j:
                        if m[i][j] == 0:
                            dodo = []
                            dodo = m[i]
                            m = permute(m, j, i)
                            if m[i][j] == 0:
                                return []
                            id = permutRes(id, i, fetchPos(m, dodo))
                        elif m[i][j] != 1:
                            num = m[i][j]
                            t = []
                            t = createElementaryMatrice(1 / num, i, j)
                            m = multiply(t, m)
                            id = multiply(t, id)
                        for o in range(n):
                            if o != i and m[o][j] != 0:
                                t1 = []
                                t1 = createElementaryMatrice(-m[o][j], j, o)
                                m = multiply(t1, m)
                                id = multiply(t1, id)
            return (id)

    result = inverse(matrix)
    sympy_matrix = smp.Matrix(result)
    return smp.latex(sympy_matrix)

def calculate_determinant(args : str) -> str :
    args_dict = json.loads(args)
    mat = args_dict["matrix"]
    rows = mat.split("&")
    matrix = []
    for r in rows:
        a = r.split(",")
        b = [float(aa) for aa in a]
        matrix.append(b)
    n = int(args_dict["order"])

    def multiply(a, b):
        res = []
        for element in range(n):
            li = []
            for elem in range(n):
                li.append(0)
            res.append(li)

        for element in range(n):
            for elem in range(n):
                for k in range(n):
                    res[element][elem] += a[element][k] * b[k][elem]
        return res

    def permute(m, pos, r):
        test = 1
        i = r
        while i < n - 1 and test == 1:
            j = i + 1
            while j in range(i + 1, n) and test == 1:
                if m[j][pos] != 0:
                    per = []
                    per = m[i]
                    m[i] = m[j]
                    m[j] = per
                    test = 0
                j += 1
            i += 1
        return m

    def createElementaryMatrice(s, pos, q):
        m = []
        for i in range(n):
            r = []
            for j in range(n):
                if i == j:
                    r.append(1)
                else:
                    r.append(0)
            m.append(r)
        m[q][pos] = s
        return m

    def calculateDeterminant(m):
        if n == 2:
            s = m[0][0] * m[1][1] + m[0][1] * m[1][0]
            return s
        else:
            op = []
            for i in range(n - 1):
                for j in range(n):
                    if i == j:
                        num = m[i][j]
                        if num == 0:
                            m = permute(m, j, i)
                            op.append(-1)
                            if m[i][j] == 0:
                                return 0
                        for o in range(i + 1, n):
                            if m[o][j] != 0:
                                t1 = []
                                t1 = createElementaryMatrice(-(m[o][j] / m[i][j]), j, o)
                                m = multiply(t1, m)
            apex = []
            for d in range(n):
                for f in range(n):
                    if d == f:
                        apex.append(m[d][f])
            z = 1
            for a in range(len(apex)):
                z = z * apex[a]
            if len(op) > 0:
                for p in range(len(op)):
                    z = z * op[p]
            return z
    result = calculateDeterminant(matrix)
    return smp.latex(result)

def calculate_rank(args : str) -> str :
    args_dict = json.loads(args)
    mat = args_dict["matrix"]
    rows = mat.split("&")

    matrix = []
    for r in rows:
        a = r.split(",")
        b = [float(aa) for aa in a]
        matrix.append(b)
    n = int(args_dict["order"])
    def multiply(a, b):
        res = []
        for element in range(n):
            li = []
            for elem in range(n):
                li.append(0)
            res.append(li)

        for element in range(n):
            for elem in range(n):
                for k in range(n):
                    res[element][elem] += a[element][k] * b[k][elem]
        return res

    def permute(m, pos, r):
        test = 1
        i = r
        while i < n - 1 and test == 1:
            j = i + 1
            while j in range(i + 1, n) and test == 1:
                if m[j][pos] != 0:
                    per = []
                    per = m[i]
                    m[i] = m[j]
                    m[j] = per
                    test = 0
                j += 1
            i += 1
        return m

    def createElementaryMatrice(s, pos, q):
        m = []
        for i in range(n):
            r = []
            for j in range(n):
                if i == j:
                    r.append(1)
                else:
                    r.append(0)
            m.append(r)
        m[q][pos] = s
        return m

    def calcMIN(u, v):
        if u <= v:
            return u
        else:
            return v

    def calculateRank(ma, n, p):
        for i in range(n - 1):
            for j in range(p):
                if i == j:
                    num = ma[i][j]
                    if num == 0:
                        ma = permute(ma, j, i)
                        if ma[i][j] == 0:
                            return n - 1
                        for o in range(i + 1, n):
                            if ma[o][j] != 0:
                                t1 = createElementaryMatrice(-(ma[o][j] / ma[i][j]), j, o)
                                ma = multiply(t1, ma)
        nb = 0
        c = []
        for dg in range(1):
            for b in range(p):
                c.append(0)
        for y in range(n):
            if c == ma[y]:
                nb += 1
        ser = calcMIN(n, p)
        return ser - nb
    result = calculateRank(matrix,n,n)
    return smp.latex(result)


#print(inverse_matrix('{"matrix" : "1,0,0&0,1,0&1,0,1","order" : 3}'))
#print(calculate_determinant('{"matrix" : "1,0,0&0,1,0&1,0,1","order" : 3}'))
#print(calculate_rank('{"matrix" : "1,0,0&0,1,0&1,0,1","order" : 3}'))