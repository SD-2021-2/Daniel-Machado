import Pyro4

print("Saldo Medio : ")
numero = int(input())

resposta = Pyro4.Proxy("PYRONAME:ex8")   


print("Valor de Credito : " , resposta.valCredito(numero))