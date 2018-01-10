# https://www.hackerrank.com/challenges/computing-the-correlation/problem
import fileinput


def compute_correlation_coefficient(xs, ys):
    n = float(len(xs))
    sum_X = sum(xs)
    sum_Y = sum(ys)
    sum_XX = sum(list(map((lambda x: x ** 2), xs)))
    sum_YY = sum(list(map((lambda y: y ** 2), ys)))
    sum_XY = sum(list(map((lambda (x, y): x * y), zip(xs, ys))))
    numer = n * sum_XY - sum_X * sum_Y
    denom = ((n * sum_XX - sum_X ** 2) * (n * sum_YY - sum_Y ** 2)) ** (0.5)
    return numer / denom


ll = []
for line in fileinput.input():
    ll.append(line)

n = int(ll[0])

m = []
p = []
c = []

for i in range(1, n + 1):
    ri = list(map((lambda s: float(s)), ll[i].split()))
    (mi, pi, ci) = (ri[0], ri[1], ri[2])
    m.append(mi)
    p.append(pi)
    c.append(ci)

c_mp = compute_correlation_coefficient(m, p)
c_pc = compute_correlation_coefficient(p, c)
c_cm = compute_correlation_coefficient(c, m)

print("{0:.2f}".format(c_mp))
print("{0:.2f}".format(c_pc))
print("{0:.2f}".format(c_cm))
