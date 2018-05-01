package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class PublisherGateway {
	private Connection conn;
	
	public PublisherGateway() throws GatewayException{
		conn = null;
		
		Properties props = new Properties();
		FileInputStream fis = null;
		try{
			fis = new FileInputStream("db.properties");
			props.load(fis);
			fis.close();
			
			//create the datasource
			MysqlDataSource ds = new MysqlDataSource();
			ds.setURL(props.getProperty("MYSQL_DB_URL"));
			ds.setUser(props.getProperty("MYSQL_DB_USERNAME"));
			ds.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
			
			//create the connection
			conn = (Connection) ds.getConnection();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			throw new GatewayException(e);
		}
	}
	
	public List<Publisher> getPublishers() {
		List<Publisher> publishers = new ArrayList<Publisher>();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			st = conn.prepareStatement("Select * from publisher");
			rs = st.executeQuery();
			
			while(rs.next()){
				//create an author object from the record
				SimpleStringProperty publisherName = new SimpleStringProperty(rs.getString("publisher_name"));
				Publisher publisher = new Publisher(rs.getInt("id"), publisherName);
				publishers.add(publisher);
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try{
				if(rs != null)
					rs.close();
				if(st != null)
					st.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return publishers;
	}
	
	public Publisher getPublisherById(int pubId) {
		PreparedStatement st = null;
		ResultSet rs = null;
		Publisher publisher = null;
		
		try{
			st = conn.prepareStatement("Select * from publisher WHERE id = '" + pubId + "'");
			rs = st.executeQuery();
			
			while(rs.next()){
				//create an author object from the record
				SimpleStringProperty publisherName = new SimpleStringProperty(rs.getString("publisher_name"));
				publisher = new Publisher(rs.getInt("id"), publisherName);
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try{
				if(rs != null)
					rs.close();
				if(st != null)
					st.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return publisher;
	}
	
	public ObservableList<Book> getBooksByPublisherId(int id) throws GatewayException {
		ObservableList<Book> books = FXCollections.observableArrayList();
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("select * from book where publisher_id = ? order by title");
			st.setInt(1, id);
			rs = st.executeQuery();
			while(rs.next()) {
				Book book = new Book(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("summary"),
						rs.getInt("year_published"),
						rs.getString("isbn"), 
						rs.getDate("date_added").toLocalDate(),
						new PublisherGateway().getPublisherById(rs.getInt("publisher_id")));
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GatewayException(e);
		} finally {
			try {
				if(st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new GatewayException(e);
			}
		}
		return books;
	}
	
	public void close(){
		if(conn != null){
			try{
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
}