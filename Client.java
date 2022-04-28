import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public Client() throws Exception {
		
		Socket socket = new Socket("localhost",2021);
		System.out.println("socket has been connected! ");
		
		BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
	
		String message = in_socket.readLine();
		System.out.println("Server said: " + message);
		Scanner scanner = new Scanner(System.in);
		message = scanner.nextLine();
		
		out_socket.println(message);
		System.out.println("sent to server:  " + message);
		
		socket.close();
		System.out.println("Socket closed!");
	}
	
	public static void main(String[] args) {
		
		try {
			new Client();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
}