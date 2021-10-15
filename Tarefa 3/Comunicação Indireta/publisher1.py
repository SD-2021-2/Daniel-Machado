import time
import zmq

context = zmq.Context()
publisher = context.socket(zmq.PUB)
publisher.bind("tcp://127.0.0.1:4000")
print("Publisher 1 Ligado!")

while True:
    publisher.send_multipart(["Funcionario".encode(), "Sergio".encode(), "Operador".encode(), str(3000).encode()])
    publisher.send_multipart(["Maioridade".encode(), "Valeria".encode(), "Feminino".encode(), str(22).encode()])
    publisher.send_multipart(["Aluno".encode(), str(9.4).encode(), str(8.1).encode(), str(7.5).encode()])
    publisher.send_multipart(["Peso".encode(), str(1.65).encode(), "Feminino".encode()])
    publisher.send_multipart(["Esporte".encode(), str(14).encode()])
    publisher.send_multipart(["Aposentadoria".encode(), str(72).encode(), str(38).encode()])
    publisher.send_multipart(["Credito".encode(), str(450).encode()])

    time.sleep(1)

publisher.close()
context.term()