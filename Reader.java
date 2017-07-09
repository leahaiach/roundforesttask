
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {
	
	//Regex for a csv line of data
	public static final String REVIEW_PATTERN= ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
	
	/**
	 * We read the file line by line and insert or update the three hashmaps each time
	 * @param fileName - path to csv file
	 * @param userActivities - users hashmap
	 * @param commentedProducts- product hashmap
	 * @param wordsUsed - words hashmap
	 * @throws IOException
	 */
	public static void readsReviewFromCSV(String fileName, HashMap<User, Integer> userActivities, HashMap<String, Integer> commentedProducts,
										  HashMap<String, Integer> wordsUsed) throws IOException {
		
		FileInputStream inputStream = null;
		Scanner sc = null;
		
		try  {
			//I chose Scanner because we want to be able to run it on 500MB RAM
			inputStream = new FileInputStream(new File(fileName));
		    sc = new Scanner(inputStream, "UTF-8");
		    String line = sc.nextLine();//skip first line
		    
		    while (sc.hasNextLine()) {
		    	
		        line = sc.nextLine();
		        
		        //Get the data 
		        String[] tokens = line.split(REVIEW_PATTERN, -1);
				//int review_id = Integer.parseInt(tokens[0]);
				String product = tokens[1];
				User user = new User(tokens[2],tokens[3]);
				String text = tokens[9];
				
		        if (!userActivities.containsKey(user)){
		        	userActivities.put(user, 1);
		        }
		        else{
		        	userActivities.put(user, userActivities.get(user) + 1);
		        }
		        if (!commentedProducts.containsKey(product))
		        	commentedProducts.put(product, 1);
		        else
		        	commentedProducts.put(product,commentedProducts.get(product)+1);
		        
		        //need to read word by word the text without punctuation
		        Pattern p = Pattern.compile("[\\w']+");
		        Matcher m = p.matcher(text);
		        while ( m.find() ) {
		            String word = text.substring(m.start(), m.end());
		        	if (!wordsUsed.containsKey(word))
		        		wordsUsed.put(word, 1);
		        	else
		        		wordsUsed.put(word,wordsUsed.get(word)+1);
		        }
		   
		         
		    }
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
		} 
		finally {
		    if (inputStream != null) {
		        inputStream.close();
		    }
		    if (sc != null) {
		        sc.close();
		    }
		}
	}
	

}
