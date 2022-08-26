package com.kimcompany.jangbogbackendver2.Deliver.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SearchCondition {

    private int page;
    private int pageSize;
    private long storeId;
    private long deliverId;
    private int state;


    public static SearchCondition set(int page,int pageSize,long storeId,long deliverId,int state){
        SearchCondition searchCondition = new SearchCondition();
        searchCondition.setPage(page);
        searchCondition.setDeliverId(deliverId);
        searchCondition.setPageSize(pageSize);
        searchCondition.setStoreId(storeId);
        searchCondition.setState(state);
        return searchCondition;
    }

}