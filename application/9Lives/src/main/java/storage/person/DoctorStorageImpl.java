package storage.person;

public class DoctorStorageImpl implements DoctorStorage{

    private static DoctorStorageImpl doctorStorage;

    public int getNewId() {
        return 0;
    }

    public static DoctorStorage getDoctorStorage() {
        if(doctorStorage == null)
            doctorStorage = new DoctorStorageImpl();
        return doctorStorage;
    }

    @Override
    public void getDoctorDetails(int doctorId) {

    }

    @Override
    public void updateFirstName(String firstName) {

    }

    @Override
    public void updateLastName(String lastName) {

    }

    @Override
    public void updateRole(String role) {

    }

    @Override
    public void updateSpecialization(String specialization) {

    }

    @Override
    public void removeDoctorDetails(int doctorId) {

    }

}
