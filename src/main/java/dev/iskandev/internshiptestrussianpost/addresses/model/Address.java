package dev.iskandev.internshiptestrussianpost.addresses.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "addresses")
@DynamicUpdate
@Component
public class Address {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "plain_address", nullable = false)
    @JsonProperty("plain_address")
    private String plainAddress;

    @Column
    private String instruction;

    @Column
    private String index;

    @Column
    private String region;

    @Column
    private String area;

    @Column
    private String place;

    @Column
    private String district;

    @Column
    private String street;

    @Column
    private String house;

    @Column
    private String letter;

    @Column
    private String slash;

    @Column
    private String corpus;

    @Column
    private String building;

    @Column
    private String room;

    /**
     * Checks if current state of address is incorrect i.e.
     *  non-null fields contain {@literal null}
     * @return {@literal true} if address's state is correct,
     *         {@literal false} otherwise.
     */
    @JsonIgnore
    public boolean isIncorrect() {
        return  Objects.isNull(id) || Objects.isNull(plainAddress);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", plainAddress='" + plainAddress + '\'' +
                ", instruction='" + instruction + '\'' +
                ", index=" + index +
                ", region='" + region + '\'' +
                ", area='" + area + '\'' +
                ", place='" + place + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", house=" + house +
                ", letter='" + letter + '\'' +
                ", slash=" + slash +
                ", corpus=" + corpus +
                ", building=" + building +
                ", room='" + room + '\'' +
                '}';
    }

    @SuppressWarnings("unused")
    public String getPlainAddress() {
        return plainAddress;
    }

    @SuppressWarnings("unused")
    public void setPlainAddress(String plainAddress) {
        this.plainAddress = plainAddress;
    }

    @SuppressWarnings("unused")
    public String getInstruction() {
        return instruction;
    }

    @SuppressWarnings("unused")
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @SuppressWarnings("unused")
    public String getIndex() {
        return index;
    }

    @SuppressWarnings("unused")
    public void setIndex(String index) {
        this.index = index;
    }

    @SuppressWarnings("unused")
    public String getRegion() {
        return region;
    }

    @SuppressWarnings("unused")
    public void setRegion(String region) {
        this.region = region;
    }

    @SuppressWarnings("unused")
    public String getArea() {
        return area;
    }

    @SuppressWarnings("unused")
    public void setArea(String area) {
        this.area = area;
    }

    @SuppressWarnings("unused")
    public String getPlace() {
        return place;
    }

    @SuppressWarnings("unused")
    public void setPlace(String place) {
        this.place = place;
    }

    @SuppressWarnings("unused")
    public String getDistrict() {
        return district;
    }

    @SuppressWarnings("unused")
    public void setDistrict(String district) {
        this.district = district;
    }

    @SuppressWarnings("unused")
    public String getStreet() {
        return street;
    }

    @SuppressWarnings("unused")
    public void setStreet(String street) {
        this.street = street;
    }

    @SuppressWarnings("unused")
    public String getHouse() {
        return house;
    }

    @SuppressWarnings("unused")
    public void setHouse(String house) {
        this.house = house;
    }

    @SuppressWarnings("unused")
    public String getLetter() {
        return letter;
    }

    @SuppressWarnings("unused")
    public void setLetter(String letter) {
        this.letter = letter;
    }

    @SuppressWarnings("unused")
    public String getSlash() {
        return slash;
    }

    @SuppressWarnings("unused")
    public void setSlash(String slash) {
        this.slash = slash;
    }

    @SuppressWarnings("unused")
    public String getCorpus() {
        return corpus;
    }

    @SuppressWarnings("unused")
    public void setCorpus(String corpus) {
        this.corpus = corpus;
    }

    @SuppressWarnings("unused")
    public String getBuilding() {
        return building;
    }

    @SuppressWarnings("unused")
    public void setBuilding(String building) {
        this.building = building;
    }

    @SuppressWarnings("unused")
    public String getRoom() {
        return room;
    }

    @SuppressWarnings("unused")
    public void setRoom(String room) {
        this.room = room;
    }

    @SuppressWarnings("unused")
    public UUID getId() {
        return id;
    }

    @SuppressWarnings("unused")
    public void setId(UUID id) {
        this.id = id;
    }
}
