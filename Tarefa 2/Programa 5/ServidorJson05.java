import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONException;
import org.json.JSONObject;

public class ServidorJson05 {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(7777);
		System.out.println("Servidor esperando por conexoes...");
		Socket socket;
		
		while(true){
			socket = null;
			socket = ss.accept(); 
			System.out.println("Conexao de " + socket + "!");
			
			new Conexao(socket).start();
		}		
    }
}

class Conexao extends Thread {

	Socket socketThread;
  
	Conexao (Socket socket) throws IOException {
		socketThread = socket;
	}
  
	public void run() throws JSONException {
		try {
		InputStream inputStream = socketThread.getInputStream();
        BufferedReader mensagem = new BufferedReader(new InputStreamReader (inputStream));
	
		JSONObject json_obj = new JSONObject(mensagem.readLine());

		Nadador nadador = new Nadador();

		nadador.idade = json_obj.getInt("idade");
		
		json_obj.put("categoria", nadador.categoria());

		PrintStream resposta = new PrintStream(socketThread.getOutputStream());
		resposta.println(json_obj.toString());

		socketThread.close();
		System.out.println(socketThread +" Finalizado!");
				
		}catch (IOException e) {
			System.out.println("Erro na troca de mensagens!");
		}
	}  
}

class Nadador {
	int idade;
	
	String categoria(){
		String resposta;
		if(idade >= 18)
			resposta = "Categoria do Nadador(a): Adulto";
		else if(idade >= 14)
			resposta = "Categoria do Nadador(a): Juvenil B";
		else if(idade >= 11)
			resposta = "Categoria do Nadador(a): Juvenil A";
		else if(idade >= 8)
			resposta = "Categoria do Nadador(a): Infantil B";
		else if(idade >= 5)
			resposta = "Categoria do Nadador(a): Infantil A";
		else
			resposta = "Idade de Nadador(a) Invalida!";
		return resposta;
	}
}