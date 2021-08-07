import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor05 {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("Servidor esperando por conexoes...");
        Socket socket = ss.accept(); 
        System.out.println("Conexao de " + socket + "!");

        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
		
		OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

		int idade = dataInputStream.readInt();
		
		dataOutputStream.writeUTF(categoria(idade));
		dataOutputStream.flush();
		
		dataOutputStream.close();
		
        System.out.println("Fechando Sockets.");
        ss.close();
        socket.close();
    }
	
	public static String categoria(int idade){
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