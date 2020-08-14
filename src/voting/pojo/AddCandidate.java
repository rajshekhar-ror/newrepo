package voting.pojo;

import java.io.InputStream;

public class AddCandidate {
	String candidateId;
	String userId;
	String electionId;
	InputStream symbol;
	String active;

	public AddCandidate() {
		super();
	}

	public AddCandidate(String candidateId, String userId, String electionId, InputStream symbol, String active) {
		super();
		this.candidateId = candidateId;
		this.userId = userId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getElectionId() {
		return electionId;
	}

	public void setElectionId(String electionId) {
		this.electionId = electionId;
	}

	public InputStream getSymbol() {
		return symbol;
	}

	public void setSymbol(InputStream symbol) {
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
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		AddCandidate other = (AddCandidate) obj;
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
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AddCandidate [candidateId=" + candidateId + ", userId=" + userId + ", electionId=" + electionId
				+ ", symbol=" + symbol + ", active=" + active + "]";
	}

}
