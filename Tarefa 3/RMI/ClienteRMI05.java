import java.rmi.Naming; 
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClienteRMI05 {

    public static void main (String a[]) {

        Categoria obj = null; 

        try { 
            System.out.println("Iniciando procura...");
            obj = (Categoria)Naming.lookup("rmi://localhost:4000/CategoriaServer"); 
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite a Idade: ");
            int idade = scanner.nextInt();
            String resposta = obj.categoria(idade);
            System.out.println(resposta);
            scanner.close();
            
        } catch (Exception e) { 
            System.out.println("ClienteRMI05 exception: " + e.getMessage()); 
            e.printStackTrace(); 
        } 
    } 
}