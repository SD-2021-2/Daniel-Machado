import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class Database06 {
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
            
			Hashtable<String, Double> salario = new Hashtable<String, Double>();
			Hashtable<String, String> nivel = new Hashtable<String, String>();
            Hashtable<String, Integer> dependentes = new Hashtable<String, Integer>();
            
            salario.put("Sergio", 4000.0);
            salario.put("Daniel", 1000.0);
            salario.put("Amanda", 2000.0);
			salario.put("Jessica", 2000.0);
			salario.put("Marcelo", 1500.0);
			salario.put("José", 3000.0);
			salario.put("Ana", 3500.0);
			salario.put("Alex", 800.0);

			nivel.put("Sergio", "A");
            nivel.put("Daniel", "D");
            nivel.put("Amanda", "C");
			nivel.put("Jessica", "B");
			nivel.put("Marcelo", "D");
			nivel.put("José", "A");
			nivel.put("Ana", "B");
			nivel.put("Alex", "C");

			dependentes.put("Sergio", 2);
            dependentes.put("Daniel", 0);
            dependentes.put("Amanda", 0);
			dependentes.put("Jessica", 1);
			dependentes.put("Marcelo", 3);
			dependentes.put("José", 3);
			dependentes.put("Ana", 0);
			dependentes.put("Alex", 1);


    
            PrintStream resposta = new PrintStream(socketThread.getOutputStream());
            resposta.println(salario.get(key) + "\n" + nivel.get(key) + "\n" + dependentes.get(key));

			System.out.println(socketThread +" Finalizado!");
			socketThread.close();	

		}catch (IOException e) {
			System.out.println("Erro na troca de mensagens!");
		}
	}
}