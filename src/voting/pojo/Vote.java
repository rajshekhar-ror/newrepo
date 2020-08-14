package voting.pojo;

public class Vote {
	String userId;
	String candidateId;
	String electionId;

	public Vote() {
		super();
	}

	public Vote(String userId, String candidateId, String electionId) {
		super();
		this.userId = userId;
		this.candidateId = candidateId;
		this.electionId = electionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getElectionId() {
		return electionId;
	}

	public void setElectionId(String electionId) {
		this.electionId = electionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((candidateId == null) ? 0 : candidateId.hashCode());
		result = prime * result + ((electionId == null) ? 0 : electionId.hashCode());
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
		Vote other = (Vote) obj;
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
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vote [userId=" + userId + ", candidateId=" + candidateId + ", electionId=" + electionId + "]";
	}

}
