import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor03 {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("Servidor esperando por conexoes...");
        Socket socket = ss.accept(); 
        System.out.println("Conexao de " + socket + "!");

        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
		
		OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        double n1 = dataInputStream.readDouble();
		double n2 = dataInputStream.readDouble();
		double n3 = dataInputStream.readDouble();
		
		dataOutputStream.writeUTF(aprovado(n1,n2,n3));
		dataOutputStream.flush();
		
		dataOutputStream.close();
		
        System.out.println("Fechando Sockets.");
        ss.close();
        socket.close();
    }
	
	public static String aprovado(double n1, double n2, double n3){
		String resposta;
		double m = (n1+n2)/2;
		if(m >= 7.0 || (m > 3.0 && (m+n3)/2 >= 5.0))
			resposta = "Parabens! Voce foi Aprovado!";
		else
			resposta = "Reprovado... mas sempre ha uma proxima vez!";
		return resposta;
	}
}