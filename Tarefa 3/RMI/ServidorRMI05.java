import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServidorRMI05 extends UnicastRemoteObject
    implements Categoria {

    private String resposta;

    public ServidorRMI05() throws RemoteException {
        super();
    }

    public String categoria(int idade){
		if(idade >= 18)
			resposta = "Categoria do Nadador(a): Adulto";
		else if(idade >= 14)
			resposta = "Categoria do Nadador(a): Juvenil B";
		else if(idade >= 11)
			resposta = "Categoria do Nadador(a): Juvenil A";
		else if(idade >= 8)
			resposta = "Categoria do Nadador(a): Infantil B";
		else if(idade >= 5)
			resposta = "Categoria do Nadador(a): Infantil A";
		else
			resposta = "Idade de Nadador(a) Invalida!";
		return resposta;
	}

    public static void main(String args[]) { 

    try { 
			Registry registry = LocateRegistry.createRegistry(4000);
        	Categoria obj = new ServidorRMI05(); 
	    	Naming.rebind("rmi://localhost:4000/CategoriaServer", obj); 
	    	System.out.println("CategoriaServer bound in registry"); 
        } catch (Exception e) { 
	    	System.out.println("ServidorRMI05 err: " + e.getMessage()); 
	    	e.printStackTrace(); 
        } 
    } 
}
