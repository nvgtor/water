package com.water.nvgtor.watermanegement.bean;

import java.util.List;

/**
 * Created by dell on 2015/9/8.
 */
public class RepairDetail {
    private List<PersonDto> personDtoList;
    private RepairRows eventModel;

    public List<PersonDto> getPersonDtoList() {
        return personDtoList;
    }

    public void setPersonDtoList(List<PersonDto> personDtoList) {
        this.personDtoList = personDtoList;
    }

    public RepairRows getEventModel() {
        return eventModel;
    }

    public void setEventModel(RepairRows eventModel) {
        this.eventModel = eventModel;
    }

    @Override
    public String toString() {
        return "RepairDetail{" +
                "personDtoList=" + personDtoList +
                ", eventModel=" + eventModel +
                '}';
    }
}
