import java.net.*;
 

public class serverudp {
public static void main (String[] arg) {
	int port=1624;
	String texte =" hi ihope you are doing great my name is maroua ";
	String [] ensClient = new String [20];
	int i=0;
    try {
    DatagramSocket soc = new DatagramSocket(port);
    while (true) {
    	byte [] data = new byte[256];
    	System.out.println("en attente");
    	DatagramPacket packet = new DatagramPacket(data,data.length);
    	soc.receive(packet);
    	String mot = new String(packet.getData(),0,packet.getLength());
    	soc.receive(packet);
    	String id = new String(packet.getData(),0,packet.getLength());
    	System.out.println("the word received :"+mot+" the host identifier is :"+id);
    	int portDistant = packet.getPort();
    	InetAddress adresse = packet.getAddress();
    	System.out.println(mot.length());
    	int result=Count(texte,mot,0);
    	ensClient[i]=id;
    	i++;
    	ensClient[i]=mot;
    	String reponse ="number of occurences ="+result;
    	System.out.println("response sent succesfully !");
    	i++;
    	byte[] reponseMessage =reponse.getBytes();
    	packet = new DatagramPacket(reponseMessage,reponseMessage.length,adresse,portDistant);
    	soc.send(packet);
    	int r=0;
    	String ensemble="the clients that had searched for this word are :  ";
    	while(r<ensClient.length) {
  
		 		if(ensClient [r]!=null) {
		 			if(ensClient[r+1].equals(mot)) {
		 				ensemble +=ensClient [r];
		 				ensemble +="    ";
		 			}
		 		}
    		r+=2;
    	}
    	r=0;
    	
    	reponseMessage =ensemble.getBytes();
   
    	packet = new DatagramPacket(reponseMessage,reponseMessage.length,adresse,portDistant);
    	soc.send(packet);
    	 
    	System.out.println("-----------------------");
    	System.out.println("    history : ");
    	while(r<ensClient.length) {
    	    		if(ensClient [r]!=null) {
    	    		 		System.out.println(ensClient [r] + " a cherché le mot    "+ensClient [r+1]);
    	    		 		}
    	        		r+=2;
    	        	}
    	    	r=0;
    	System.out.println("-----------------------\n");
    }
    	
    }catch(Exception exc) {
    	System.out.println("Probleme reception");
    }
}
 static int Count(String str, String search, int cpt){
    if(str.contains(search)){
    	cpt++;
        return Count(str.substring(str.indexOf(search)+search.length()),search,cpt++);
    }
    else{ 
        return cpt;
    }
}
}