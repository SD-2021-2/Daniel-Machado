import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor05 {
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
		
		Nadador nadador = new Nadador();
		nadador.idade = database.idade;

		PrintStream resposta = new PrintStream(socketThread.getOutputStream());
		resposta.print(nadador.categoria());

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

class ConexaoDataBase{
	String nome;
	int idade;

	void dados() throws IOException{
		Socket socketDatabase = new Socket("localhost", 1234);
		System.out.println("Conectado com Database Realizada!");

		PrintStream solicitation = new PrintStream(socketDatabase.getOutputStream());
		solicitation.println(nome);

		InputStream inputStreamDatabase = socketDatabase.getInputStream();
		BufferedReader response = new BufferedReader(new InputStreamReader (inputStreamDatabase));

        idade = Integer.parseInt(response.readLine());

		System.out.println("Conectado com Database Finalizada!");
		socketDatabase.close();
	}
}