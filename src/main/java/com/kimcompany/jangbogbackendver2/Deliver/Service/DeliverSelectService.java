package com.kimcompany.jangbogbackendver2.Deliver.Service;

import com.kimcompany.jangbogbackendver2.Deliver.Repo.DeliverRepo;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliverSelectService {
    private final DeliverRepo deliverRepo;
}
