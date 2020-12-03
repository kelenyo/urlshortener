package com.kelenyo.urlshortener.repositories;

import com.kelenyo.urlshortener.models.ShortenUrl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ShortenUrlRepository extends CrudRepository<ShortenUrl, Long> {
    @Query("select s from ShortenUrl s where s.code = :code")
    ShortenUrl findByCode(@Param(value = "code") String code);

    @Query("select s from ShortenUrl s where s.url = :url")
    ShortenUrl findByUrl( @Param(value = "url") String url);

}
