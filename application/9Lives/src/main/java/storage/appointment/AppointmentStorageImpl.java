package storage.appointment;

public class AppointmentStorageImpl implements AppointmentStorage{
    private static AppointmentStorageImpl appointmentStorage;

    public static AppointmentStorage getAppointmentStorage(){
        if(appointmentStorage==null){
            appointmentStorage = new AppointmentStorageImpl();
        }
        return appointmentStorage;
    }

    @Override
    public void getAppointmentInfo(int DoctorId) {

    }

    @Override
    public void updateAppointment(int appointmentId) {

    }

    @Override
    public void deleteAppointment(int appointmentId) {

    }
}
