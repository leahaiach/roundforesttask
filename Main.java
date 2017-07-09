import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Main {
	public static String REVIEW_FILE = "Reviews.csv";
	public static int TOP = 1000;
	public static HashMap<User, Integer>  userActivities = new HashMap<User, Integer>();
	public static HashMap<String, Integer> commentedProducts = new HashMap<String, Integer>();
	public static HashMap<String , Integer> wordsUsed = new HashMap<String, Integer>();
	
	/**
	 * We use this function in order to sort the Hashmap by its value
	 * @param map
	 * @param n
	 * @return
	 */
	private static <K, V extends Comparable<? super V>> List<Entry<K, V>> findGreatest(Map<K, V> map, int n){

		Comparator<? super Entry<K, V>> comparator = new Comparator<Entry<K, V>>(){

			@Override /**Compare by the value **/
			public int compare(Entry<K, V> e0, Entry<K, V> e1){
				V v0 = e0.getValue();
				V v1 = e1.getValue();
				return v0.compareTo(v1);
			}
		};

		PriorityQueue<Entry<K, V>> highest = new PriorityQueue<Entry<K,V>>(n, comparator);

		for (Entry<K, V> entry : map.entrySet())
		{
			highest.offer(entry);
			while (highest.size() > n)
			{
				highest.poll();
			}
		}

		List<Entry<K, V>> result = new ArrayList<Map.Entry<K,V>>();
		while (highest.size() > 0)
		{
			result.add(highest.poll());
		}
		return result;
	}
	
	/**
	 * Insert the results into an arrayList so that we can sort it alphabetically
	 * @param greatesProduct
	 * @param greatestUser
	 * @param greatestWord
	 * @param products - will hold sorted result for product
	 * @param user - will hold sorted result for user
	 * @param words - will hold sorted result for words
	 */
	public static void sortResult(List<Entry<String, Integer>> greatesProduct, List<Entry<User, Integer>> greatestUser, List<Entry<String, Integer>> greatestWord,
							ArrayList<String> products,ArrayList<String> user, ArrayList<String> words){
		
		
		for(Entry<String, Integer> p : greatesProduct){
			products.add(p.getKey());
		}
		for(Entry<User, Integer> u : greatestUser){
			user.add(u.getKey().getUserProfile());
		}
		for(Entry<String, Integer> w : greatestWord){
			words.add(w.getKey());
		}
		sortMyList(products);
		sortMyList(user);
		sortMyList(words);
		
	}
	
	/**
	 * Sort List alphabetically
	 * @param list
	 */
	public static void sortMyList(ArrayList<String> list){
		Collections.sort(list, new Comparator<String>() {
		    @Override
		    public int compare(String s1, String s2) {
		        return s1.compareToIgnoreCase(s2);
		    }
		});
	}


	public static void main(String[] args) {
		
		
		try {
			Reader.readsReviewFromCSV(REVIEW_FILE, userActivities, commentedProducts, wordsUsed);
			
			List<Entry<String, Integer>> greatestProd = findGreatest( commentedProducts, TOP);
			List<Entry<User, Integer>> greatestUser = findGreatest( userActivities, TOP);
			List<Entry<String, Integer>> greatestWord = findGreatest(wordsUsed, TOP);
			
			ArrayList<String> products = new ArrayList<String>();
			ArrayList<String> user = new ArrayList<String>();
			ArrayList<String> words = new ArrayList<String>();
			sortResult( greatestProd, greatestUser,  greatestWord,products,user,  words);
			
			System.out.println("Top "+TOP+" most active users:");
			System.out.println(Arrays.toString(user.toArray()));
	        System.out.println("====================");
	        System.out.println(TOP+"  most commented food items:");
	        System.out.println(Arrays.toString(products.toArray()));
	        System.out.println("====================");
	        System.out.println(TOP+" most used words in the reviews:");
	        System.out.println(Arrays.toString(words.toArray()));

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
	