package br.com.andersonmatte.EnglishOnTv.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stream implements Serializable {

    private String channel;
    private String url;
    private String status;

    public Stream(){

    }

    public Stream(String channel, String url, String status) {
        this.channel = channel;
        this.url = url;
        this.status = status;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
