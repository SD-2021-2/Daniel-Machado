import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONException;
import org.json.JSONObject;

public class ServidorJson01 {
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

		Funcionario funcionario = new Funcionario();

		funcionario.nome = json_obj.getString("nome");
		funcionario.cargo = json_obj.getString("cargo");
		funcionario.sal = json_obj.getDouble("salario");
		
		json_obj.put("salReajuste", funcionario.salReajuste());

		PrintStream resposta = new PrintStream(socketThread.getOutputStream());
		resposta.println(json_obj.toString());

		socketThread.close();		
		System.out.println(socketThread +" Finalizado!");
		
		}catch (IOException e) {
			System.out.println("Erro na troca de mensagens!");
		}
	}  
}

class Funcionario {
	String nome;
	String cargo;
	double sal;
	
	String salReajuste(){
		String resposta = "Funcionario " +nome;
		
		if(cargo.equals("operador"))
			resposta = resposta.concat(" tera seu salario reajustado para " +sal*1.2);
		else if(cargo.equals("programador"))
			resposta = resposta.concat(" tera seu salario reajustado para " +sal*1.18);
		else
			resposta = resposta.concat(" nao recebera reajuste");

		return resposta;
	}
}