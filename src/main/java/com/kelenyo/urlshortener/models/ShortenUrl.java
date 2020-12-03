package com.kelenyo.urlshortener.models;

import lombok.*;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shorten_url", uniqueConstraints={@UniqueConstraint(columnNames={"code"})})
public class ShortenUrl {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="code")
    private String code;

    @Column(name="url")
    private String url;

    @Column(name="created")
    private LocalDateTime created;

}
