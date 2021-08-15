import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONException;
import org.json.JSONObject;

public class ServidorJson08 {
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

		Cliente cliente = new Cliente();

		cliente.saldo = json_obj.getDouble("saldo");
		
		json_obj.put("credito", cliente.valorCredito());

		PrintStream resposta = new PrintStream(socketThread.getOutputStream());
		resposta.println(json_obj.toString());

		socketThread.close();
		System.out.println(socketThread +" Finalizado!");
				
		}catch (IOException e) {
			System.out.println("Erro na troca de mensagens!");
		}
	}  
}

class Cliente {
	double saldo;
	
	String valorCredito(){
		String resposta;
		if(saldo <= 200)
			resposta = "Saldo Medio: " +saldo+ "\nNenhum Credito";
		else if(saldo <= 400)
			resposta = "Saldo Medio: " +saldo+ "\nValor do Credito: " +saldo*0.2;
		else if(saldo <= 600)
			resposta = "Saldo Medio: " +saldo+ "\nValor do Credito: " +saldo*0.3;
		else
			resposta = "Saldo Medio: " +saldo+ "\nValor do Credito: " +saldo*0.4;
		return resposta;
	}
}