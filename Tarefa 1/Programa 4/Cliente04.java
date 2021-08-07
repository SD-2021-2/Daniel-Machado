import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Cliente04 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 7777);
        System.out.println("Conectado!");

        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
		
		InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);	
        
		double altura;
		altura = Double.parseDouble(JOptionPane.showInputDialog(null,"Digite a Altura (em metros):","Altura",JOptionPane.QUESTION_MESSAGE));
		dataOutputStream.writeDouble(altura);
        dataOutputStream.flush();
		
		Object[] options = { "Masculino", "Feminino" };
		int sexo = JOptionPane.showOptionDialog(null, "Selecione o Sexo:","Sexo",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		dataOutputStream.writeInt(sexo);
        dataOutputStream.flush();
		
		String pesoIdeal;
		pesoIdeal = dataInputStream.readUTF();
		JOptionPane.showMessageDialog(null,pesoIdeal);

		dataOutputStream.close();

        System.out.println("Fechando Socket e Terminando o Programa.");
        socket.close();
    }
}