import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor07 {
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
		int tempoServ = dataInputStream.readInt();
		
		dataOutputStream.writeUTF(aposentado(idade,tempoServ));
		dataOutputStream.flush();
		
		dataOutputStream.close();
		
        System.out.println("Fechando Sockets.");
        ss.close();
        socket.close();
    }
	
	public static String aposentado(int idade, int tempoServ){
		String resposta;
		if(idade >= 65 && tempoServ >= 30)
			resposta = "Voce ja pode se Aposentar!";
		else
			resposta = "Ainda nao se pode Aposentar";
		return resposta;
	}
}