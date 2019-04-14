package za.co.juba.properties.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.inject.Inject;

import com.concept.dbtools.DBAccessor;

/**
 * 
 * @author Sabside
 *
 */
public class PropertiesDAO implements com.concept.mvc.properties.PropertiesDAO {

	@Inject DBAccessor dbAccessor;
	
	@Override
	public Properties fetch(String filename) throws Exception {
		Connection conn = null;
		try {
			conn = dbAccessor.connect();
			PreparedStatement stmt = conn.prepareStatement("select * from properties where filename = ?");
			stmt.setString(1, filename);
			ResultSet rs = stmt.executeQuery();
			return build(rs);
			
		} finally {
			dbAccessor.disconnect(conn);
		}
	}
	
	private Properties build(ResultSet rs) throws Exception {
		Properties props = new Properties();
		while (rs.next()) {
			props.setProperty(rs.getString("propkey"), rs.getString("propvalue"));
		}
		return props;
	}

}
