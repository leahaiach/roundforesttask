

/**
 * This class creates an amazon user
 * @author leah
 *
 */
public class User {
	
	String id;
	String profileName;
	
	public User(String myid, String myprofile) {
		// TODO Auto-generated constructor stub
		id = myid;
		profileName = myprofile;
	}
	
	public String getUserID(){
		return this.id;
	}
	
	public String getUserProfile(){
		return this.profileName;
	}
	
	@Override
	public boolean equals(Object o){
	    if(o == null)                
	    	return false;
	    if(!(o instanceof User)) 
	    	return false;

	    User other = (User) o;
	    if(this.profileName.equals(other.getUserProfile()) && this.id.equals(other.getUserID()))      
	    	return true;
	   
	    return false;
	  }

	@Override 
	public int hashCode() {
		return this.profileName.hashCode()+this.id.hashCode(); 
	}

	
}
