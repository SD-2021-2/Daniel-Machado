import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class Database07 {
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
            
			Hashtable<String, Integer> idade = new Hashtable<String, Integer>();
            Hashtable<String, Integer> tempo = new Hashtable<String, Integer>();
            
            idade.put("Sergio", 60);
            idade.put("Daniel", 22);
            idade.put("Amanda", 15);
			idade.put("Jessica", 19);
			idade.put("Marcelo", 16);
			idade.put("José", 42);
			idade.put("Ana", 62);
			idade.put("Alex", 17);

			tempo.put("Sergio", 32);
            tempo.put("Daniel", 9);
            tempo.put("Amanda", 14);
			tempo.put("Jessica", 33);
			tempo.put("Marcelo", 24);
			tempo.put("José", 22);
			tempo.put("Ana", 40);
			tempo.put("Alex", 17);
    
            PrintStream resposta = new PrintStream(socketThread.getOutputStream());
            resposta.println(idade.get(key) + "\n" + tempo.get(key));

			System.out.println(socketThread +" Finalizado!");
			socketThread.close();	

		}catch (IOException e) {
			System.out.println("Erro na troca de mensagens!");
		}
	}
}