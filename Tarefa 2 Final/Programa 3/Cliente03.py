import socket

HOST = 'LocalHost'
PORT = 7777

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))

print("Digite o Nome do Aluno: ")
nome = input()

s.sendall((nome+"\n").encode())

resposta = s.recv(1024)

print(resposta.decode())