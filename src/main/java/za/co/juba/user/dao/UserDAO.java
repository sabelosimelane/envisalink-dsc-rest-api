package za.co.juba.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import com.concept.dbtools.DBAccessor;
import com.concept.mvc.exception.ServiceException;

import za.co.juba.user.domain.User;
import za.co.juba.user.domain.UserDraft;
/**
 * 
 * @author Sabside
 *
 */
public class UserDAO {

	private @Inject DBAccessor dbAccessor;
	
	public void updateLastlogin(User user) throws Exception {
		Connection conn = null;
		try {
			conn = dbAccessor.connect();
			PreparedStatement stmt = conn.prepareStatement("update users set lastlogin=now() where id = ?");
			stmt.setInt(1, user.getId());
			stmt.executeUpdate();
		} finally {
			dbAccessor.disconnect(conn);
		}
	}
	
	public void updatePassword(User user, String password) throws Exception {
		Connection conn = null;
		try {
			conn = dbAccessor.connect();
			PreparedStatement stmt = conn.prepareStatement("update users set password=? where id = ?");
			stmt.setString(1, password);
			stmt.setInt(2, user.getId());
			stmt.executeUpdate();
		} finally {
			dbAccessor.disconnect(conn);
		}
	}
	
	public User update(User user, UserDraft draft) throws Exception {
		Connection conn = null;
		try {
			conn = dbAccessor.connect();
			PreparedStatement stmt = conn.prepareStatement("update users set name=?, email=?, cellphone=?, role=? where id = ?");
			stmt.setString(1, draft.getName());
			stmt.setString(2, draft.getEmail());
			stmt.setString(3, draft.getCellphone());
			stmt.setInt(4, draft.getRole());
			stmt.setInt(5, user.getId());
			stmt.executeUpdate();
		} catch (Exception e) {
			if (e.getMessage().contains("already exists")) {
				throw new ServiceException("Email address already exists. Use a different one.");
			}
		} finally {
			dbAccessor.disconnect(conn);
		}
		
		return fetch(user.getId()).get();
	}
	
	public User create(UserDraft draft) throws Exception {
		int id = -1;
		Connection conn = null;
		try {
			conn = dbAccessor.connect();
			PreparedStatement stmt = conn.prepareStatement("insert into users (name, email, cellphone, role, created) values (?, ?, ?, ?, now())", PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, draft.getName());
			stmt.setString(2, draft.getEmail());
			stmt.setString(3, draft.getCellphone());
			stmt.setInt(4, draft.getRole());
			stmt.executeUpdate();
			id = dbAccessor.getGenerateKey(stmt, "id");
			
		} catch (Exception e) {
			if (e.getMessage().contains("already exists")) {
				throw new ServiceException("Email address already exists. Use a different one.");
			} else {
				throw e;
			}
		} finally {
			dbAccessor.disconnect(conn);
		}	
		return fetch(id).get();
	}
	
	public List<User> fetch() throws Exception {
		List<User> users = new ArrayList<>();
		Connection conn = null;
		try {
			conn = dbAccessor.connect();
			PreparedStatement stmt = conn.prepareStatement("select * from users where role <>1 order by created desc");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				users.add(build(rs));
			}
			
		} finally {
			dbAccessor.disconnect(conn);
		}	
		return users;
	}
	
	public Optional<User> fetch(int id) throws Exception {
		Connection conn = null;
		try {
			conn = dbAccessor.connect();
			PreparedStatement stmt = conn.prepareStatement("select * from users where id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return Optional.ofNullable(build(rs));
			}
		} finally {
			dbAccessor.disconnect(conn);
		}
		return Optional.empty();
	}
	
	public Optional<User> fetch(String email) throws Exception {
		Connection conn = null;
		try {
			conn = dbAccessor.connect();
			PreparedStatement stmt = conn.prepareStatement("select * from users where lower(email) = lower(?)");
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return Optional.ofNullable(build(rs));
			}
		} finally {
			dbAccessor.disconnect(conn);
		}
		return Optional.empty();
	}
	
	public Optional<User> fetch(String email, boolean active) throws Exception {
		Connection conn = null;
		try {
			conn = dbAccessor.connect();
			PreparedStatement stmt = conn.prepareStatement("select * from users where lower(email) = lower(?) and active is true");
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return Optional.ofNullable(build(rs));
			}
		} finally {
			dbAccessor.disconnect(conn);
		}
		return Optional.empty();
	}
	
	private User build(ResultSet rs) throws Exception {
		return new User(
				rs.getInt("id"),
				rs.getString("name"), 
				rs.getString("email"), 
				rs.getString("cellphone"),
				rs.getBoolean("active"), 
				rs.getString("password"),
				rs.getInt("role"),
				rs.getTimestamp("lastlogin")==null?null:new Date(rs.getTimestamp("lastlogin").getTime())
				);
	}
}
