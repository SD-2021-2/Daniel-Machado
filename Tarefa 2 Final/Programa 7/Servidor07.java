import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor07 {
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

		ConexaoDataBase database = new ConexaoDataBase();
		database.nome = request.readLine();
		database.dados();
		
		Funcionario funcionario = new Funcionario();
		funcionario.idade = database.idade;
		funcionario.tempo = database.tempo;

		PrintStream resposta = new PrintStream(socketThread.getOutputStream());
		resposta.print(funcionario.aposentado());

		socketThread.close();
		System.out.println(socketThread +" Finalizado!");
		
		}catch (IOException e) {
			System.out.println("Erro na troca de mensagens!");
		}
	}  
}

class Funcionario {
	int idade;
	int tempo;
	
	String aposentado(){
		String resposta;
		if(idade >= 65 && tempo >= 30)
			resposta = "Voce ja pode se Aposentar!";
		else
			resposta = "Ainda nao se pode Aposentar";
		return resposta;
	}
}

class ConexaoDataBase{
	String nome;
	int idade;
	int tempo;

	void dados() throws IOException{
		Socket socketDatabase = new Socket("localhost", 1234);
		System.out.println("Conectado com Database Realizada!");

		PrintStream solicitation = new PrintStream(socketDatabase.getOutputStream());
		solicitation.println(nome);

		InputStream inputStreamDatabase = socketDatabase.getInputStream();
		BufferedReader response = new BufferedReader(new InputStreamReader (inputStreamDatabase));

		idade = Integer.parseInt(response.readLine());
        tempo = Integer.parseInt(response.readLine());

		System.out.println("Conectado com Database Finalizada!");
		socketDatabase.close();
	}
}