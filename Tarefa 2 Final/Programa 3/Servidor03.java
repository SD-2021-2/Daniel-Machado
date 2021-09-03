import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor03 {
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
		
		Notas notas = new Notas();
		notas.n1 = database.n1;
		notas.n2 = database.n2;
		notas.n3 = database.n3;

		PrintStream resposta = new PrintStream(socketThread.getOutputStream());
		resposta.print(notas.aprovado());

		socketThread.close();
		System.out.println(socketThread +" Finalizado!");
		
		}catch (IOException e) {
			System.out.println("Erro na troca de mensagens!");
		}
	}  
}

class Notas {
	double n1,n2,n3;
	
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

class ConexaoDataBase{
	String nome;
	double n1,n2,n3;

	void dados() throws IOException{
		Socket socketDatabase = new Socket("localhost", 1234);
		System.out.println("Conectado com Database Realizada!");

		PrintStream solicitation = new PrintStream(socketDatabase.getOutputStream());
		solicitation.println(nome);

		InputStream inputStreamDatabase = socketDatabase.getInputStream();
		BufferedReader response = new BufferedReader(new InputStreamReader (inputStreamDatabase));

        n1 = Double.parseDouble(response.readLine());
		n2 = Double.parseDouble(response.readLine());
		n3 = Double.parseDouble(response.readLine());

		System.out.println("Conectado com Database Finalizada!");
		socketDatabase.close();
	}
}