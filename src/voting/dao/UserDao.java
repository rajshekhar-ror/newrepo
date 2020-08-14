package voting.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;

import voting.pojo.UserDetails;
import voting.pojo.UserPojo;
import voting.utility.*;

public class UserDao {

	private static PreparedStatement ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9;
	static {
		try {
			ps1 = DBConnection.getConnection()
					.prepareStatement("select * from users where userid = ? and password = ?");
			ps2 = DBConnection.getConnection().prepareStatement("update users set photo = ? where userid = ?");
			ps3 = DBConnection.getConnection().prepareStatement("select photo from users where userid = ?");
			ps4 = DBConnection.getConnection().prepareStatement("select * from users where userid = ?");
			ps5 = DBConnection.getConnection().prepareStatement("select * from users where user_type = 'voter'");
			ps6 = DBConnection.getConnection()
					.prepareStatement("select * from users where userid = ? or username = ? and user_type = 'voter'");
			ps7 = DBConnection.getConnection().prepareStatement("update users set active = ? where userid = ?");
			ps8 = DBConnection.getConnection().prepareStatement("select password from users where userid = ?");
			ps9 = DBConnection.getConnection().prepareStatement("update users set password = ? where userid = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String validateUser(UserPojo user) throws SQLException {
		ps1.setString(1, user.getUserId());
		ps1.setString(2, user.getPassword());
		ResultSet rs = ps1.executeQuery();
		if (rs.next()) {
			if (rs.getString(8).equalsIgnoreCase("y")) {
				return rs.getString(7);
			} else {
				return "blocked";
			}

		} else {
			return null;
		}
	}

	public static boolean changePhoto(String userId, InputStream photo) throws SQLException, IOException {
		ps2.setBinaryStream(1, photo, photo.available());
		ps2.setString(2, userId);
		return ps2.executeUpdate() != 0;
	}

	public static Blob getPhoto(String userid) throws SQLException {
		ps3.setString(1, userid);
		ResultSet rs = ps3.executeQuery();
		if (rs.next()) {
			return rs.getBlob(1);
		}
		return null;
	}

	public static UserDetails getDetails(String userid) throws SQLException {
		ps4.setString(1, userid);
		ResultSet rs = ps4.executeQuery();
		UserDetails user = null;
		if (rs.next()) {
			user = new UserDetails();
			user.setUserId(rs.getString("userid"));
			user.setUserName(rs.getString("username"));
			user.setBranch(rs.getString("branch"));
			user.setYear(rs.getInt("year"));
			user.setActive(rs.getString("active"));
			Blob blob = (Blob) rs.getBlob("photo");
			String base64Image = "";
			if (blob != null) {
				byte[] imageBytes = blob.getBytes(1L, (int) blob.length());
				Encoder ec = Base64.getEncoder();
				base64Image = ec.encodeToString(imageBytes);
			}
			user.setPhoto(base64Image);
		}
		return user;
	}

	public static ArrayList<UserDetails> getUsers() throws SQLException {
		ArrayList<UserDetails> users = new ArrayList<UserDetails>();
		ResultSet rs = ps5.executeQuery();
		UserDetails user = null;
		while (rs.next()) {
			user = new UserDetails();
			user.setUserId(rs.getString("userid"));
			user.setUserName(rs.getString("username"));
			user.setBranch(rs.getString("branch"));
			user.setYear(rs.getInt("year"));
			user.setActive(rs.getString("active"));
			Blob blob = (Blob) rs.getBlob("photo");
			String base64Image = "";
			if (blob != null) {
				byte[] imageBytes = blob.getBytes(1L, (int) blob.length());
				Encoder ec = Base64.getEncoder();
				base64Image = ec.encodeToString(imageBytes);
			}
			user.setPhoto(base64Image);
			users.add(user);
		}
		return users;
	}

	public static ArrayList<UserDetails> searchUsers(String userId, String userName) throws SQLException {
		ArrayList<UserDetails> users = new ArrayList<UserDetails>();
		ps6.setString(1, userId);
		ps6.setString(2, userName);
		ResultSet rs = ps6.executeQuery();
		UserDetails user = null;
		while (rs.next()) {
			user = new UserDetails();
			user.setUserId(rs.getString("userid"));
			user.setUserName(rs.getString("username"));
			user.setBranch(rs.getString("branch"));
			user.setYear(rs.getInt("year"));
			user.setActive(rs.getString("active"));
			Blob blob = (Blob) rs.getBlob("photo");
			String base64Image = "";
			if (blob != null) {
				byte[] imageBytes = blob.getBytes(1L, (int) blob.length());
				Encoder ec = Base64.getEncoder();
				base64Image = ec.encodeToString(imageBytes);
			}
			user.setPhoto(base64Image);
			users.add(user);
		}
		return users;
	}

	public static boolean updateUser(String userId, String active) throws SQLException {
		ps7.setString(1, active);
		ps7.setString(2, userId);
		return ps7.executeUpdate() > 0;
	}

	public static String getPassword(String userid) throws SQLException {
		ps8.setString(1, userid);
		ResultSet rs = ps8.executeQuery();
		if (rs.next()) {
			return rs.getString(1);
		}
		return "";
	}

	public static boolean setPassword(String userid, String password) throws SQLException {
		ps9.setString(1, password);
		ps9.setString(2, userid);
		return ps9.executeUpdate() > 0;
	}
}
