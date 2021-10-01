from xmlrpc.server import SimpleXMLRPCServer

def carta(numero, tipo):

    if numero == 1:
        valor = "As"
    if numero == 2:
        valor = "Dois"
    if numero == 3:
        valor = "Tres"
    if numero == 4:
        valor = "Quatro"
    if numero == 5:
        valor = "Cinco"
    if numero == 6:
        valor = "Seis"
    if numero == 7:
        valor = "Sete"
    if numero == 8:
        valor = "Oito"
    if numero == 9:
        valor = "Nove"
    if numero == 10:
        valor = "Dez"
    if numero == 11:
        valor = "Valete"
    if numero == 12:
        valor = "Dama"
    if numero == 13:
        valor = "Rei"

    if tipo == 1:
        naipe = "Ouros"
    if tipo == 2:
        naipe = "Paus"
    if tipo == 3:
        naipe = "Copas"
    if tipo == 4:
        naipe = "Espadas"

    print(valor + " de " + naipe)

    return (valor + " de " + naipe)

server = SimpleXMLRPCServer(("127.0.0.1", 4000))
print("Listening on port 4000...")
server.register_function(carta, 'carta')

server.serve_forever()