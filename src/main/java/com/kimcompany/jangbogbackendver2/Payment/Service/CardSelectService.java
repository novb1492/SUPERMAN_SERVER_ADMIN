package com.kimcompany.jangbogbackendver2.Payment.Service;

import com.kimcompany.jangbogbackendver2.Payment.Dto.SelectForOrderDto;
import com.kimcompany.jangbogbackendver2.Payment.Repo.CardRepo;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.kimcompany.jangbogbackendver2.Text.BasicText.deleteState;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CardSelectService {

    private final CardRepo cardRepo;

    public Optional<SelectForOrderDto> selectForOrder(long storeId, long id){
        return cardRepo.findByIdAndStoreId(deleteState, id, storeId);
    }
}
