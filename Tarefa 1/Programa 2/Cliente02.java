import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Cliente02 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 7777);
        System.out.println("Conectado!");

        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
		
		InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);	

        String nome;
		nome = JOptionPane.showInputDialog(null,"Digite o Nome:","Nome",JOptionPane.INFORMATION_MESSAGE);
		dataOutputStream.writeUTF(nome);
        dataOutputStream.flush();
		
		Object[] options = { "Masculino", "Feminino" };
		int sexo = JOptionPane.showOptionDialog(null, "Selecione o Sexo","Sexo",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		dataOutputStream.writeInt(sexo);
        dataOutputStream.flush();
		
		int idade;
		idade = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite a Idade:","Idade",JOptionPane.INFORMATION_MESSAGE));
		dataOutputStream.writeInt(idade);
        dataOutputStream.flush();
		
		String maioridade;
		maioridade = dataInputStream.readUTF();
		JOptionPane.showMessageDialog(null,maioridade);

		dataOutputStream.close();

        System.out.println("Fechando Socket e Terminando o Programa.");
        socket.close();
    }
}