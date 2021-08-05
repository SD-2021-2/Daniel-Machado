import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor01 {
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
		String cargo = dataInputStream.readUTF();
		double sal = dataInputStream.readDouble();
		
		if(cargo.equals("operador")){
			dataOutputStream.writeUTF("Funcionario " +nome+ " tera seu salario reajustado para " +sal*1.2);
			dataOutputStream.flush();
		}
		if(cargo.equals("programador")){
			dataOutputStream.writeUTF("Funcionario " +nome+ " tera seu salario reajustado para " +sal*1.18);
			dataOutputStream.flush();
		}
		else{
			dataOutputStream.writeUTF("Funcionario nao recebera reajuste");
			dataOutputStream.flush();
		}
		dataOutputStream.close();
		
        System.out.println("Fechando Sockets.");
        ss.close();
        socket.close();
    }
}