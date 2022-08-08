package com.kimcompany.jangbogbackendver2.Store.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class SearchCondition {

    private int page;
    private String keyword;
    private String role;
    private String category;

    public static SearchCondition set(int page,String keyword,String role,String category){
        SearchCondition searchCondition = new SearchCondition();
        searchCondition.setKeyword(keyword);
        searchCondition.setPage(page);
        searchCondition.setRole(role);
        searchCondition.setCategory(category);
        return searchCondition;
    }
}
