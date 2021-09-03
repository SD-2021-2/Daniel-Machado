import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor06 {
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
		funcionario.nivel = database.nivel;
		funcionario.sal = database.sal;
		funcionario.dependentes = database.dependentes;

		PrintStream resposta = new PrintStream(socketThread.getOutputStream());
		resposta.print(funcionario.salLiquido());

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
class ConexaoDataBase{
	String nome;
	double sal;
	String nivel;
	int dependentes;

	void dados() throws IOException{
		Socket socketDatabase = new Socket("localhost", 1234);
		System.out.println("Conectado com Database Realizada!");

		PrintStream solicitation = new PrintStream(socketDatabase.getOutputStream());
		solicitation.println(nome);

		InputStream inputStreamDatabase = socketDatabase.getInputStream();
		BufferedReader response = new BufferedReader(new InputStreamReader (inputStreamDatabase));

		sal = Double.parseDouble(response.readLine());
		nivel = response.readLine();
        dependentes = Integer.parseInt(response.readLine());

		System.out.println("Conectado com Database Finalizada!");
		socketDatabase.close();
	}
}