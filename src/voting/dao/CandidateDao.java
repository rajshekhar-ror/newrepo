package voting.dao;

import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;

import voting.pojo.AddCandidate;
import voting.pojo.Candidate;
import voting.pojo.CandidateDetails;
import voting.utility.DBConnection;

public class CandidateDao {
	private static PreparedStatement ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9, ps10, ps11, ps12, ps13;
	static {
		try {
			ps1 = DBConnection.getConnection()
					.prepareStatement("select candidateid from candidates ORDER BY candidateid DESC LIMIT 1");
			ps2 = DBConnection.getConnection().prepareStatement("select * from candidates join election on candidates.election_id = election.election_id where userid = ? and status = 1");
			ps3 = DBConnection.getConnection().prepareStatement(
					"SELECT candidateid, candidates.userid, candidates.election_id, username, branch, year, symbol, photo, candidates.active FROM voting.candidates join voting.users on candidates.userid = users.userid where candidates.candidateid = ?");
			ps4 = DBConnection.getConnection().prepareStatement("select * from candidates where candidateid = ?");
			ps5 = DBConnection.getConnection()
					.prepareStatement("select * from candidates where userid = ? and election_id = ?");
			ps6 = DBConnection.getConnection().prepareStatement("insert into candidates values(?, ?, ?, ?, ?)");
			ps7 = DBConnection.getConnection().prepareStatement("delete from candidates where candidateid = ?");
			ps8 = DBConnection.getConnection().prepareStatement("delete from votes where candidateid = ?");
			ps9 = DBConnection.getConnection()
					.prepareStatement("update candidates set active = ? where candidateid = ?");
			ps10 = DBConnection.getConnection().prepareStatement(
					"SELECT candidateid, candidates.userid, candidates.election_id, username, branch, year, symbol, photo, candidates.active FROM voting.candidates join voting.users on candidates.userid = users.userid join election on candidates.election_id = election.election_id where status < 4");
			ps11 = DBConnection.getConnection().prepareStatement(
					"SELECT candidateid, candidates.userid, candidates.election_id, username, branch, year, symbol, photo, candidates.active FROM voting.candidates join voting.users on candidates.userid = users.userid join election on candidates.election_id = election.election_id where status < 4 and username = ? or candidates.userid = ?");
			ps12 = DBConnection.getConnection().prepareStatement(
					"select * from candidates join election on candidates.election_id = election.election_id where status = ?");
			ps13 = DBConnection.getConnection().prepareStatement(
					"SELECT candidateid, candidates.userid, candidates.election_id, username, branch, year, symbol, photo, candidates.active FROM voting.candidates join voting.users on candidates.userid = users.userid where candidates.election_id = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getCandidateId() throws SQLException {
		ResultSet rs = ps1.executeQuery();
		if (rs.next()) {
			String key = rs.getString("candidateid");
			int id = Integer.parseInt(key.substring(1));
			return "C" + (id + 1);
		}
		return "C1001";
	}

	public static ArrayList<Candidate> getCandidate(String userid) throws SQLException {
		ArrayList<Candidate> candidates = new ArrayList<Candidate>();
		ps2.setString(1, userid);
		ResultSet rs = ps2.executeQuery();
		while (rs.next()) {
			Candidate candidate = new Candidate();
			candidate.setCandidateId(rs.getString(1));
			candidate.setUserID(rs.getString(2));
			candidate.setElectionId(rs.getString(3));
			Blob blob = (Blob) rs.getBlob(4);
			String base64Image = "";
			byte[] imageBytes = blob.getBytes(1L, (int) blob.length());
			Encoder ec = Base64.getEncoder();
			base64Image = ec.encodeToString(imageBytes);
			candidate.setSymbol(base64Image);
			candidate.setActive(rs.getString(5));
			candidates.add(candidate);
		}
		return candidates;
	}

	public static Candidate getCandidateById(String candidateId) throws SQLException {
		ps4.setString(1, candidateId);
		ResultSet rs = ps4.executeQuery();
		Candidate candidate = null;
		if (rs.next()) {
			candidate = new Candidate();
			candidate.setCandidateId(rs.getString(1));
			candidate.setUserID(rs.getString(2));
			candidate.setElectionId(rs.getString(3));
			Blob blob = (Blob) rs.getBlob(4);
			String base64Image = "";
			byte[] imageBytes = blob.getBytes(1L, (int) blob.length());
			Encoder ec = Base64.getEncoder();
			base64Image = ec.encodeToString(imageBytes);
			candidate.setSymbol(base64Image);
			candidate.setActive(rs.getString(5));
		}
		return candidate;
	}

	public static boolean checkDup(String userId, String electionId) throws SQLException {
		ps5.setString(1, userId);
		ps5.setString(2, electionId);
		return ps5.executeQuery().next();
	}

	public static boolean addCandidate(AddCandidate candidate) throws SQLException, IOException {
		ps6.setString(1, candidate.getCandidateId());
		ps6.setString(2, candidate.getUserId());
		ps6.setString(3, candidate.getElectionId());
		ps6.setBinaryStream(4, candidate.getSymbol(), candidate.getSymbol().available());
		ps6.setString(5, candidate.getActive());
		return ps6.executeUpdate() != 0;
	}

	public static boolean deleteCandidate(String candidateId) throws SQLException {
		ps8.setString(1, candidateId);
		ps7.setString(1, candidateId);
		ps8.executeUpdate();
		return ps7.executeUpdate() != 0;
	}

	public static boolean updateCandidate(String candidateId, String status) throws SQLException {
		ps9.setString(2, candidateId);
		ps9.setString(1, status);
		ps9.executeUpdate();
		return ps9.executeUpdate() != 0;
	}

	public static ArrayList<CandidateDetails> getCandidateDetails() throws SQLException {
		ArrayList<CandidateDetails> candidates = new ArrayList<CandidateDetails>();
		ResultSet rs = ps10.executeQuery();
		while (rs.next()) {
			CandidateDetails candidate = new CandidateDetails();
			candidate.setCandidateId(rs.getString(1));
			candidate.setUserId(rs.getString(2));
			;
			candidate.setElectionId(rs.getString(3));
			candidate.setUserName(rs.getString(4));
			candidate.setBranch(rs.getString(5));
			candidate.setYear(rs.getString(6));
			Blob blob = (Blob) rs.getBlob(7);
			String base64Image = "";
			byte[] imageBytes = blob.getBytes(1L, (int) blob.length());
			Encoder ec = Base64.getEncoder();
			base64Image = ec.encodeToString(imageBytes);
			candidate.setSymbol("data:image/jpg;base64," + base64Image);
			blob = (Blob) rs.getBlob(8);
			if (blob != null) {
				base64Image = "";
				imageBytes = blob.getBytes(1L, (int) blob.length());
				base64Image = ec.encodeToString(imageBytes);
				candidate.setPhoto("data:image/jpg;base64," + base64Image);
			} else {
				candidate.setPhoto("images/businessman-310819_640.png");
			}
			candidate.setActive(rs.getString(9));
			candidates.add(candidate);
		}
		return candidates;
	}

	public static ArrayList<CandidateDetails> searchCandidateDetailsById(String userId, String username)
			throws SQLException {
		ArrayList<CandidateDetails> candidates = new ArrayList<CandidateDetails>();
		ps11.setString(2, userId);
		ps11.setString(1, username);
		ResultSet rs = ps11.executeQuery();
		while (rs.next()) {
			CandidateDetails candidate = new CandidateDetails();
			candidate.setCandidateId(rs.getString(1));
			candidate.setUserId(rs.getString(2));
			candidate.setElectionId(rs.getString(3));
			candidate.setUserName(rs.getString(4));
			candidate.setBranch(rs.getString(5));
			candidate.setYear(rs.getString(6));
			Blob blob = (Blob) rs.getBlob(7);
			String base64Image = "";
			byte[] imageBytes = blob.getBytes(1L, (int) blob.length());
			Encoder ec = Base64.getEncoder();
			base64Image = ec.encodeToString(imageBytes);
			candidate.setSymbol("data:image/jpg;base64," + base64Image);
			blob = (Blob) rs.getBlob(8);
			if (blob != null) {
				base64Image = "";
				imageBytes = blob.getBytes(1L, (int) blob.length());
				base64Image = ec.encodeToString(imageBytes);
				candidate.setPhoto("data:image/jpg;base64," + base64Image);
			} else {
				candidate.setPhoto("images/businessman-310819_640.png");
			}
			candidate.setActive(rs.getString(9));
			candidates.add(candidate);
		}
		return candidates;
	}

	public static ArrayList<Candidate> getCandidateDetailsByStatus(int i) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Candidate> candidates = new ArrayList<Candidate>();
		ps12.setInt(1, i);
		ResultSet rs = ps12.executeQuery();
		while (rs.next()) {
			Candidate candidate = new Candidate();
			candidate.setCandidateId(rs.getString(1));
			candidate.setUserID(rs.getString(2));
			candidate.setElectionId(rs.getString(3));
			Blob blob = (Blob) rs.getBlob(4);
			String base64Image = "";
			byte[] imageBytes = blob.getBytes(1L, (int) blob.length());
			Encoder ec = Base64.getEncoder();
			base64Image = ec.encodeToString(imageBytes);
			candidate.setSymbol(base64Image);
			candidate.setActive(rs.getString(5));
			candidates.add(candidate);
		}
		return candidates;
	}

	public static CandidateDetails getCandidateDetailsByCandidateId(String candidateId) throws SQLException {
		ps3.setString(1, candidateId);
		ResultSet rs = ps3.executeQuery();
		CandidateDetails candidate = null;
		if (rs.next()) {
			candidate = new CandidateDetails();
			candidate.setCandidateId(rs.getString(1));
			candidate.setUserId(rs.getString(2));
			;
			candidate.setElectionId(rs.getString(3));
			candidate.setUserName(rs.getString(4));
			candidate.setBranch(rs.getString(5));
			candidate.setYear(rs.getString(6));
			Blob blob = (Blob) rs.getBlob(7);
			String base64Image = "";
			byte[] imageBytes = blob.getBytes(1L, (int) blob.length());
			Encoder ec = Base64.getEncoder();
			base64Image = ec.encodeToString(imageBytes);
			candidate.setSymbol("data:image/jpg;base64," + base64Image);
			blob = (Blob) rs.getBlob(8);
			if (blob != null) {
				base64Image = "";
				imageBytes = blob.getBytes(1L, (int) blob.length());
				base64Image = ec.encodeToString(imageBytes);
				candidate.setPhoto("data:image/jpg;base64," + base64Image);
			} else {
				candidate.setPhoto("images/businessman-310819_640.png");
			}
			candidate.setActive(rs.getString(9));
		}
		return candidate;
	}

	public static ArrayList<CandidateDetails> getCandidateDetailsByElectionId(String electionId) throws SQLException {
		ArrayList<CandidateDetails> candidates = new ArrayList<CandidateDetails>();
		ps13.setString(1, electionId);
		ResultSet rs = ps13.executeQuery();
		while (rs.next()) {
			CandidateDetails candidate = new CandidateDetails();
			candidate.setCandidateId(rs.getString(1));
			candidate.setUserId(rs.getString(2));
			candidate.setElectionId(rs.getString(3));
			candidate.setUserName(rs.getString(4));
			candidate.setBranch(rs.getString(5));
			candidate.setYear(rs.getString(6));
			Blob blob = (Blob) rs.getBlob(7);
			String base64Image = "";
			byte[] imageBytes = blob.getBytes(1L, (int) blob.length());
			Encoder ec = Base64.getEncoder();
			base64Image = ec.encodeToString(imageBytes);
			candidate.setSymbol("data:image/jpg;base64," + base64Image);
			blob = (Blob) rs.getBlob(8);
			if (blob != null) {
				base64Image = "";
				imageBytes = blob.getBytes(1L, (int) blob.length());
				base64Image = ec.encodeToString(imageBytes);
				candidate.setPhoto("data:image/jpg;base64," + base64Image);
			} else {
				candidate.setPhoto("images/businessman-310819_640.png");
			}
			candidate.setActive(rs.getString(9));
			candidates.add(candidate);
		}
		return candidates;
	}

}
