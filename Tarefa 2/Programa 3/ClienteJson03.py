import socket
import json

HOST = 'LocalHost'
PORT = 7777

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))

print("Digite a Primeira Nota: ")
n1 = input()

print("Digite a Segunda Nota: ")
n2 = input()

print("Digite a Terceira Nota: ")
n3 = input()

json_objeto = {
    "n1": n1,
    "n2": n2,
    "n3": n3,
}

mensagem = json.dumps(json_objeto)

s.sendall((mensagem+"\n").encode())

json_objeto = s.recv(1024)
resposta = json.loads(json_objeto.decode())

print(resposta["resultado"])