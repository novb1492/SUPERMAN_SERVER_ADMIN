package com.kimcompany.jangbogbackendver2.Deliver.Service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliverService {
    private final DeliverSelectService deliverSelectService;
}
