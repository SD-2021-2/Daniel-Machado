import zmq

context = zmq.Context()
subscriber = context.socket(zmq.SUB)
subscriber.connect("tcp://127.0.0.1:4000")

sub = "Aposentadoria"
subscriber.setsockopt(zmq.SUBSCRIBE, sub.encode())

while True:

    [a, id, t] = subscriber.recv_multipart()

    idade = int(id.decode())
    tempo = int(t.decode())

    if idade >= 65 and tempo >= 30:
        resposta = "Voce ja pode se Aposentar!"
    else:
        resposta = "Ainda nao se pode Aposentar"

    print(resposta)

subscriber.close()
context.term()