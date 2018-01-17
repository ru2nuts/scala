def factorial(n):
    if n == 0:
        return 1
    return reduce((lambda x, y: x * y), range(1, n + 1))


def fibonacci(n):
    if n == 0:
        return 0
    if n == 1:
        return 1
    else:
        return fibonacci(n - 1) + fibonacci(n - 2)


def gcd(a, b):
    if (a >= b):
        return gcdR(a, b)
    else:
        gcdR(b, a)


def gcdR(a, b):
    if (b == 0):
        return a
    else:
        gcdR(b, a % b)


data = int(raw_input())
print(factorial(data))
print(fibonacci(data))

a = int(raw_input())
b = int(raw_input())
g = gcd(a, b)
print(g)
