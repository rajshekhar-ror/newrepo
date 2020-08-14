package voting.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import voting.pojo.Election;
import voting.utility.DBConnection;

public class ElectionDao {
	private static PreparedStatement ps1, ps2, ps3, ps5, ps6, ps7, ps8, ps9, ps10, ps11;
	static {
		try {
			ps1 = DBConnection.getConnection()
					.prepareStatement("select * from election where election_name = ? and status < 4");
			ps2 = DBConnection.getConnection().prepareStatement("insert into election values(?, ?, ?)");
			ps3 = DBConnection.getConnection().prepareStatement("select * from election where status < 4");
			ps5 = DBConnection.getConnection()
					.prepareStatement("select election_id from election ORDER BY election_id DESC LIMIT 1");
			ps6 = DBConnection.getConnection().prepareStatement("delete from election where election_id = ?");
			ps8 = DBConnection.getConnection().prepareStatement("delete from votes where election_id = ?");
			ps7 = DBConnection.getConnection().prepareStatement("delete from candidates where election_id = ?");
			ps9 = DBConnection.getConnection().prepareStatement("update election set status = ? where election_id = ?");
			ps10 = DBConnection.getConnection().prepareStatement("select * from election where status = ?");
			ps11 = DBConnection.getConnection()
					.prepareStatement("select election_name from election where election_id = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean checkDup(String election) throws SQLException {
		ps1.setString(1, election);
		return ps1.executeQuery().next();
	}

	public static String getElectionId() throws SQLException {
		ResultSet rs = ps5.executeQuery();
		if (rs.next()) {
			String key = rs.getString("election_id");
			int id = Integer.parseInt(key.substring(1));
			return "E" + (id + 1);
		}
		return "E1001";
	}

	public static boolean addElection(Election election) throws SQLException {
		ps2.setString(1, election.getElectionId());
		ps2.setString(2, election.getElectionName());
		ps2.setInt(3, election.getStatus());
		return ps2.executeUpdate() > 0;
	}

	public static ArrayList<Election> searchElection(String electionName) throws SQLException {
		ps1.setString(1, electionName);
		Election election = null;
		ResultSet rs = ps1.executeQuery();
		ArrayList<Election> elections = new ArrayList<Election>();
		while (rs.next()) {
			election = new Election();
			election.setElectionId(rs.getString(1));
			election.setElectionName(rs.getString(2));
			election.setStatus(rs.getInt(3));
			elections.add(election);
		}
		return elections;
	}

	public static ArrayList<Election> getElection() throws SQLException {
		ArrayList<Election> elections = new ArrayList<Election>();
		Election election = null;
		ResultSet rs = ps3.executeQuery();
		while (rs.next()) {
			election = new Election();
			election.setElectionId(rs.getString(1));
			election.setElectionName(rs.getString(2));
			election.setStatus(rs.getInt(3));
			elections.add(election);
		}
		return elections;
	}

	public static boolean deleteElection(String electionId) throws SQLException {
		ps6.setString(1, electionId);
		ps7.setString(1, electionId);
		ps8.setString(1, electionId);
		ps8.executeUpdate();
		ps7.executeUpdate();
		return ps6.executeUpdate() > 0;
	}

	public static boolean updateElection(String electionId, int status) throws SQLException {
		ps9.setInt(1, status);
		ps9.setString(2, electionId);
		return ps9.executeUpdate() > 0;
	}

	public static ArrayList<Election> getElectionForCondition(int status) throws SQLException {
		ArrayList<Election> elections = new ArrayList<Election>();
		Election election = null;
		ps10.setInt(1, status);
		ResultSet rs = ps10.executeQuery();
		while (rs.next()) {
			election = new Election();
			election.setElectionId(rs.getString(1));
			election.setElectionName(rs.getString(2));
			election.setStatus(rs.getInt(3));
			elections.add(election);
		}
		return elections;
	}

	public static String getElectionName(String electionId) throws SQLException {
		ps11.setString(1, electionId);
		ResultSet rs = ps11.executeQuery();
		if (rs.next()) {
			return rs.getString(1);
		}
		return null;
	}
}
