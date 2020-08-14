package voting.pojo;

public class Candidate {
	String candidateId;
	String userID;
	String electionId;
	String symbol;
	String active;

	public Candidate() {
		super();
	}

	public Candidate(String candidateId, String userID, String electionId, String symbol, String active) {
		super();
		this.candidateId = candidateId;
		this.userID = userID;
		this.electionId = electionId;
		this.symbol = symbol;
		this.active = active;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getElectionId() {
		return electionId;
	}

	public void setElectionId(String electionId) {
		this.electionId = electionId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((candidateId == null) ? 0 : candidateId.hashCode());
		result = prime * result + ((electionId == null) ? 0 : electionId.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidate other = (Candidate) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (candidateId == null) {
			if (other.candidateId != null)
				return false;
		} else if (!candidateId.equals(other.candidateId))
			return false;
		if (electionId == null) {
			if (other.electionId != null)
				return false;
		} else if (!electionId.equals(other.electionId))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Candidate [candidateId=" + candidateId + ", userID=" + userID + ", electionId=" + electionId
				+ ", symbol=" + symbol + ", active=" + active + "]";
	}

}
