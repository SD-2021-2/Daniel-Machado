import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Cliente07 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 7777);
        System.out.println("Conectado!");

        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
		
		InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);	
        
		while(true){
			try{
				int idade;
				idade = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite sua Idade:","Idade",JOptionPane.QUESTION_MESSAGE));
				dataOutputStream.writeInt(idade);
				dataOutputStream.flush();
				break;
			}catch (NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Formato Errado! Tente Novamente\n(Ex: 70)", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		while(true){
			try{
				int tempoServ;
				tempoServ = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite seu Tempo de Serviço em Anos:","Tempo de Serviço",JOptionPane.QUESTION_MESSAGE));
				dataOutputStream.writeInt(tempoServ);
				dataOutputStream.flush();
				break;
			}catch (NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Formato Errado! Tente Novamente\n(Ex: 20)", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		String aposentado;
		aposentado = dataInputStream.readUTF();
		JOptionPane.showMessageDialog(null,aposentado);

		dataOutputStream.close();

        System.out.println("Fechando Socket e Terminando o Programa.");
        socket.close();
    }
}