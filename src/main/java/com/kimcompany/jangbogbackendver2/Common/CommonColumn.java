package com.kimcompany.jangbogbackendver2.Common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonColumn {

    @Column(name = "STATE", columnDefinition = "TINYINT")
    private int state;

    @Column(name = "CREATED")
    @CreatedDate
    private LocalDateTime created;
}
