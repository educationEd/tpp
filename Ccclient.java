import java.rmi.Naming;
import java.util.Scanner;

public class Ccclient {

	public static void main(String[] args) throws Exception {
		//Ask the registry running on localhost and listening in port 1099 for the instance of
		//the DictionaryServer object that is bound to the RMI registry with the name dictionaryService.
		DictionaryServer fs = (DictionaryServer) Naming.lookup("rmi://127.0.0.1:1099/dictionaryService");
		fs.getDictionary();
		 boolean s=true;
	     while(s) {
		 Scanner NomduScanner = new Scanner(System.in);
	        System.out.println("tapez 1 pour ajouter un mot");
	        System.out.println("tapez 2 pour supprimer un mot");
	        System.out.println("tapez 3 pour modifier un mot");
	        System.out.println("tapez 4 pour recherche un mot");
	        System.out.println("tapez 5 pour voir le dictionnaire");
	        System.out.println("tapez 6 pour exit");
	        int op = NomduScanner.nextInt();
	        if (op==1) {
	        	//System.out.println(ds.addItem(d.getWord(),d.getDefinition()));
	        	System.out.print("donner le mot pour le ajouter : ");
	        	String mot = NomduScanner.next();
	        	System.out.print("donner la deffinition : ");
	        	String def = NomduScanner.next();
	        	//stub.addItem(mot,def);
	        	System.out.println(fs.addWordMeaning(mot,def));
	        }
	        if (op==2) {
	        	//System.out.println(ds.addItem(d.getWord(),d.getDefinition()));
	        	System.out.print("donner le mot a supprimÃ© : ");
	        	String mot = NomduScanner.next();
	        //	System.out.print("donner la deffinition : ");
	        	//String def = NomduScanner.next();
	        	System.out.println(fs.deleteWordMeaning(mot));
	        }
	        if (op==3) {
	        	//System.out.println(ds.addItem(d.getWord(),d.getDefinition()));
	        	System.out.print("donner a modifier : ");
	        	String mot = NomduScanner.next();
	        	System.out.print("donner la nouvelle definition : ");
	        	String def = NomduScanner.next();
	        	System.out.println(fs.modifyWordMeaning(mot,def));
	        }
	       
	        if (op==4) {
	        	System.out.print("donner mot a cherche : ");
	        	String mot = NomduScanner.next();
	        	System.out.println(fs.getWordMeaning(mot));
	        }
	        
	        if (op==5) {
	        	System.out.print("le dictionnaire: ");
	        	//String mot = NomduScanner.next();
	        	System.out.println(fs.getDictionary());
	        }
	        if (op==6) {
	        	s=false;
	        }
	     }
	}}