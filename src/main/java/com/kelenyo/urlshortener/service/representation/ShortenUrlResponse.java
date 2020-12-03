package com.kelenyo.urlshortener.service.representation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Request object for POST method")
public class ShortenUrlResponse {

    private Long id;
    @ApiModelProperty(required = true, notes = "Url to convert to short")
    private String url;
    private LocalDateTime created;
    private Boolean status;
    private String description;

    @Override
    public String toString() {
        return "ShortenResponse{" +
                "urlId=" + id +
                ", urlCode='" + url + '\'' +
                ", createdAt=" + created +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
