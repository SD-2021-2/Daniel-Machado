import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONException;
import org.json.JSONObject;

public class ServidorJson09 {
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

		Carta carta = new Carta();

		carta.numero = json_obj.getInt("numero");
		carta.tipo = json_obj.getInt("naipe");
		
		json_obj.put("extenso", carta.cartaExtenso());

		PrintStream resposta = new PrintStream(socketThread.getOutputStream());
		resposta.println(json_obj.toString());

		socketThread.close();
		System.out.println(socketThread +" Finalizado!");
				
		}catch (IOException e) {
			System.out.println("Erro na troca de mensagens!");
		}
	}  
}

class Carta {
	int numero;
	int tipo;

	String cartaExtenso(){
		String extenso = "Sua Carta: ";
	
		switch(numero){
			case 1:
				extenso = extenso.concat("As de ");
				break;
			case 2:
				extenso = extenso.concat("Dois de ");
				break;
			case 3:
				extenso = extenso.concat("Tres de ");
				break;
			case 4:
				extenso = extenso.concat("Quatro de ");
				break;
			case 5:
				extenso = extenso.concat("Cinco de ");
				break;
			case 6:
				extenso = extenso.concat("Seis de ");
				break;
			case 7:
				extenso = extenso.concat("Sete de ");
				break;
			case 8:
				extenso = extenso.concat("Oito de ");
				break;
			case 9:
				extenso = extenso.concat("Nove de ");
				break;
			case 10:
				extenso = extenso.concat("Dez de ");
				break;
			case 11:
				extenso = extenso.concat("Valete de ");
				break;
			case 12:
				extenso = extenso.concat("Dama de ");
				break;
			case 13:
				extenso = extenso.concat("Rei de ");
				break;
		}	
		
		switch(tipo){
			case 1:
				extenso = extenso.concat("Ouros");
				break;
			case 2:
				extenso = extenso.concat("Paus");
				break;
			case 3:
				extenso = extenso.concat("Copas");
				break;
			case 4:
				extenso = extenso.concat("Espadas");
				break;
		}
		return extenso;
	}
}