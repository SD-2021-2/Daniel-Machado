import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor06 {
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
		double sal = dataInputStream.readDouble();
		String nivel = dataInputStream.readUTF();
		int dependentes = dataInputStream.readInt();
		
		dataOutputStream.writeUTF(salLiquido(nome,sal,nivel,dependentes));
		dataOutputStream.flush();
		
		dataOutputStream.close();
		
        System.out.println("Fechando Sockets.");
        ss.close();
        socket.close();
    }
	
	public static String salLiquido(String nome,double sal,String nivel,int dependentes){
		String resposta = "Erro.";
		switch(nivel){
			case "Nivel: A":
				if(dependentes > 0)
					resposta = ("Nome: " +nome+ "\n" +nivel+ "\nSalario Liquido: " +sal*0.92+ " reais");
				else
					resposta = ("Nome: " +nome+ "\n" +nivel+ "\nSalario Liquido: " +sal*0.97+ " reais");
				break;
				
			case "Nivel: B":
				if(dependentes > 0)
					resposta = ("Nome: " +nome+ "\n" +nivel+ "\nSalario Liquido: " +sal*0.9+ " reais");
				else
					resposta = ("Nome: " +nome+ "\n" +nivel+ "\nSalario Liquido: " +sal*0.95+ " reais");
				break;
				
			case "Nivel: C":
				if(dependentes > 0)
					resposta = ("Nome: " +nome+ "\n" +nivel+ "\nSalario Liquido: " +sal*0.85+ " reais");
				else
					resposta = ("Nome: " +nome+ "\n" +nivel+ "\nSalario Liquido: " +sal*0.92+ " reais");
				break;
				
			case "Nivel: D":
				if(dependentes > 0)
					resposta = ("Nome: " +nome+ "\n" +nivel+ "\nSalario Liquido: " +sal*0.83+ " reais");
				else
					resposta = ("Nome: " +nome+ "\n" +nivel+ "\nSalario Liquido: " +sal*0.9+ " reais");
				break;
		}
		return resposta;
	}
}