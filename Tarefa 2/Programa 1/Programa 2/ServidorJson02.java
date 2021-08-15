import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONException;
import org.json.JSONObject;

public class ServidorJson02 {
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

		pessoa.nome = json_obj.getString("nome");
		pessoa.sexo = json_obj.getString("sexo");
		pessoa.idade = json_obj.getInt("idade");
		
		json_obj.put("maioridade", pessoa.maioridade());

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
	String nome;
	String sexo;
	int idade;
	
	String maioridade(){
		String resposta;
		if((sexo.equals("Masculino") && idade >= 18) || (sexo.equals("Feminino") && idade >= 21))
			resposta = "Atingiu a Maioridade!";
		else
			resposta = "Nao atingiu a Maioridade!";
		return resposta;
	}
}