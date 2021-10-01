import Pyro4

@Pyro4.expose
class Tabela(object):
        def pegarNota1(self,nome):
                return nota1[nome]

        def pegarNota2(self,nome):
                return nota2[nome]

        def pegarNota3(self,nome):
                return nota3[nome]

nota1 = {"Sergio": 6.0,
        "Daniel": 7.0,
        "Amanda": 6.5,
        "Jessica": 8.0,
        "Marcelo": 10.0,
        "José": 5.0,
        "Ana": 2.0,
        "Alex": 3.5,
        "Marcos":  4.0,
        "Catarina": 5.5}

nota2 = {"Sergio": 10.0,
        "Daniel": 9.0,
        "Amanda": 6.5,
        "Jessica": 8.5,
        "Marcelo": 8.0,
        "José": 3.0,
        "Ana": 1.0,
        "Alex": 4.5,
        "Marcos":  2.0,
        "Catarina": 3.5}

nota3 = {"Sergio": 7.0,
        "Daniel": 6.0,
        "Amanda": 5.0,
        "Jessica": 4.0,
        "Marcelo": 8.0,
        "José": 4.0,
        "Ana": 3.0,
        "Alex": 1.5,
        "Marcos":  1.0,
        "Catarina": 2.5}

daemon = Pyro4.Daemon(host="172.31.84.36", port=4002)               
uri = daemon.register(Tabela)
print(uri)
servidorNomes = Pyro4.locateNS(host="172.31.84.36",port=4000)
servidorNomes.register("tab", uri)
print(servidorNomes)

print("Database esperando conexão...")
daemon.requestLoop() 

