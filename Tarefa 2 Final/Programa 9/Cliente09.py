import socket

HOST = 'LocalHost'
PORT = 7777

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))

print("Digite o Numero da Carta: ")
numero = input()

print("Digite o Naipe(1 = ouros, 2 = paus, 3 = copas e 4 = espadas): ")
naipe = input()

s.sendall((numero+"\n"+naipe+"\n").encode())

resposta = s.recv(1024)

print(resposta.decode())