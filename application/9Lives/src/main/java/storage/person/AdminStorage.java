package storage.person;

import entity.person.Admin;

public interface AdminStorage {

    static AdminStorage getAdminStorage(){
        return AdminStorageImpl.getAdminStorage();
    }
    public Admin getAdmin(String username);
}
