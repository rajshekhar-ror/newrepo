package voting.dao;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;

import voting.pojo.ResultCard;
import voting.pojo.Vote;
import voting.utility.DBConnection;

public class VoteDao {
	private static PreparedStatement ps1, ps2, ps3, ps4;
	static {
		try {
			ps1 = DBConnection.getConnection()
					.prepareStatement("select * from votes where election_id = ? and userid = ?");
			ps2 = DBConnection.getConnection().prepareStatement("insert into votes values(?, ?, ?)");
			ps3 = DBConnection.getConnection().prepareStatement(
					"SELECT candidates.candidateid, candidates.userid, username, branch, year, symbol, count(*) FROM voting.votes join candidates on candidates.candidateid = votes.candidateid join users on candidates.userid = users.userid where candidates.election_id = ? group by votes.candidateid order by count(*) desc");
			ps4 = DBConnection.getConnection().prepareStatement("select count(*) from votes where election_id = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String verifyVote(Vote vote) throws SQLException {
		ps1.setString(1, vote.getElectionId());
		ps1.setString(2, vote.getUserId());
		ResultSet rs = ps1.executeQuery();
		if (rs.next()) {
			return rs.getString("candidateid");
		}
		return null;
	}

	public static boolean addVote(Vote vote) throws SQLException {
		ps2.setString(1, vote.getElectionId());
		ps2.setString(2, vote.getCandidateId());
		ps2.setString(3, vote.getUserId());
		return ps2.executeUpdate() > 0;
	}

	public static ArrayList<ResultCard> getResult(String electionId) throws SQLException {
		ps3.setString(1, electionId);
		ResultSet rs = ps3.executeQuery();
		ArrayList<ResultCard> results = new ArrayList<ResultCard>();
		while (rs.next()) {
			ResultCard rc = new ResultCard();
			rc.setCandidateId(rs.getString(1));
			rc.setUserId(rs.getString(2));
			rc.setUserName(rs.getString(3));
			rc.setBranch(rs.getString(4));
			rc.setYear(rs.getString(5));
			Blob blob = (Blob) rs.getBlob(6);
			String base64Image = "";
			byte[] imageBytes = blob.getBytes(1L, (int) blob.length());
			Encoder ec = Base64.getEncoder();
			base64Image = ec.encodeToString(imageBytes);
			rc.setSymbol("data:image/jpg;base64," + base64Image);
			rc.setCount(rs.getInt(7));
			results.add(rc);
		}
		return results;
	}

	public static int getCount(String electionId) throws SQLException {
		ps4.setString(1, electionId);
		ResultSet rs = ps4.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 1;
	}
}
