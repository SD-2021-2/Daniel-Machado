import socket
import json

HOST = 'LocalHost'
PORT = 7777

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))

print("Digite a Altura em Metros: ")
altura = input()

print("Digite o Sexo (Masculino ou Feminino): ")
sexo = input()

json_objeto = {
    "altura": altura,
    "sexo": sexo
}

mensagem = json.dumps(json_objeto)

s.sendall((mensagem+"\n").encode())

json_objeto = s.recv(1024)
resposta = json.loads(json_objeto.decode())

print(resposta["peso"])