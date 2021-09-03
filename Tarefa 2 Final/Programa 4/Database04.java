import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class Database04 {
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
            
            Hashtable<String, Double> altura = new Hashtable<String, Double>();
            Hashtable<String, String> sexo = new Hashtable<String, String>();
            
            altura.put("Sergio", 1.82);
            altura.put("Daniel", 1.70);
            altura.put("Amanda", 1.86);
			altura.put("Jessica", 1.68);
			altura.put("Marcelo", 1.75);
			altura.put("José", 2.02);
			altura.put("Ana", 1.50);
			altura.put("Alex", 1.70);

			sexo.put("Sergio", "Masculino");
            sexo.put("Daniel", "Masculino");
            sexo.put("Amanda", "Feminino");
			sexo.put("Jessica", "Feminino");
			sexo.put("Marcelo", "Masculino");
			sexo.put("José", "Masculino");
			sexo.put("Ana", "Feminino");
			sexo.put("Alex", "Feminino");
    
            PrintStream resposta = new PrintStream(socketThread.getOutputStream());
            resposta.println(altura.get(key) + "\n" + sexo.get(key));

			System.out.println(socketThread +" Finalizado!");
			socketThread.close();	

		}catch (IOException e) {
			System.out.println("Erro na troca de mensagens!");
		}
	}
}