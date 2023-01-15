package com.aliak.webapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="post")
@Data //Lombok'dan gelir ototmaik olarak getter setter yapar
public class Post {
    @Id
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)//bir kullanıcının birden fazla postu olabilir.
    @JoinColumn(name = "user_id",nullable = false)//User table'ındaki idyi foreign key olarak kullanarak iki tablo birleşir.
    @OnDelete(action =  OnDeleteAction.CASCADE)//bir user silindiğinde tüm postları silinmeli.
    @JsonIgnore//seriazilation kısmında sorun çıkmaması için,Fetch etmediğimiz için bu alanı ignore et.
    User user;//FetchType.LAZY user objesini getirme(FechType.EAGER yazsaydık bir de user objesi gelirdi.
    String title;
    @Lob
    @Column(columnDefinition = "text")
    String text;

}

