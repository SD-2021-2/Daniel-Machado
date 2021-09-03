import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor01 {
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
  
	public void run(){
		try {
		InputStream inputStreamClient = socketThread.getInputStream();
        BufferedReader request = new BufferedReader(new InputStreamReader (inputStreamClient));

		Funcionario funcionario = new Funcionario();
		funcionario.nome = request.readLine();

		ConexaoDataBase database = new ConexaoDataBase();
		database.nome = funcionario.nome;
		database.dados();

		funcionario.cargo = database.cargo;
        funcionario.sal = database.sal;

		PrintStream resposta = new PrintStream(socketThread.getOutputStream());
		resposta.print(funcionario.salReajuste());

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

class ConexaoDataBase{
	String nome;
	String cargo;
	double sal;

	void dados() throws IOException{
		Socket socketDatabase = new Socket("localhost", 1234);
		System.out.println("Conectado com Database Realizada!");

		PrintStream solicitation = new PrintStream(socketDatabase.getOutputStream());
		solicitation.println(nome);

		InputStream inputStreamDatabase = socketDatabase.getInputStream();
		BufferedReader response = new BufferedReader(new InputStreamReader (inputStreamDatabase));

		cargo = response.readLine();
        sal = Double.parseDouble(response.readLine());

		System.out.println("Conectado com Database Finalizada!");
		socketDatabase.close();
	}
}