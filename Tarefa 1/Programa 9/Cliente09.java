import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Cliente09 {
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("localhost", 7777);
		System.out.println("Conectado!");

		OutputStream outputStream = socket.getOutputStream();
		DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
		
		InputStream inputStream = socket.getInputStream();
		DataInputStream dataInputStream = new DataInputStream(inputStream);	
		
		Object[] cartas = {1,2,3,4,5,6,7,8,9,10,11,12,13};
		Object valorCarta = JOptionPane.showInputDialog(null, "Escolha o valor da Carta", "Carta",JOptionPane.QUESTION_MESSAGE, null, cartas, cartas[0]);
		dataOutputStream.writeInt(Integer.parseInt(valorCarta.toString()));
		
		Object[] naipesPossiveis = {"Ouros","Paus","Copas","Espadas"};
		int naipe = JOptionPane.showOptionDialog(null, "Escolha o Naipe", "Naipe",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, naipesPossiveis, naipesPossiveis[0]);
		dataOutputStream.writeInt(naipe);
		
		String valor;
		valor = dataInputStream.readUTF();
		JOptionPane.showMessageDialog(null,valor,"Carta",JOptionPane.INFORMATION_MESSAGE);

		dataOutputStream.close();

		System.out.println("Fechando Socket e Terminando o Programa.");
		socket.close();
    }
}