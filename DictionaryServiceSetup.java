import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class DictionaryServiceSetup {

	public static void main(String[] args) throws Exception {

		//Create an instance of a dictionaryServiceImpl. As dictionaryServiceImpl implements the dictionaryService
		//interface, it can be referred to as a dictionaryService type.
		DictionaryServer fs = new DictionaryServiceImpl();
		
		//Start the RMI registry on port 1099
		LocateRegistry.createRegistry(1099);

		//Bind our remote object to the registry with the human-readable name "dictionaryService"
		Naming.rebind("dictionaryService", fs);

		//Print a message to standard output
		System.out.println("Server ready.");


		
	}
}