package yihang.wang.model;

public class Patient {
	private String patientFirstName;
	private String patientId;
	private String patientLastName;

	public Patient() {
		patientFirstName = "";
		patientId = "";
		patientLastName = "";
	}

	public Patient(String newPatientFirstName, String newPatientId,
			String newPatientLastName) {
		this.patientFirstName = newPatientFirstName;
		this.patientId = newPatientId;
		this.patientLastName = newPatientLastName;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
}
