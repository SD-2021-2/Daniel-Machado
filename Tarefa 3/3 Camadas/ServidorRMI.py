import Pyro4

@Pyro4.expose
class Exercicio3(object):
   def aprovado(self, nome):
      tabela = Pyro4.Proxy("PYRONAME:tab")
      n1 = tabela.pegarNota1(nome)
      n2 = tabela.pegarNota2(nome)
      n3 = tabela.pegarNota3(nome)
      m = (n1+n2)/2
      if(m >= 7.0 or (m > 3.0 and (m+n3)/2 >= 5.0)):
         return "Parabens! Voce foi Aprovado!"
      else:
         return "Reprovado... mas sempre ha uma proxima vez!"

daemon = Pyro4.Daemon(host="172.31.23.92", port=4001)               
uri = daemon.register(Exercicio3)
print(uri)
servidorNomes = Pyro4.locateNS(host="172.31.23.92",port=4000)
servidorNomes.register("ex3", uri)
print(servidorNomes)

print("Esperando Conexão...")
daemon.requestLoop()       