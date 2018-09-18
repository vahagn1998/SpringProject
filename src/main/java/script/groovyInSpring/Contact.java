package script.groovyInSpring;

import org.joda.time.DateTime;

public class Contact {
    private Long id;
    private String firstNarne;
    private String lastNarne;
    private DateTime birthDate;
    private String ageCategory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstNarne() {
        return firstNarne;
    }

    public void setFirstNarne(String firstNarne) {
        this.firstNarne = firstNarne;
    }

    public String getLastNarne() {
        return lastNarne;
    }

    public void setLastNarne(String lastNarne) {
        this.lastNarne = lastNarne;
    }

    public DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(String ageCategory) {
        this.ageCategory = ageCategory;
    }
}
