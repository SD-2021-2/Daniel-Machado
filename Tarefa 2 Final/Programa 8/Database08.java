import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class Database08 {
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
            
			Hashtable<String, Double> saldo = new Hashtable<String, Double>();
            
            saldo.put("Sergio", 700.2);
            saldo.put("Daniel", 250.0);
            saldo.put("Amanda", 400.0);
			saldo.put("Jessica", 555.0);
			saldo.put("Marcelo", 198.0);
			saldo.put("José", 602.0);
			saldo.put("Ana", 221.0);
			saldo.put("Alex", 363.0);

            PrintStream resposta = new PrintStream(socketThread.getOutputStream());
            resposta.println(saldo.get(key));

			System.out.println(socketThread +" Finalizado!");
			socketThread.close();	

		}catch (IOException e) {
			System.out.println("Erro na troca de mensagens!");
		}
	}
}