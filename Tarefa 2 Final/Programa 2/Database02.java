import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class Database02 {
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
            
            Hashtable<String, String> sexo = new Hashtable<String, String>();
            Hashtable<String, Integer> idade = new Hashtable<String, Integer>();
            
            sexo.put("Sergio", "Masculino");
            sexo.put("Daniel", "Masculino");
            sexo.put("Amanda", "Feminino");
			sexo.put("Jessica", "Feminino");
			sexo.put("Marcelo", "Masculino");
			sexo.put("José", "Masculino");
			sexo.put("Ana", "Feminino");
			sexo.put("Alex", "Feminino");
    
            idade.put("Sergio", 60);
            idade.put("Daniel", 22);
            idade.put("Amanda", 15);
			idade.put("Jessica", 19);
			idade.put("Marcelo", 16);
			idade.put("José", 42);
			idade.put("Ana", 62);
			idade.put("Alex", 17);
            
            PrintStream resposta = new PrintStream(socketThread.getOutputStream());
            resposta.println(sexo.get(key) + "\n" + idade.get(key));

			System.out.println(socketThread +" Finalizado!");
			socketThread.close();	

		}catch (IOException e) {
			System.out.println("Erro na troca de mensagens!");
		}
	}
}