package za.co.juba.user.dao;
/**
 * 
 * @author Sabside
 *
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Optional;

import javax.inject.Inject;

import com.concept.dbtools.DBAccessor;

public class AccessTokenDAO {

	private @Inject DBAccessor dbAccessor;
	
	public void delete(int userId) throws Exception {
		Connection conn = null;
		try {
			conn = dbAccessor.connect();
			PreparedStatement stmt = conn.prepareStatement("delete from accesstoken where userid = ?");
			stmt.setInt(1, userId);
			
			stmt.executeUpdate();
			
		} finally {
			dbAccessor.disconnect(conn);
		}
	}
	
	public void save(int userId, String token) throws Exception {
		Connection conn = null;
		try {
			conn = dbAccessor.connect();
			PreparedStatement stmt = conn.prepareStatement("insert into accesstoken (userid, token, created) values (?, ?, now())");
			stmt.setInt(1, userId);
			stmt.setString(2, token);
			
			stmt.executeUpdate();
			
		} finally {
			dbAccessor.disconnect(conn);
		}
	}
	
	public Optional<Integer> fetch(String token) throws Exception {
		Connection conn = null;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -2);
		
		try {
			conn = dbAccessor.connect();
			PreparedStatement stmt = conn.prepareStatement("select * from accesstoken where token = ? and created > ? order by created desc limit 1");
			stmt.setString(1, token);
			stmt.setTimestamp(2, new Timestamp(cal.getTimeInMillis()));
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return Optional.ofNullable(rs.getInt("userid"));
			}
			
		} finally {
			dbAccessor.disconnect(conn);
		}
		return Optional.empty();
	}
	
	public Optional<String> fetch(int tokenId) throws Exception {
		Connection conn = null;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -2);
		
		try {
			conn = dbAccessor.connect();
			PreparedStatement stmt = conn.prepareStatement("select * from accesstoken where id = ? and created > ? order by created desc limit 1");
			stmt.setInt(1, tokenId);
			stmt.setTimestamp(2, new Timestamp(cal.getTimeInMillis()));
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return Optional.ofNullable(rs.getString("token"));
			}
			
		} finally {
			dbAccessor.disconnect(conn);
		}
		return Optional.empty();
	}
	
	public Optional<String> find(int userId) throws Exception {
		Connection conn = null;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -2);
		
		try {
			conn = dbAccessor.connect();
			PreparedStatement stmt = conn.prepareStatement("select * from accesstoken where userid = ? and created > ? order by created desc limit 1");
			stmt.setInt(1, userId);
			stmt.setTimestamp(2, new Timestamp(cal.getTimeInMillis()));
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return Optional.ofNullable(rs.getString("token"));
			}
			
		} finally {
			dbAccessor.disconnect(conn);
		}
		return Optional.empty();
	}
}
