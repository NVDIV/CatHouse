package org.example.models;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true,
        defaultImpl = Kitten.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FemaleCat.class, name = "female"),
        @JsonSubTypes.Type(value = MaleCat.class, name = "male"),
        @JsonSubTypes.Type(value = Kitten.class, name = "kitten")
})
abstract public class Cat {
    String id;
    String name;
    String status;
    LocalDate birthDate;
    String litterId;
    String breedId;
    String userId;

    @JsonIgnore
    public abstract boolean isAvailableForBreeding();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreedId() {
        return breedId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setLitterId(String litterId) {
        this.litterId = litterId;
    }

    public void setBreedId(String breedId) {
        this.breedId = breedId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    abstract boolean isAdult(LocalDate currentDate);
}
