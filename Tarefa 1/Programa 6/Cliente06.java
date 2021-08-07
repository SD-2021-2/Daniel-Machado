import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Cliente06 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 7777);
        System.out.println("Conectado!");

        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
		
		InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);	
        
		String nome;
		nome = JOptionPane.showInputDialog(null,"Digite o Nome:","Nome",JOptionPane.QUESTION_MESSAGE);
		dataOutputStream.writeUTF(nome);
        dataOutputStream.flush();
		
		while(true){
			try{
				double sal;
				sal = Double.parseDouble(JOptionPane.showInputDialog(null,"Digite o Salario Bruto:","Salario",JOptionPane.QUESTION_MESSAGE));
				dataOutputStream.writeDouble(sal);
				dataOutputStream.flush();
				break;
			}catch (NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Formato Errado! Tente Novamente\n(Ex: 1000.0)", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		Object[] NiveisPossiveis = {"Nivel: A","Nivel: B","Nivel: C","Nivel: D"};
		Object nivel = JOptionPane.showInputDialog(null, "Escolha o Nivel", "Nivel",JOptionPane.INFORMATION_MESSAGE, null, NiveisPossiveis, NiveisPossiveis[0]);
		dataOutputStream.writeUTF(nivel.toString());
		
		while(true){
			try{
				int dependentes;
				dependentes = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o numero de dependentes","Dependentes",JOptionPane.QUESTION_MESSAGE));
				dataOutputStream.writeInt(dependentes);
				dataOutputStream.flush();
					break;
			}catch (NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Formato Errado! Tente Novamente\n(Ex: 2)", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		String salLiquido;
		salLiquido = dataInputStream.readUTF();
		JOptionPane.showMessageDialog(null,salLiquido);

		dataOutputStream.close();

        System.out.println("Fechando Socket e Terminando o Programa.");
        socket.close();
    }
}