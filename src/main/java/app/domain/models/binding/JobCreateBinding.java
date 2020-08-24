package app.domain.models.binding;

import app.domain.entities.Sector;

import java.math.BigDecimal;

public class JobCreateBinding {
    private String sector;
    private String profession;
    private BigDecimal salary;
    private String description;

    public JobCreateBinding() {
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
