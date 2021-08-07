import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Cliente05 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 7777);
        System.out.println("Conectado!");

        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
		
		InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);	
        
		int idade;
		idade = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite a Idade do Nadador(a):","Idade",JOptionPane.QUESTION_MESSAGE));
		dataOutputStream.writeInt(idade);
        dataOutputStream.flush();
		
		String categoria;
		categoria = dataInputStream.readUTF();
		JOptionPane.showMessageDialog(null,categoria);

		dataOutputStream.close();

        System.out.println("Fechando Socket e Terminando o Programa.");
        socket.close();
    }
}