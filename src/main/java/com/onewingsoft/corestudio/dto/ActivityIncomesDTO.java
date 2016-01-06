package com.onewingsoft.corestudio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ignacio González Bullón - <nacho.gonzalez.bullon@gmail.com>
 * @since 01/01/16.
 */
public class ActivityIncomesDTO {

    private String activityName;
    private List<PassTypeIncomesDTO> passTypeIncomes;

    public ActivityIncomesDTO(String activityName) {
        this.activityName = activityName;
        this.passTypeIncomes = new ArrayList<>();
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public List<PassTypeIncomesDTO> getPassTypeIncomes() {
        return passTypeIncomes;
    }

    public void setPassTypeIncomes(List<PassTypeIncomesDTO> passTypeIncomes) {
        this.passTypeIncomes = passTypeIncomes;
    }

    public void addPassTypeIncomes(String passType, Long incomes) {
        PassTypeIncomesDTO passTypeAccounts = this.getPassTypeIncomes(passType);
        passTypeAccounts.addToIncomes(incomes);
        passTypeAccounts.increaseNumberOfSessions();
    }

    @JsonIgnore
    private PassTypeIncomesDTO getPassTypeIncomes(String passType) {
        for (PassTypeIncomesDTO passTypeIncomes : this.passTypeIncomes) {
            if (passType.equals(passTypeIncomes.getPassTypeName())) {
                return passTypeIncomes;
            }
        }
        PassTypeIncomesDTO result = new PassTypeIncomesDTO(passType);
        passTypeIncomes.add(result);
        return result;
    }

    public Long getActivityIncomes() {
        Long result = 0L;
        for (PassTypeIncomesDTO passTypeIncomes : this.passTypeIncomes) {
            result += passTypeIncomes.getIncomes();
        }
        return result;
    }

    public Integer getActivitySessions() {
        Integer result = 0;
        for (PassTypeIncomesDTO passTypeIncomes : this.passTypeIncomes) {
            result += passTypeIncomes.getNumberOfSessions();
        }
        return result;
    }
}
