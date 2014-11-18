package yihang.wang.model;

public class Sieve {
	private String sieveId;
	private String walkStatus;
	private String breathStatus;
	private String aOBreathing;
	private String respiratoryRate;
	private String pulseRate;
	private String symptoms;
	private String injuries;
	private String priority;
	private String sieveDate;
	private String sieveTime;
	private User user;
	private Patient patient;
	private GeoInfo geoInfo;

	public Sieve() {
		sieveId = "";
		walkStatus = "";
		breathStatus = "";
		aOBreathing = "";
		respiratoryRate = "";
		pulseRate = "";
		symptoms = "";
		injuries = "";
		priority = "";
		sieveDate = "";
		sieveTime = "";
		user = null;
		patient = null;
		geoInfo = null;
	}

	public Sieve(String sieveId, String walkStatus, String breathStatus,
			String aOBreathing, String respiratoryRate, String pulseRate,
			String symptoms, String injuries, String priority,
			String sieveDate, String sieveTime, User user, Patient patient, GeoInfo geoInfo) {
		this.sieveId = sieveId;
		this.walkStatus = walkStatus;
		this.breathStatus = breathStatus;
		this.aOBreathing = aOBreathing;
		this.respiratoryRate = respiratoryRate;
		this.pulseRate = pulseRate;
		this.symptoms = symptoms;
		this.injuries = injuries;
		this.priority = priority;
		this.sieveDate = sieveDate;
		this.sieveTime = sieveTime;
		this.user = user;
		this.patient = patient;
		this.geoInfo = geoInfo;
	}

	public String getSieveId() {
		return sieveId;
	}

	public void setSieveId(String sieveId) {
		this.sieveId = sieveId;
	}

	public String getWalkStatus() {
		return walkStatus;
	}

	public void setWalkStatus(String walkStatus) {
		this.walkStatus = walkStatus;
	}

	public String getBreathStatus() {
		return breathStatus;
	}

	public void setBreathStatus(String breathStatus) {
		this.breathStatus = breathStatus;
	}

	public String getAOBreathing() {
		return aOBreathing;
	}

	public void setAOBreathing(String aOBreathing) {
		this.aOBreathing = aOBreathing;
	}

	public String getRespiratoryRate() {
		return respiratoryRate;
	}

	public void setRespiratoryRate(String respiratoryRate) {
		this.respiratoryRate = respiratoryRate;
	}

	public String getPulseRate() {
		return pulseRate;
	}

	public void setPulseRate(String pulseRate) {
		this.pulseRate = pulseRate;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getInjuries() {
		return injuries;
	}

	public void setInjuries(String injuries) {
		this.injuries = injuries;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getSieveDate() {
		return sieveDate;
	}

	public void setSieveDate(String sieveDate) {
		this.sieveDate = sieveDate;
	}

	public String getSieveTime() {
		return sieveTime;
	}

	public void setSieveTime(String sieveTime) {
		this.sieveTime = sieveTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public GeoInfo getGeo() {
		return geoInfo;
	}

	public void setGeo(GeoInfo geoInfo) {
		this.geoInfo = geoInfo;
	}
}
