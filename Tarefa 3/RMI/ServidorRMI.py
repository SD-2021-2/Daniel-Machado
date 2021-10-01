import Pyro4

@Pyro4.expose
class Exercicio8(object):
    def valCredito(self, numero):
        if (numero >= 0 and numero <= 200):
            return 0

        if (numero >= 201 and numero <= 400):
           return  numero*0.2

        if (numero >= 401 and numero <= 600):
           return numero*0.3

        if (numero >= 601):
           return numero*0.4

daemon = Pyro4.Daemon(host="172.31.23.92", port=4000)               
uri = daemon.register(Exercicio8)
print(uri)
servidorNomes = Pyro4.locateNS(host="172.31.84.36",port=4000)
servidorNomes.register("ex8", uri)
print(servidorNomes)

print("Ready.")
daemon.requestLoop()       