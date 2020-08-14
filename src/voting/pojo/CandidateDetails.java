package voting.pojo;

public class CandidateDetails {
	String candidateId;
	String userId;
	String electionId;
	String userName;
	String branch;
	String year;
	String symbol;
	String photo;
	String active;

	public CandidateDetails() {
		super();
	}

	public CandidateDetails(String candidateId, String userId, String electionId, String userName, String branch,
			String year, String symbol, String photo, String active) {
		super();
		this.candidateId = candidateId;
		this.userId = userId;
		this.electionId = electionId;
		this.userName = userName;
		this.branch = branch;
		this.year = year;
		this.symbol = symbol;
		this.photo = photo;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result + ((candidateId == null) ? 0 : candidateId.hashCode());
		result = prime * result + ((electionId == null) ? 0 : electionId.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		CandidateDetails other = (CandidateDetails) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
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
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
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
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CandidateDetails [candidateId=" + candidateId + ", userId=" + userId + ", electionId=" + electionId
				+ ", userName=" + userName + ", branch=" + branch + ", year=" + year + ", symbol=" + symbol + ", photo="
				+ photo + ", active=" + active + "]";
	}

}
