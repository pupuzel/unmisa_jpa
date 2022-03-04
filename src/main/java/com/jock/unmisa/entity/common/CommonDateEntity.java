package com.jock.unmisa.entity.common;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CommonDateEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime cre_date;

    @LastModifiedDate
    private LocalDateTime upd_date;

}
