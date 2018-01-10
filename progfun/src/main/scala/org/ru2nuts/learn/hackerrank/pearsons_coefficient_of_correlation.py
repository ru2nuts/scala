# https://www.hackerrank.com/challenges/correlation-and-regression-lines-6/problem
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
    ll.append(list(map((lambda s: float(s)), line.split())))

xs = ll[0]
ys = ll[1]

cc = compute_correlation_coefficient(ll[0], ll[1])

print("{0:.3f}".format(cc))

# beta = (n * sum_XY - sum_X * sum_Y) / (n * sum_XX - sum_X ** 2)
# alpha = (1.0 / n) * sum_Y - beta * (1.0 / n) * sum_Y
