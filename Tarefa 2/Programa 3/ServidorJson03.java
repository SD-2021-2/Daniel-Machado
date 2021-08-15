import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONException;
import org.json.JSONObject;

public class ServidorJson03 {
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

		Notas notas = new Notas();

		notas.n1 = json_obj.getDouble("n1");
		notas.n2 = json_obj.getDouble("n2");
		notas.n3 = json_obj.getDouble("n3");
		
		json_obj.put("resultado", notas.aprovado());

		PrintStream resposta = new PrintStream(socketThread.getOutputStream());
		resposta.println(json_obj.toString());

		socketThread.close();
		System.out.println(socketThread +" Finalizado!");
				
		}catch (IOException e) {
			System.out.println("Erro na troca de mensagens!");
		}
	}  
}

class Notas {
	double n1, n2,n3;
	
	String aprovado(){
		String resposta;
		double m = (n1+n2)/2;
		if(m >= 7.0 || (m > 3.0 && (m+n3)/2 >= 5.0))
			resposta = "Parabens! Voce foi Aprovado!";
		else
			resposta = "Reprovado... mas sempre ha uma proxima vez!";
		return resposta;
	}
}