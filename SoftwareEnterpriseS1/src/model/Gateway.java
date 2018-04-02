package model;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Gateway {
	private Connection conn;
	private static Logger logger = LogManager.getLogger();
	
	public List<Author> getAuthor(){
		List<Author> authors = new ArrayList<Author>();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			st = conn.prepareStatement("Select * from author");
			rs = st.executeQuery();
			
			while(rs.next()){
				Author author = new Author(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("dob"), rs.getString("gender"), rs.getString("web_site"));
				authors.add(author);
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
		
		return authors;
	}
	
	public Author getAuthorById(int id) throws GatewayException {
		PreparedStatement st = null;
		Author author = null;
		
		try {
			st = conn.prepareStatement("select * from author where id = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				author = new Author(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("dob"), rs.getString("gender"), rs.getString("web_site"));
				//author.setGateway(this);
				author.setId(rs.getInt("id"));
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
		return author;
	}
	
	public Gateway() throws GatewayException{
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
	//update the author in the db
	public void updateAuthor(Author modelauthor){
		PreparedStatement st = null;
		PreparedStatement st1 = null;
		PreparedStatement st2 = null;
		Author author = null;
		
		try{
			st = conn.prepareStatement("update author set first_name = '" + modelauthor.getFirst_name() + "',"
					+ "last_name = '" + modelauthor.getLast_name() + "',"
					+ "dob = '" + modelauthor.getDob() + "',"
					+ "gender = '" + modelauthor.getGender() + "',"
					+ "web_site = '" + modelauthor.getWeb_site() + "'"
					+ "where id = '" + modelauthor.getId() + "'");
			st1 = conn.prepareStatement("Select * from author where id = '" + modelauthor.getId() + "'",PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = st1.executeQuery();
			rs.beforeFirst();
			
			while (rs.next()) {
				// create a book object from the record
				//System.out.println("in the while");
			    author = new Author(
						rs.getInt("id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("dob"),
						rs.getString("gender"),
						rs.getString("web_site"));
						//new PublisherGateway().getPublisherById(rs.getInt("publisher_id")));
				       System.out.println("duh duh" + author.getFirst_name());
				       System.out.println("duh duh2 " + modelauthor.getFirst_name());
			   }
			if(! author.getFirst_name().equals(modelauthor.getFirst_name())) {
			System.out.println("here we are titles should be different " + author.getFirst_name() + " " + modelauthor.getFirst_name() );
			
			st2 = conn.prepareStatement("insert into author_audit_trail(entry_msg, author_id ) values(?,?) ");
			//st2.setInt(1, 1);
			System.out.println("duh duh2 " + modelauthor.getFirst_name());
			st2.setString(1, "Author first name changed from " + author.getFirst_name() + " to " + modelauthor.getFirst_name() + "");
			st2.setInt(2,modelauthor.getId());
			st2.executeUpdate();
			st2.close();
			}
			if(! author.getLast_name().equals(modelauthor.getLast_name())) {
				System.out.println("here we are titles should be different " + author.getLast_name() + " " + modelauthor.getLast_name() );
				
				st2 = conn.prepareStatement("insert into author_audit_trail(entry_msg, author_id ) values(?,?) ");
				//st2.setInt(1, 1);
				System.out.println("duh duh2 " + modelauthor.getLast_name());
				st2.setString(1, "Author first name changed from " + author.getLast_name() + " to " + modelauthor.getLast_name() + "");
				st2.setInt(2,modelauthor.getId());
				st2.executeUpdate();
				st2.close();
				}
			if(! author.getDob().equals(modelauthor.getDob())) {
				System.out.println("here we are titles should be different " + author.getDob() + " " + modelauthor.getDob());
				
				st2 = conn.prepareStatement("insert into author_audit_trail(entry_msg, author_id ) values(?,?) ");
				//st2.setInt(1, 1);
				System.out.println("duh duh2 " + modelauthor.getDob());
				st2.setString(1, "Author last name changed from " + author.getDob()+ " to " + modelauthor.getDob()+ "");
				st2.setInt(2,modelauthor.getId());
				st2.executeUpdate();
				st2.close();
				}
			if(! author.getGender().equals(modelauthor.getGender())) {
				System.out.println("here we are titles should be different " + author.getGender()+ " " + modelauthor.getGender() );
				
				st2 = conn.prepareStatement("insert into author_audit_trail(entry_msg, author_id ) values(?,?) ");
				//st2.setInt(1, 1);
				System.out.println("duh duh2 " + modelauthor.getGender());
				st2.setString(1, "Author gender changed from " + author.getGender()+ " to " + modelauthor.getGender()+ "");
				st2.setInt(2,modelauthor.getId());
				st2.executeUpdate();
				st2.close();
				}
			if(! author.getWeb_site().equals(modelauthor.getWeb_site())) {
				System.out.println("here we are titles should be different " + author.getWeb_site() + " " + modelauthor.getWeb_site() );
				
				st2 = conn.prepareStatement("insert into author_audit_trail(entry_msg, author_id ) values(?,?) ");
				//st2.setInt(1, 1);
				System.out.println("duh duh2 " + modelauthor.getWeb_site());
				st2.setString(1, "Author website changed from " + author.getWeb_site()+ " to " + modelauthor.getWeb_site() + "");
				st2.setInt(2,modelauthor.getId());
				st2.executeUpdate();
				st2.close();
				}
			st.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try{
				if(st != null) {
					st.close(); 
				}
				if(st2 != null) {
					st2.close();
				}
				if (st != null) {
					st.close();
				}
			}
				catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public List<AuthorAuditTrail> GetAuthorAuditTrail(Author author){
		List<AuthorAuditTrail> AuditTrails = new ArrayList<AuthorAuditTrail>();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		
		 try{
			st = conn.prepareStatement("Select * from author_audit_trail where author_id = '" + author.getId() + "'");
			rs = st.executeQuery();
			System.out.println("this is rs" + rs);
			
			while(rs.next()){
				
				
					// create a book object from the record
					//System.out.println("in the while23");
				    AuthorAuditTrail AuditTrail = new AuthorAuditTrail();
				    AuditTrail.setId(rs.getInt("id"));
				    //rs.next();
				    AuditTrail.setAuthorID(rs.getInt("author_id"));
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
	//insert an author in the db and add audit trail to author audit trail
	public void insertAuthor(Author newguy) throws SQLException{
		PreparedStatement st = null;
		PreparedStatement st2 = null;
		ResultSet rs = null;
		
		try{
			st = conn.prepareStatement("insert into author (first_name, last_name, dob, gender, web_site) values (?, ?, ?, ?, ?)", st.RETURN_GENERATED_KEYS);
			logger.error(newguy.getDob());
			st.setString(1, newguy.getFirst_name());
			st.setString(2, newguy.getLast_name());
			st.setString(3, newguy.getDob());
			st.setString(4, newguy.getGender());
			st.setString(5, newguy.getWeb_site());
			st.executeUpdate();
			rs = st.getGeneratedKeys();
			if(rs != null && rs.next()){
				st2 = conn.prepareStatement("insert into author_audit_trail( entry_msg, author_id ) values(?,?) ");
				newguy.setId(rs.getInt(1));
				st2.setInt(2, rs.getInt(1));
				st2.setString(1, "Author Added");
				st2.executeUpdate();
			}
			
		}catch(SQLException e){
			logger.error("The insert has failed");
			e.printStackTrace();
		}finally{
			if(rs != null){
				rs.close();
			}
			if(st != null){
				st.close();
			}
			if(st2 != null) {
				st2.close();
			}
			}
	}
	
	public LocalDateTime getTimeStamp(Author author) {
		LocalDateTime time = null;
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("select * from author where id = ?");
			st.setInt(1, author.getId());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				time = rs.getTimestamp("last_modified").toLocalDateTime();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return time;
	}
	
	//delete an author from the db
	public void authorDelete(Author badauthor){
		PreparedStatement st = null;
			try{
				st = conn.prepareStatement("delete from author where id = '" + badauthor.getId() + "'");
				st.executeUpdate();
				logger.error("delete button clicked");
		}catch(SQLException e){
			e.printStackTrace();
		}
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

