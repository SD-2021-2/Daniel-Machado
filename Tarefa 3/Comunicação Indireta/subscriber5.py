import zmq

context = zmq.Context()
subscriber = context.socket(zmq.SUB)
subscriber.connect("tcp://127.0.0.1:4000")

sub = "Esporte"
subscriber.setsockopt(zmq.SUBSCRIBE, sub.encode())

while True:

    [e, id] = subscriber.recv_multipart()

    idade = int(id.decode())

    if idade >= 18:
        resposta = "Categoria do Nadador(a): Adulto"
    elif idade >= 14:
        resposta = "Categoria do Nadador(a): Juvenil B"
    elif idade >= 11:
        resposta = "Categoria do Nadador(a): Juvenil A"
    elif idade >= 8:
        resposta = "Categoria do Nadador(a): Infantil B"
    elif idade >= 5:
        resposta = "Categoria do Nadador(a): Infantil A"
    else:
        resposta = "Idade de Nadador(a) Invalida!"

    print(resposta)

subscriber.close()
context.term()