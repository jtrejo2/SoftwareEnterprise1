package model;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
	public void updateBook(Book updated) throws GatewayException {
		PreparedStatement st = null;
		PreparedStatement st1 = null;
		PreparedStatement st2 = null;
		Book book = null;
		try {
			st = conn.prepareStatement("update book set title = '" + updated.getTitle() + "'," 
				+ "summary = '"+ updated.getSummary() + "'," 
				+ "year_published = '" + updated.getYearPublished() + "',"
				+ "publisher_id = '" + updated.getPublisherId() 
				+ "'," + "isbn = '" + updated.getIsbn() + "'"
				+ "where id = '" + updated.getId() + "'");
			st1 = conn.prepareStatement("Select * from book where id = '" + updated.getId() + "'",PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = st1.executeQuery();
			System.out.println(st1);
			System.out.println("here we are" + rs);
			
			//ResultSet rs = testing ;//st1.getGeneratedKeys();
			rs.beforeFirst();
			
			while (rs.next()) {
				// create a book object from the record
				System.out.println("in the while");
			    book = new Book(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("summary"),
						rs.getInt("year_published"),
						rs.getString("isbn"),
						rs.getDate("date_added").toLocalDate(),
						new PublisherGateway().getPublisherById(rs.getInt("publisher_id")));
				       System.out.println("duh duh" + book.getTitle());
			   }
			if( book.getTitle() != updated.getTitle()){
				
				st2 = conn.prepareStatement("insert into book_audit_trail( entry_msg, book_id ) values(?,?) ");
				//st2.setInt(1, 1);
				st2.setString(1, "Book Title changed from " + book.getTitle() + " to " + updated.getTitle() + "");
				st2.setInt(2,updated.getId());
				st2.executeUpdate();
				st2.close();
				
			}
			if( book.getSummary() != updated.getSummary()){
				
				st2 = conn.prepareStatement("insert into book_audit_trail( entry_msg, book_id ) values(?,?) ");
				//st2.setInt(1, 1);
				st2.setString(1, "Summary changed from " + book.getSummary() + " to " + updated.getSummary() + "");
				st2.setInt(2,updated.getId());
				st2.executeUpdate();
				st2.close();
			}
			if( book.getYearPublished() != updated.getYearPublished()){
				
				st2 = conn.prepareStatement("insert into book_audit_trail( entry_msg, book_id ) values(?,?) ");
				//st2.setInt(1, 1);
				st2.setString(1, "Year Published changed from " + book.getYearPublished() + " to " + updated.getYearPublished() + "");
				st2.setInt(2,updated.getId());
				st2.executeUpdate();
				st2.close();
			}
			if( book.getIsbn() != updated.getIsbn()){
				
				st2 = conn.prepareStatement("insert into book_audit_trail( entry_msg, book_id ) values(?,?) ");
				//st2.setInt(1, 1);
				st2.setString(1, "ISBN changed from " + book.getIsbn() + " to " + updated.getIsbn() + "");
				st2.setInt(2,updated.getId());
				st2.executeUpdate();
				st2.close();
			}
			if( book.getPublisher() != updated.getPublisher()){
				
				st2 = conn.prepareStatement("insert into book_audit_trail( entry_msg, book_id ) values(?,?) ");
				//st2.setInt(1, 1);
				st2.setString(1, "Publisher changed from " + book.getPublisher() + " to " + updated.getPublisher()+ "");
				st2.setInt(2,updated.getId());
				st2.executeUpdate();
				st2.close();
			}
			//System.out.println(rs.getInt(1));
			//System.out.println(book);

			//st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(st1 != null) {
					st1.close();
					
				}
				if(st2 != null) {
					st2.close();
				}
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//insert an book in the db
	public void insertBook (Book book) throws GatewayException {
		PreparedStatement st = null;
		PreparedStatement st2 = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("insert into book( title, summary, year_published, isbn, publisher_id ) values( ?, ?, ?, ?, ? )", PreparedStatement.RETURN_GENERATED_KEYS);
			st.setString(1, book.getTitle());
			st.setString(2, book.getSummary());
			st.setInt(3, book.getYearPublished());
			st.setString(4, book.getIsbn());
			st.setInt(5, book.getPublisherId());
			
			
			
			
			
			st2 = conn.prepareStatement("insert into book_audit_trail( entry_msg, book_id ) values(?,?) ");
			//st2.setInt(1, 1);
			st2.setString(1, "Book Added");
			System.out.println(st2);
			//st2.executeUpdate();
			
			st.executeUpdate();
			rs = st.getGeneratedKeys();
			rs.first();
			System.out.println("this is the int" + rs.getInt(1));
			System.out.println(rs.first());
			st2.setInt(2, rs.getInt(1));
			st2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GatewayException(e);
		} finally {
			try {
				if(rs != null)
					rs.close();
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
	
	public List<Book> searchBooks(String search) throws GatewayException {
		List<Book> books = new ArrayList<Book>();
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * from book WHERE title LIKE '%" + search + "%'");
			rs = st.executeQuery();

			while (rs.next()) {
				// create an author object from the record
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
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return books;
	}
	public List<AuditTrail> GetBookAuditTrail(Book book){
		List<AuditTrail> AuditTrails = new ArrayList<AuditTrail>();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		
		 try{
			st = conn.prepareStatement("Select * from book_audit_trail where book_id = '" + book.getId() + "'");
			rs = st.executeQuery();
			System.out.println("this is rs" + rs);
			
			while(rs.next()){
				
				
					// create a book object from the record
					System.out.println("in the while23");
				    AuditTrail AuditTrail = new AuditTrail();
				    AuditTrail.setId(rs.getInt("id"));
				    //rs.next();
				    AuditTrail.setBookId(rs.getInt("book_id"));
				    AuditTrail.setDateAdded(rs.getTimestamp("date_added"));
				    //System.out.println("this is the time stamp" + rs.getTimestamp("date_added"));
				    AuditTrail.setMessage(rs.getString("entry_msg"));
				    AuditTrails.add(AuditTrail);
				    //System.out.println("duh duh" + book.getTitle() + book.getId() + book.getDateAdded() + book.getSummary());
				    //System.out.println("this is the time stamp" + rs.getTimestamp("date_added"));
				    //System.out.println("this is the Audit" + AuditTrail.getDateAdded());
				    
			}	    //System.out.println("this is the Audit");
					//System.out.println("duh duh" + book.getTitle() + book.getId() +book.getBookId() + book.getDateAdded() + book.getSummary());
				 /*Book book = new Book(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("summary"),
						rs.getInt("year_published"),
						rs.getString("isbn"), 
						rs.getDate("date_added").toLocalDate(),
						new PublisherGateway().getPublisherById(rs.getInt("publisher_id")));
				books.add(book);*/
				
			//}
			//for(AuditTrail a: AuditTrails)
				
			//	System.out.println("duh duh" + a);
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
		
		//System.out.println("Audit Trails" + AuditTrails);
		//AuditTrails =  new ArrayList<AuditTrail>();
		return AuditTrails;
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
