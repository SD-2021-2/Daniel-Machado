import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class Database01 {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(1234);
		System.out.println("Servidor esperando por conexoes...");
		Socket socket;

        while(true){
			socket = null;
			socket = ss.accept(); 
			System.out.println("Conexao de " + socket + "!");
			
			new Tabela(socket).start();
		}	
    }
}

class Tabela extends Thread {

	Socket socketThread;
  
	Tabela (Socket socket) throws IOException {
		socketThread = socket;
	}

    public void run() {
		try {
	
            InputStream inputStream = socketThread.getInputStream();
            BufferedReader mensagem = new BufferedReader(new InputStreamReader (inputStream));
    
            String key = mensagem.readLine();
            
            Hashtable<String, String> cargo = new Hashtable<String, String>();
            Hashtable<String, Double> salario = new Hashtable<String, Double>();
            
            cargo.put("Sergio", "operador");
            cargo.put("Daniel", "programador");
            cargo.put("Amanda", "operador");
			cargo.put("Jessica", "programador");
			cargo.put("Marcelo", "operador");
			cargo.put("José", "programador");
			cargo.put("Ana", "programador");
			cargo.put("Alex", "operador");
    
    
            salario.put("Sergio", 4000.0);
            salario.put("Daniel", 1000.0);
            salario.put("Amanda", 2000.0);
			salario.put("Jessica", 2000.0);
			salario.put("Marcelo", 1500.0);
			salario.put("José", 3000.0);
			salario.put("Ana", 3500.0);
			salario.put("Alex", 800.0);
            
            PrintStream resposta = new PrintStream(socketThread.getOutputStream());
            resposta.println(cargo.get(key) + "\n" + salario.get(key));

			System.out.println(socketThread +" Finalizado!");
			socketThread.close();	

		}catch (IOException e) {
			System.out.println("Erro na troca de mensagens!");
		}
	}  
}