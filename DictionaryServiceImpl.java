import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.*;
import java.rmi.server.*;
import java.util.HashMap;

public class DictionaryServiceImpl extends UnicastRemoteObject implements DictionaryServer {
	private static final long serialVersionUID = 1L;


	public DictionaryServiceImpl() throws RemoteException{
		super();
	}

	public  HashMap<String,String> getDictionary()  throws RemoteException{
		HashMap<String,String> dictionary = new HashMap<String,String>();
		String dictionaryPath = "C:\\Users\\DELL\\Documents\\tp\\dic.csv";
		// Reading line by line the word and definitions in Dictionary.csv file
		try(BufferedReader br = new BufferedReader(new FileReader(dictionaryPath))) {
			for(String line; (line = br.readLine()) != null; ) {
				String[] tokens = line.split(",");	
				dictionary.put(tokens[0], tokens[1]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dictionary;
	}
	/* Getting the meaning of the word entered by user
	 * and putting the thread to sleep for 10 seconds
	 * to slow the service down and simulate a real asynchronous service.
	*/
	@Override
	
//	public void load()throws RemoteException{
		
//	}
	
	public String getWordMeaning(String word) throws RemoteException {	
		
		HashMap<String,String> dictionary = getDictionary();
		
		if(dictionary.containsKey(word)){
			String s = dictionary.get(word);
			return s;
		}else
			return "Word not found!";
	}

	/*
	 * Adding word in dictionary 
	 */
	@Override
	
	
	public String addWordMeaning(String word, String meaning) throws RemoteException {
		
		HashMap<String,String> dictionary = getDictionary();
		
		if(dictionary.containsKey(word)){
			return "Word already exists";
		}else
			if(addToFile(word, meaning)){
			return word+" sucessfully added to dictionary";
			} else{
				return "Error at RMI server updating the dictionary!";
			}
	}
	
	/*
	 * Deleting word in dictionary 
	 */
	@Override
	public String deleteWordMeaning(String word) throws RemoteException {	
		
		HashMap<String,String> dictionary = getDictionary();
		
		if(dictionary.containsKey(word)){
			if(deleteInFile(word))
				return word+" sucessfully deleted from dictionary";
			else
				return "Error deleting word";
		}else{
			return "Word does not exist in Dictionary";
		}
	}
	
	/*
	 * Modifying word in dictionary 
	 */
	@Override
	public String modifyWordMeaning(String word, String meaning) throws RemoteException {
		
		HashMap<String,String> dictionary = getDictionary();
		
		if(dictionary.containsKey(word)){
			deleteInFile(word);
			addToFile(word, meaning);
			return word+" sucessfully modified in dictionary";
		}else{
			return "Word does not exist in Dictionary";
		}
	}
	
	
	private boolean addToFile(String word, String meaning) {
		PrintWriter out = null;
		Boolean flag = false;
		try{
			    out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\DELL\\Documents\\tp\\dic.csv", true)));
			    out.print(word+","+meaning+"\r\n");
			    flag = true;
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				out.close();
			}
		return flag;
	}
	
	/*
	 * Deleting word in dictionary file 
	 */
	public static boolean deleteInFile(String word){

		Boolean flag = false;
		try{
		
		File inputFile = new File("C:\\Users\\DELL\\Documents\\tp\\dic.csv");
		File tempFile = new File("C:\\Users\\DELL\\Documents\\tp\\dictemp.csv");

		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		
		String wordToRemove = word;
		String currentLine;

		while((currentLine = reader.readLine()) != null) {
		    // trim newline when comparing with lineToRemove
		    String[] tokens = currentLine.trim().split(",");
		    if(tokens[0].equals(word)) continue;
		    writer.write(currentLine + System.getProperty("line.separator"));
		}
		writer.close(); 
		reader.close(); 
		if (inputFile.exists()) inputFile.delete();
		   flag = tempFile.renameTo(inputFile);

	} catch (IOException e) {
		e.printStackTrace();
	} 
	return flag;
	}
	
}