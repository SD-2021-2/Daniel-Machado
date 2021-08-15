import socket
import json

HOST = 'LocalHost'
PORT = 7777

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))

print("Digite o Nome: ")
nome = input()

print("Digite o Nivel (A, B, C ou D): ")
nivel = input()

print("Digite o Salario Bruto: ")
sal = input()

print("Digite o Numero de Dependentes: ")
dependentes = input()

json_objeto = {
    "nome": nome,
    "nivel": nivel,
    "salario": sal,
    "dependentes": dependentes
}

mensagem = json.dumps(json_objeto)

s.sendall((mensagem+"\n").encode())

json_objeto = s.recv(1024)
resposta = json.loads(json_objeto.decode())

print(resposta["salario liquido"])