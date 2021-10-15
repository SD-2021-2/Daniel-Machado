import zmq

context = zmq.Context()
subscriber = context.socket(zmq.SUB)
subscriber.connect("tcp://127.0.0.1:4000")

sub = "Credito"
subscriber.setsockopt(zmq.SUBSCRIBE, sub.encode())

while True:

    [c, s] = subscriber.recv_multipart()

    saldo = float(s.decode())

    if saldo <= 200:
        resposta = "Saldo Medio: " +saldo+ "\nNenhum Credito"
    elif saldo <= 400:
        resposta = "Saldo Medio: " +str(saldo)+ "\nValor do Credito: " + str(saldo*0.2)
    elif saldo <= 600:
        resposta = "Saldo Medio: " +str(saldo)+ "\nValor do Credito: " + str(saldo*0.3)
    else:
        resposta = "Saldo Medio: " +str(saldo)+ "\nValor do Credito: " + str(saldo*0.4)

    print(resposta)

subscriber.close()
context.term()