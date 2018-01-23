
public class Dog {
	private String dogName;
	
	public String Dog(dogName) {
		this();
	}
		
	
	//biz logic
	public String getDogName() {
		return dogName;
	}
	public void setDogName(String dogName) {
		this.dogName = dogName;
	}
	public boolean isValidDogName(String dogName) {
		//dog name must be between 1 and 50 chars
		if(!isValidDogName(dogName))
			throw new IllegalArgumentException("Dog name must be bewtween 2 and 50 charcatres");
		setDogName(dogName);
		
	}

}
