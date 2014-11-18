package yihang.wang.model;

public class Sort {
	private String eyeOpen;
	private String gcsValue;
	private String glasgowComaScore;
	private String injuries;
	private String motorResponse;
	private String priority;
	private String respiratoryRate;
	private String score;
	private String sortDate;
	private String sortId;
	private String sortTime;
	private String symptoms;
	private String systilicBloodPresure;
	private String verbalResponse;
	private GeoInfo geoInfo;
	private Patient patient;
	private User user;

	public Sort() {
		eyeOpen = "";
		gcsValue = "";
		glasgowComaScore = "";
		injuries = "";
		motorResponse = "";
		priority = "";
		respiratoryRate = "";
		score = "";
		sortDate = "";
		sortId = "";
		sortTime = "";
		symptoms = "";
		systilicBloodPresure = "";
		verbalResponse = "";
		geoInfo = null;
		patient = null;
		user = null;
	}

	public Sort(String eyeOpen, String gcsValue, String glasgowComaScore,
			String injuries, String motorResponse, String priority,
			String respiratoryRate, String score, String sortDate,
			String sortId, String sortTime, String symptoms,
			String systilicBloodPresure, String verbalResponse,
			GeoInfo geoInfo, Patient patient, User user) {
		this.eyeOpen = eyeOpen;
		this.gcsValue = gcsValue;
		this.glasgowComaScore = glasgowComaScore;
		this.injuries = injuries;
		this.motorResponse = motorResponse;
		this.priority = priority;
		this.respiratoryRate = respiratoryRate;
		this.score = score;
		this.sortDate = sortDate;
		this.sortId = sortId;
		this.sortTime = sortTime;
		this.symptoms = symptoms;
		this.systilicBloodPresure = systilicBloodPresure;
		this.verbalResponse = verbalResponse;
		this.geoInfo = geoInfo;
		this.patient = patient;
		this.user = user;
	}
	
	public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getEyeOpen() {
        return eyeOpen;
    }

    public void setEyeOpen(String eyeOpen) {
        this.eyeOpen = eyeOpen;
    }

    public String getVerbalResponse() {
        return verbalResponse;
    }

    public void setVerbalResponse(String verbalResponse) {
        this.verbalResponse = verbalResponse;
    }

    public String getMotorResponse() {
        return motorResponse;
    }

    public void setMotorResponse(String motorResponse) {
        this.motorResponse = motorResponse;
    }

    public String getGlasgowComaScore() {
        return glasgowComaScore;
    }

    public void setGlasgowComaScore(String glasgowComaScore) {
        this.glasgowComaScore = glasgowComaScore;
    }

    public String getGcsValue() {
        return gcsValue;
    }

    public void setGcsValue(String gcsValue) {
        this.gcsValue = gcsValue;
    }

    public String getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(String respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public String getSystilicBloodPresure() {
        return systilicBloodPresure;
    }

    public void setSystilicBloodPresure(String systilicBloodPresure) {
        this.systilicBloodPresure = systilicBloodPresure;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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

    public String getSortDate() {
        return sortDate;
    }

    public void setSortDate(String sortDate) {
        this.sortDate = sortDate;
    }

    public String getSortTime() {
        return sortTime;
    }

    public void setSortTime(String sortTime) {
        this.sortTime = sortTime;
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
