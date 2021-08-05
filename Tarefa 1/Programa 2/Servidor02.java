import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor02 {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("Servidor esperando por conexoes...");
        Socket socket = ss.accept(); 
        System.out.println("Conexao de " + socket + "!");

        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
		
		OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        String nome = dataInputStream.readUTF();
		int sexo = dataInputStream.readInt();
		int idade = dataInputStream.readInt();
		
		dataOutputStream.writeUTF(maioridade(sexo,idade));
		dataOutputStream.flush();
		
		dataOutputStream.close();
		
        System.out.println("Fechando Sockets.");
        ss.close();
        socket.close();
    }
	
	public static String maioridade(int sexo, int idade){
		String resposta;
		if((sexo == 0 && idade >= 18) || (sexo == 1 && idade >= 21))
			resposta = "Atingiu a Maioridade!";
		else
			resposta = "Nao atingiu a Maioridade!";
		return resposta;
	}
}