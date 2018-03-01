package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.GatewayException;
import model.Publisher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PublisherGateway {
	private Connection conn;
	
	public PublisherGateway(Connection conn) {
		this.conn = conn;
	}
	
	public ObservableList<Publisher> getPublishers() throws GatewayException {
		ObservableList<Publisher> publishers = FXCollections.observableArrayList();
		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("select * from publisher order by publisher_name");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Publisher publisher = new Publisher(rs.getInt("id"), rs.getString("publisher_name"));
				publishers.add(publisher);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GatewayException(e);
		} finally {
			try {
				if(st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new GatewayException(e);
			}
		}
		
		return publishers;
	}
}
