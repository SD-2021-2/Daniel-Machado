import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor09 {
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
		database.numero = Integer.parseInt(request.readLine());
		database.tipo = Integer.parseInt(request.readLine());
		database.dados();
		
		Carta carta = new Carta();
		carta.valor = database.valor;
		carta.naipe = database.naipe;
		

		PrintStream resposta = new PrintStream(socketThread.getOutputStream());
		resposta.print(carta.cartaExtenso());

		socketThread.close();
		System.out.println(socketThread +" Finalizado!");
		
		}catch (IOException e) {
			System.out.println("Erro na troca de mensagens!");
		}
	}  
}

class Carta {
	String valor;
	String naipe;

	String cartaExtenso(){
		String extenso = "Sua Carta: ";
		extenso = extenso.concat(valor+" de");
		extenso = extenso.concat(" "+naipe);
		return extenso;
	}
}

class ConexaoDataBase{
	int numero;
	int tipo;
	String valor;
	String naipe;

	void dados() throws IOException{
		Socket socketDatabase = new Socket("localhost", 1234);
		System.out.println("Conectado com Database Realizada!");

		PrintStream solicitation = new PrintStream(socketDatabase.getOutputStream());
		solicitation.println(numero+"\n"+tipo);

		InputStream inputStreamDatabase = socketDatabase.getInputStream();
		BufferedReader response = new BufferedReader(new InputStreamReader (inputStreamDatabase));

		valor = response.readLine();
		naipe = response.readLine();

		System.out.println("Conectado com Database Finalizada!");
		socketDatabase.close();
	}
}