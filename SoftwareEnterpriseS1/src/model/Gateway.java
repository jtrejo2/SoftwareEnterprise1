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
	
	public Gateway() throws GatewayException{
		conn = null;
		
		Properties props = new Properties();
		FileInputStream fis = null;
		try{
			fis = new FileInputStream("db.properties");
			props.load(fis);
			fis.close();
			
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
	
	public void updateAuthor(Author modelauthor){
		PreparedStatement st = null;
		
		try{
			st = conn.prepareStatement("update author set first_name = '" + modelauthor.getFirst_name() + "',"
					+ "last_name = '" + modelauthor.getLast_name() + "',"
					+ "dob = '" + modelauthor.getDob() + "',"
					+ "gender = '" + modelauthor.getGender() + "',"
					+ "web_site = '" + modelauthor.getWeb_site() + "'"
					+ "where id = '" + modelauthor.getId() + "'");
			
			st.executeUpdate();
			
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try{
				if(st != null)
					st.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public void insertAuthor(Author newguy) throws SQLException{
		PreparedStatement st = null;
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
				newguy.setId(rs.getInt(1));
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
		}
	}
	
	public void authorDelete(Author badauthor){
		PreparedStatement st = null;
		try{
		st = conn.prepareStatement("delete from author where id = '" + badauthor.getId() + "'");
		st.executeUpdate();
		logger.error("The delete button was clicked");
		}catch(SQLException e){
			e.printStackTrace();
		}
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

