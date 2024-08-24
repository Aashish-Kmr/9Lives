package storage.person;

public interface DoctorStorage {
    int getNewId();

    static DoctorStorage getDoctorStorage() {
        return DoctorStorageImpl.getDoctorStorage();
    }

    void getDoctorDetails(int doctorId);

    void updateFirstName(String firstName);

    void updateLastName(String lastName);

    void updateRole(String role);

    void updateSpecialization(String specialization);

    void removeDoctorDetails(int doctorId);
}
