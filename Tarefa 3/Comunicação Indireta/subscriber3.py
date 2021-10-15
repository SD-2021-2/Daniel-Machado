import zmq

context = zmq.Context()
subscriber = context.socket(zmq.SUB)
subscriber.connect("tcp://127.0.0.1:4000")

sub = "Aluno"
subscriber.setsockopt(zmq.SUBSCRIBE, sub.encode())

while True:

    [a, n1, n2, n3] = subscriber.recv_multipart()

    nota1 = float(n1.decode())
    nota2 = float(n2.decode())
    nota3 = float(n3.decode())
    media = (nota1+nota2)/2

    if media >=7.0 or (media > 3.0 and (media+nota3)/2 >= 5.0):
        resposta = "Parabens! Voce foi Aprovado!"
    else:
        resposta = "Reprovado... mas sempre ha uma proxima vez!"

    print(resposta)

subscriber.close()
context.term()