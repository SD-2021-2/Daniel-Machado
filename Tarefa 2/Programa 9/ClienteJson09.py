import socket
import json

HOST = 'LocalHost'
PORT = 7777

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))

print("Digite o Numero da Carta: ")
numero = input()

print("Digite o Naipe (1 = Ouros, 2 = Paus, 3 = Copas e 4 = Espadas): ")
naipe = input()

json_objeto = {
    "numero": numero,
    "naipe": naipe
}

mensagem = json.dumps(json_objeto)

s.sendall((mensagem+"\n").encode())

json_objeto = s.recv(1024)
resposta = json.loads(json_objeto.decode())

print(resposta["extenso"])