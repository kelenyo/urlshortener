package com.kelenyo.urlshortener.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

}
