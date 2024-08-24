package storage.appointment;

public interface AppointmentStorage {
    
    static AppointmentStorage getAppointmentStorage(){
        return AppointmentStorageImpl.getAppointmentStorage();
    }
    
    void getAppointmentInfo(int DoctorId);
    
    void updateAppointment(int appointmentId);

    void deleteAppointment(int appointmentId);
}
