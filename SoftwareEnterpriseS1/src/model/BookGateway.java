package model;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class BookGateway {
	private Connection conn;
	private static Logger logger = LogManager.getLogger();
	
	public List<Book> getBook() throws GatewayException{
		List<Book> books = new ArrayList<Book>();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			st = conn.prepareStatement("Select * from book");
			rs = st.executeQuery();
			
			while(rs.next()){
				
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
		
		return books;
	}
	
	public BookGateway() throws GatewayException{
		conn = null;
		
		Properties props = new Properties();
		FileInputStream fis = null;
		try{
			fis = new FileInputStream("db.properties");
			props.load(fis);
			fis.close();
			//connect to the db
			MysqlDataSource ds = new MysqlDataSource();
			ds.setURL(props.getProperty("MYSQL_DB_URL"));
			ds.setUser(props.getProperty("MYSQL_DB_USERNAME"));
			ds.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
			
			conn = ds.getConnection();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			throw new GatewayException(e);
		}
	}
	//update the book in the db
	public void updateBook(Book book) throws GatewayException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("update book set title = ?, "
					+ "summary= ?, year_published=?, isbn = ?,"
					+ "where id = ? ");

			st.setString(1, book.getTitle());
			st.setString(2, book.getSummary());
			st.setInt(3,book.getYearPublished());
			st.setString(4, book.getIsbn());
			st.setInt(5, book.getId());
			
			st.executeUpdate();
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
	}
	
	//insert an book in the db
	public void insertBook (Book book) throws GatewayException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("insert into book( title, summary, year_published, isbn ) values( ?, ?, ?, ? )");
			st.setString(1, book.getTitle());
			st.setString(2, book.getSummary());
			st.setInt(3, book.getYearPublished());
			st.setString(4, book.getIsbn());
			
			st.executeUpdate();
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
	}
	
	//delete an book from the db
	public void bookDelete(Book book){
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			String query = "delete from book "
					+ "where id = ?";
			st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, book.getId());
			
			//executeUpdate is used to run insert, update, and delete statements
			st.executeUpdate();
			logger.info("Book with id = " + book.getId() + " deleted from database.");
		} catch (SQLException e) {
			logger.error("Error deleting from author table in database: " + e.getMessage());
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(st != null)
					st.close();
			} catch (SQLException e) {
				logger.error("Statement or Result Set close error: " + e.getMessage());
			}
		}
	}
	
	public List<Book> searchBooks(String searchTitle) throws SQLException, GatewayException {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Book> books = new ArrayList<Book>();
		searchTitle = "%" + searchTitle + "%";
		
		try {
			st = conn.prepareStatement("SELECT * FROM bookTable WHERE title LIKE ?");
			st.setString(1, searchTitle);
			rs = st.executeQuery();
			
			while(rs.next()) {
				books.add(new Book(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("summary"),
						rs.getInt("year_published"),
						rs.getString("isbn"),
						rs.getDate("date_added").toLocalDate(),
						new PublisherGateway().getPublisherById(rs.getInt("publisher_id"))));
			}
		} catch (SQLException e) {
			logger.info("try/catch SQLException in searchBooks(");
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(st != null)
					st.close();
			} catch (SQLException e) {
				logger.info("try/catch/finally SQLException in searchBooks()");
			}
		}
		
		return books;
	}
	
	
	//close the conenction to the db
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

