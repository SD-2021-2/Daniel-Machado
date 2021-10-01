import Pyro4

print("Digite o Nome do Aluno: ")
nome = input()

resposta = Pyro4.Proxy("PYRONAME:ex3")   

print(resposta.aprovado(nome))