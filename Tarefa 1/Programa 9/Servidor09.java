import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor09 {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(7777);
		System.out.println("Servidor esperando por conexoes...");
		Socket socket = ss.accept(); 
		System.out.println("Conexao de " + socket + "!");

		InputStream inputStream = socket.getInputStream();
		DataInputStream dataInputStream = new DataInputStream(inputStream);
		
		OutputStream outputStream = socket.getOutputStream();
		DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

		Carta carta = new Carta();

		carta.numero = dataInputStream.readInt();
		carta.tipo = dataInputStream.readInt();
		
		dataOutputStream.writeUTF(carta.cartaExtenso());
		dataOutputStream.flush();
		
		dataOutputStream.close();
		
		System.out.println("Fechando Sockets.");
		ss.close();
		socket.close();
    }
}

public class Carta {

	public int numero;
	public int tipo;

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
			case 0:
				extenso = extenso.concat("Ouros");
				break;
				
			case 1:
				extenso = extenso.concat("Paus");
				break;
				
			case 2:
				extenso = extenso.concat("Copas");
				break;
				
			case 3:
				extenso = extenso.concat("Espadas");
				break;
		}
		return extenso;
	}

}
