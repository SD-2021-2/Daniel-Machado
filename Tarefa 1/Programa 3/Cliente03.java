import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Cliente03 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 7777);
        System.out.println("Conectado!");

        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
		
		InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);	
        
		double n1;
		n1 = Double.parseDouble(JOptionPane.showInputDialog(null,"Digite a Primeira Nota:","Nota 1",JOptionPane.INFORMATION_MESSAGE));
		dataOutputStream.writeDouble(n1);
        dataOutputStream.flush();
		
		double n2;
		n2 = Double.parseDouble(JOptionPane.showInputDialog(null,"Digite a Segunda Nota:","Nota 2",JOptionPane.INFORMATION_MESSAGE));
		dataOutputStream.writeDouble(n2);
        dataOutputStream.flush();
		
		double n3;
		n3 = Double.parseDouble(JOptionPane.showInputDialog(null,"Digite a Terceira Nota:","Nota 3",JOptionPane.INFORMATION_MESSAGE));
		dataOutputStream.writeDouble(n3);
        dataOutputStream.flush();
		
		String aprovado;
		aprovado = dataInputStream.readUTF();
		JOptionPane.showMessageDialog(null,aprovado);

		dataOutputStream.close();

        System.out.println("Fechando Socket e Terminando o Programa.");
        socket.close();
    }
}