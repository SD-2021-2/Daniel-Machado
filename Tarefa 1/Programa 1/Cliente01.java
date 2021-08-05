import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Cliente01 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 7777);
        System.out.println("Conectado!");

        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
		
		InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);	

        String nome;
		nome = JOptionPane.showInputDialog(null,"Digite o Nome do funcionario:","Funcionario",JOptionPane.INFORMATION_MESSAGE);
		dataOutputStream.writeUTF(nome);
        dataOutputStream.flush();
		
		String cargo;
		cargo = JOptionPane.showInputDialog(null,"Digite o Cargo do funcionario:","Funcionario",JOptionPane.INFORMATION_MESSAGE);
		dataOutputStream.writeUTF(cargo);
        dataOutputStream.flush();
		
		double sal;
		sal = Double.parseDouble(JOptionPane.showInputDialog(null,"Digite o Salario do funcionario:","Funcionario",JOptionPane.INFORMATION_MESSAGE));
		dataOutputStream.writeDouble(sal);
        dataOutputStream.flush();

		String reajuste;
		reajuste = dataInputStream.readUTF();
		JOptionPane.showMessageDialog(null,reajuste);

		dataOutputStream.close();

        System.out.println("Fechando Socket e Terminando o Programa.");
        socket.close();
    }
}