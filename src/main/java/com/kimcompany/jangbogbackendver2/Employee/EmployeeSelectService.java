package com.kimcompany.jangbogbackendver2.Employee;

import com.kimcompany.jangbogbackendver2.Employee.Repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeSelectService {

    private final EmployeeRepo employeeRepo;
    public boolean exist(long storeId,long userId){
        return employeeRepo.exist(storeId, userId);
    }
}
