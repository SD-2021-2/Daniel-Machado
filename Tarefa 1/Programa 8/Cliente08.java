import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Cliente08 {
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("localhost", 7777);
		System.out.println("Conectado!");

		OutputStream outputStream = socket.getOutputStream();
		DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
		
		InputStream inputStream = socket.getInputStream();
		DataInputStream dataInputStream = new DataInputStream(inputStream);	
        
		while(true){
			try{
				double saldo;
				saldo = Double.parseDouble(JOptionPane.showInputDialog(null,"Digite o Saldo Medio:","Saldo",JOptionPane.QUESTION_MESSAGE));
				dataOutputStream.writeDouble(saldo);
				dataOutputStream.flush();
				break;
			}catch (NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Formato Errado! Tente Novamente\n(Ex: 200)", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		String valor;
		valor = dataInputStream.readUTF();
		JOptionPane.showMessageDialog(null,valor);

		dataOutputStream.close();

		System.out.println("Fechando Socket e Terminando o Programa.");
		socket.close();
	}
}