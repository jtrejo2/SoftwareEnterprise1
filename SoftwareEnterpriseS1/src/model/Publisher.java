package model;

import javafx.beans.property.SimpleStringProperty;

public class Publisher {
	private  int id;
	private SimpleStringProperty publisherName;
	
	public  Publisher(String publisherName){
		this.publisherName = new SimpleStringProperty(publisherName);
	}
	
	public Publisher() {
		this("No Name");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPublisherName() {
		return publisherName.get();
	}

	public void setPublisherName(String publisherName) {
		this.publisherName.set(publisherName);
	}
}