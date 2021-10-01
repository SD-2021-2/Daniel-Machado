import java.rmi.Remote; 
import java.rmi.RemoteException; 

public interface Categoria extends Remote { 
    public String categoria(int idade) throws RemoteException;
}