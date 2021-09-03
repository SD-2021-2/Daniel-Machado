import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor02 {
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
  
	public void run() {
		try {
		InputStream inputStreamClient = socketThread.getInputStream();
        BufferedReader request = new BufferedReader(new InputStreamReader (inputStreamClient));

		Pessoa pessoa = new Pessoa();
		pessoa.nome = request.readLine();
		
		ConexaoDataBase database = new ConexaoDataBase();
		database.nome = pessoa.nome;
		database.dados();
		
		pessoa.sexo = database.sexo;
		pessoa.idade = database.idade;

		PrintStream resposta = new PrintStream(socketThread.getOutputStream());
		resposta.print(pessoa.maioridade());

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

class ConexaoDataBase{
	String nome;
	String sexo;
	int idade;

	void dados() throws IOException{
		Socket socketDatabase = new Socket("localhost", 1234);
		System.out.println("Conectado com Database Realizada!");

		PrintStream solicitation = new PrintStream(socketDatabase.getOutputStream());
		solicitation.println(nome);

		InputStream inputStreamDatabase = socketDatabase.getInputStream();
		BufferedReader response = new BufferedReader(new InputStreamReader (inputStreamDatabase));

		sexo = response.readLine();
        idade = Integer.parseInt(response.readLine());

		System.out.println("Conectado com Database Finalizada!");
		socketDatabase.close();
	}
}