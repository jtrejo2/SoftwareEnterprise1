package model;

import javafx.beans.property.SimpleStringProperty;

public class Publisher {
	private  int id;
	private SimpleStringProperty publisherName;
	
	
	public Publisher() {
		publisherName = new SimpleStringProperty();
	}
	
	public  Publisher(int id, String publisherName){
		this();
		setId(id);
		setPublisherName(publisherName);
	}
	
	@Override
	public String toString() {
		return getPublisherName();
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
	
	public SimpleStringProperty publisherNameProperty() {
		return publisherName;
	}
}