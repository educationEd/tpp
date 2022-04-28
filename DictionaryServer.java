import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;


public interface DictionaryServer extends Remote{
	
	public String getWordMeaning(String word) throws RemoteException;
	
	public String addWordMeaning(String word, String meaning) throws RemoteException;
	
	public String deleteWordMeaning(String word) throws RemoteException;
	
	public String modifyWordMeaning(String word, String meaning) throws RemoteException;
	
	public  HashMap<String,String> getDictionary()throws RemoteException;
	
}