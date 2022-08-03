package com.kimcompany.jangbogbackendver2.Store.Repo;

public interface StoreRepoCustom {
    Boolean exist(String address, String storeName);
    Boolean exist(long storeId, long adminId);

}
