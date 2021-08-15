import socket
import json

HOST = 'LocalHost'
PORT = 7777

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))

print("Digite o Nome do Funcionario: ")
nome = input()

print("Digite o Cargo do Funcionario: ")
cargo = input()

print("Digite o Salario do Funcionario: ")
salario = input()

json_objeto = {
    "nome": nome,
    "cargo": cargo,
    "salario": salario,
}

mensagem = json.dumps(json_objeto)

s.sendall((mensagem+"\n").encode())

json_objeto = s.recv(1024)
resposta = json.loads(json_objeto.decode())

print(resposta["salReajuste"])