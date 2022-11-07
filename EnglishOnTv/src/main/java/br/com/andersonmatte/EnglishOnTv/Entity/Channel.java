package br.com.andersonmatte.EnglishOnTv.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel implements Serializable {

    private String id;
    private String name;
    private String country;
    private String logo;
    private Stream stream;

    public Channel(){

    }

    public Channel(String id, String name, String country, String logo, Stream stream) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.logo = logo;
        this.stream = stream;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Stream getStream() {
        return stream;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }

}
