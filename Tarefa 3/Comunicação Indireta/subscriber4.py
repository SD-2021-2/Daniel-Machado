import zmq

context = zmq.Context()
subscriber = context.socket(zmq.SUB)
subscriber.connect("tcp://127.0.0.1:4000")

sub = "Peso"
subscriber.setsockopt(zmq.SUBSCRIBE, sub.encode())

while True:

    [p, alt, s] = subscriber.recv_multipart()

    altura = float(alt.decode())
    sexo = s.decode()

    if sexo == "Masculino":
        peso = (72.7*altura)-58
    elif sexo == "Feminino":
        peso = (62.1*altura)-44.7

    print("Peso ideal:  %.2f" % peso)

subscriber.close()
context.term()