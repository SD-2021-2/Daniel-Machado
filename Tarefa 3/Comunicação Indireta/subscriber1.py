import zmq

context = zmq.Context()
subscriber = context.socket(zmq.SUB)
subscriber.connect("tcp://127.0.0.1:4000")

sub = "Funcionario"
subscriber.setsockopt(zmq.SUBSCRIBE, sub.encode())

[f, n, c, s] = subscriber.recv_multipart()

nome = n.decode()
salario = float(s.decode())
cargo = c.decode()

if cargo == "Operador":
    salario = salario*1.2
elif cargo == "Programador":
    salario = salario*1.18

print("Salario de " +nome+ " Reajustado para: " + str(salario))

subscriber.close()
context.term()