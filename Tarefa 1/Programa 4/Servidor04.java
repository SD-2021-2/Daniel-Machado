import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor04 {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("Servidor esperando por conexoes...");
        Socket socket = ss.accept(); 
        System.out.println("Conexao de " + socket + "!");

        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
		
		OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        double altura = dataInputStream.readDouble();
		int sexo = dataInputStream.readInt();
		
		dataOutputStream.writeUTF(pesoIdeal(altura,sexo));
		dataOutputStream.flush();
		
		dataOutputStream.close();
		
        System.out.println("Fechando Sockets.");
        ss.close();
        socket.close();
    }
	
	public static String pesoIdeal(double altura, int sexo){
		String resposta;
		double peso;
		if(sexo == 0){
			peso = (72.7*altura)-58;
			resposta = "Seu peso ideal: " +peso;
		}else{
			peso = (62.1*altura)-44.7;
			resposta = "Seu peso ideal: " +peso;
		}
		return resposta;
	}
}