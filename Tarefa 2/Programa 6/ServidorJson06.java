import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONException;
import org.json.JSONObject;

public class ServidorJson06 {
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
		funcionario.nivel = json_obj.getString("nivel");
		funcionario.sal = json_obj.getDouble("salario");
		funcionario.dependentes = json_obj.getInt("dependentes");
		
		json_obj.put("salario liquido", funcionario.salLiquido());

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
	double sal;
	String nivel;
	int dependentes;
	
	String salLiquido(){
		String resposta = "Erro.";
		switch(nivel){
			case "A":
				if(dependentes > 0)
					resposta = ("Nome: " +nome+ "\nNivel: " +nivel+ "\nSalario Liquido: " +sal*0.92+ " reais");
				else
					resposta = ("Nome: " +nome+ "\nNivel: " +nivel+ "\nSalario Liquido: " +sal*0.97+ " reais");
				break;
				
			case "B":
				if(dependentes > 0)
					resposta = ("Nome: " +nome+ "\nNivel: " +nivel+ "\nSalario Liquido: " +sal*0.9+ " reais");
				else
					resposta = ("Nome: " +nome+ "\nNivel: " +nivel+ "\nSalario Liquido: " +sal*0.95+ " reais");
				break;
				
			case "C":
				if(dependentes > 0)
					resposta = ("Nome: " +nome+ "\nNivel: " +nivel+ "\nSalario Liquido: " +sal*0.85+ " reais");
				else
					resposta = ("Nome: " +nome+ "\nNivel: " +nivel+ "\nSalario Liquido: " +sal*0.92+ " reais");
				break;
				
			case "D":
				if(dependentes > 0)
					resposta = ("Nome: " +nome+ "\nNivel: " +nivel+ "\nSalario Liquido: " +sal*0.83+ " reais");
				else
					resposta = ("Nome: " +nome+ "\nNivel: " +nivel+ "\nSalario Liquido: " +sal*0.9+ " reais");
				break;
		}
		return resposta;
	}
}