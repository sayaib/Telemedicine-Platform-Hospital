public class Appointment {
    // data members
    String type;
    int patient_id;
    String patient_name;
    String appointment_date_time;
    String doctor_name;
    String patient_details;

    public Appointment(String type, int patient_id, String patient_name, String appointment_date_time, String doctor_name,
            String patient_details) {
        this.type = type;
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.appointment_date_time = appointment_date_time;
        this.doctor_name = doctor_name;
        this.patient_details = patient_details;
    }

    // getter methods

    public String getType() {
        return type;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public String getAppointment_date_time() {
        return appointment_date_time;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public String getpatient_details() {
        return patient_details;
    }

    // setter methods

    public void setType(String type) {
        this.type = type;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public void setAppointment_date_time(String appointment_date_time) {
        this.appointment_date_time = appointment_date_time;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public void setpatient_details(String patient_details) {
        this.patient_details = patient_details;
    }

}