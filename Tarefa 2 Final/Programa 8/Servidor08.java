import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor08 {
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
		
		Cliente cliente = new Cliente();
		cliente.saldo = database.saldo;

		PrintStream resposta = new PrintStream(socketThread.getOutputStream());
		resposta.print(cliente.valorCredito());

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

class ConexaoDataBase{
	String nome;
	double saldo;

	void dados() throws IOException{
		Socket socketDatabase = new Socket("localhost", 1234);
		System.out.println("Conectado com Database Realizada!");

		PrintStream solicitation = new PrintStream(socketDatabase.getOutputStream());
		solicitation.println(nome);

		InputStream inputStreamDatabase = socketDatabase.getInputStream();
		BufferedReader response = new BufferedReader(new InputStreamReader (inputStreamDatabase));

		saldo = Double.parseDouble(response.readLine());

		System.out.println("Conectado com Database Finalizada!");
		socketDatabase.close();
	}
}