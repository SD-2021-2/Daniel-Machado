import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor04 {
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
		
		Pessoa pessoa = new Pessoa();
		pessoa.altura = database.altura;
		pessoa.sexo = database.sexo;

		PrintStream resposta = new PrintStream(socketThread.getOutputStream());
		resposta.print(pessoa.pesoIdeal());

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

class ConexaoDataBase{
	String nome;
	double altura;
	String sexo;;

	void dados() throws IOException{
		Socket socketDatabase = new Socket("localhost", 1234);
		System.out.println("Conectado com Database Realizada!");

		PrintStream solicitation = new PrintStream(socketDatabase.getOutputStream());
		solicitation.println(nome);

		InputStream inputStreamDatabase = socketDatabase.getInputStream();
		BufferedReader response = new BufferedReader(new InputStreamReader (inputStreamDatabase));

        altura = Double.parseDouble(response.readLine());
		sexo = response.readLine();

		System.out.println("Conectado com Database Finalizada!");
		socketDatabase.close();
	}
}