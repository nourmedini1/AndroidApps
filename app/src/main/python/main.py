ch = "1,0,0&0,1,0&0,0,1"

rows = ch.split("&")
print(rows)
mat = []
for r in rows :
    a = r.split(",")
    b = [int(aa) for aa in a]
    mat.append(b)

print(mat)