package com.kelenyo.urlshortener.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Request object for POST method")
public class UrlRequest {

    @ApiModelProperty(required = true, notes = "Url to convert to short")
    private String url;

    @ApiModelProperty(notes = "code to use for short url")
    private String code;

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

}
