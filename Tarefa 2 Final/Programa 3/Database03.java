import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class Database03 {
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
            
            Hashtable<String, Double> nota1 = new Hashtable<String, Double>();
            Hashtable<String, Double> nota2 = new Hashtable<String, Double>();
			Hashtable<String, Double> nota3 = new Hashtable<String, Double>();
            
            nota1.put("Sergio", 6.0);
            nota1.put("Daniel", 7.0);
            nota1.put("Amanda", 6.5);
			nota1.put("Jessica", 8.0);
			nota1.put("Marcelo", 10.0);
			nota1.put("José", 5.0);
			nota1.put("Ana", 2.0);
			nota1.put("Alex", 3.5);
    
            nota2.put("Sergio", 4.0);
            nota2.put("Daniel", 9.0);
            nota2.put("Amanda", 6.5);
			nota2.put("Jessica", 8.5);
			nota2.put("Marcelo", 9.0);
			nota2.put("José", 3.0);
			nota2.put("Ana", 1.0);
			nota2.put("Alex", 4.0);

			nota3.put("Sergio", 7.0);
            nota3.put("Daniel", 6.0);
            nota3.put("Amanda", 5.0);
			nota3.put("Jessica", 4.0);
			nota3.put("Marcelo", 8.0);
			nota3.put("José", 9.0);
			nota3.put("Ana", 2.0);
			nota3.put("Alex", 1.0);
            
            PrintStream resposta = new PrintStream(socketThread.getOutputStream());
            resposta.println(nota1.get(key) + "\n" + nota2.get(key) + "\n" + nota3.get(key));

			System.out.println(socketThread +" Finalizado!");
			socketThread.close();	

		}catch (IOException e) {
			System.out.println("Erro na troca de mensagens!");
		}
	}
}