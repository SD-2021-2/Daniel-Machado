import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class Database09 {
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
    
            int key1 = Integer.parseInt(mensagem.readLine());
			int key2 = Integer.parseInt(mensagem.readLine());
            
			Hashtable<Integer, String> numero = new Hashtable<Integer, String>();
			Hashtable<Integer, String> tipo = new Hashtable<Integer, String>();
            
            numero.put(1, "As");
            numero.put(2, "Dois");
            numero.put(3, "Tres");
			numero.put(4, "Quatro");
			numero.put(5, "Cinco");
			numero.put(6, "Seis");
			numero.put(7, "Sete");
			numero.put(8, "Oito");
			numero.put(9, "Nove");
			numero.put(10, "Dez");
			numero.put(11, "Valete");
			numero.put(12, "Dama");
			numero.put(13, "Rei");

			tipo.put(1, "Ouros");
            tipo.put(2, "Paus");
            tipo.put(3, "Copas");
			tipo.put(4, "Espadas");

            PrintStream resposta = new PrintStream(socketThread.getOutputStream());
            resposta.println(numero.get(key1) + "\n" + tipo.get(key2));

			System.out.println(socketThread +" Finalizado!");
			socketThread.close();	

		}catch (IOException e) {
			System.out.println("Erro na troca de mensagens!");
		}
	}
}