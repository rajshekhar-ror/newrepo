package voting.pojo;

public class Election {
	String electionId;
	String electionName;
	int status;

	public Election() {
		super();
	}

	public Election(String electionId, String electionName, int status) {
		super();
		this.electionId = electionId;
		this.electionName = electionName;
		this.status = status;
	}

	public String getElectionId() {
		return electionId;
	}

	public void setElectionId(String electionId) {
		this.electionId = electionId;
	}

	public String getElectionName() {
		return electionName;
	}

	public void setElectionName(String electionName) {
		this.electionName = electionName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((electionId == null) ? 0 : electionId.hashCode());
		result = prime * result + ((electionName == null) ? 0 : electionName.hashCode());
		result = prime * result + status;
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
		Election other = (Election) obj;
		if (electionId == null) {
			if (other.electionId != null)
				return false;
		} else if (!electionId.equals(other.electionId))
			return false;
		if (electionName == null) {
			if (other.electionName != null)
				return false;
		} else if (!electionName.equals(other.electionName))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Election [electionId=" + electionId + ", electionName=" + electionName + ", status=" + status + "]";
	}

}
