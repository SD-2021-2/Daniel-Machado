import xmlrpc.client

proxy = xmlrpc.client.ServerProxy("http://127.0.0.1:4000/")
    
print("Digite o Numero da Carta: ")
valor = int(input())

print("Digite o Naipe(1 = ouros, 2 = paus, 3 = copas e 4 = espadas): ")
naipe = int(input())

resposta = proxy.carta(valor,naipe)

print(resposta)