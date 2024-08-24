package storage.person;

import entity.person.Admin;

public class AdminStorageImpl implements AdminStorage{

    private static AdminStorageImpl adminStorage;

    public static AdminStorage getAdminStorage(){
        if(adminStorage==null){
            adminStorage = new AdminStorageImpl();
        }
        return adminStorage;
    }

    @Override
    public Admin getAdmin(String username) {
        return null;
    }
}
