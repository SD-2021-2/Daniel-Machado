import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONException;
import org.json.JSONObject;

public class ServidorJson04 {
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

		Pessoa pessoa = new Pessoa();

		pessoa.altura = json_obj.getDouble("altura");
		pessoa.sexo = json_obj.getString("sexo");
		
		json_obj.put("peso", pessoa.pesoIdeal());

		PrintStream resposta = new PrintStream(socketThread.getOutputStream());
		resposta.println(json_obj.toString());

		socketThread.close();
		System.out.println(socketThread +" Finalizado!");
		
		}catch (IOException e) {
			System.out.println("Erro na troca de mensagens!");
		}
	}  
}

class Pessoa {
	double altura;
	String sexo;
	
	String pesoIdeal(){
		String resposta = "Sexo Invalido.";
		double peso;
		if(sexo.equals("Masculino")){
			peso = (72.7*altura)-58;
			resposta = String.format("Seu peso ideal: %.2f", peso);
		}else if(sexo.equals("Feminino")){
			peso = (62.1*altura)-44.7;
			resposta = String.format("Seu peso ideal: %.2f", peso);
		}
		return resposta;
	}
}