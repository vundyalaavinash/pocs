package com.avi.learning.sandbox.drools.models;

import lombok.Data;

@Data
public class Rule {

    private Integer id;

    private String ifcondition;

    private String thencondition;

    private int version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIfcondition() {
        return ifcondition;
    }

    public void setIfcondition(String ifcondition) {
        this.ifcondition = ifcondition;
    }

    public String getThencondition() {
        return thencondition;
    }

    public void setThencondition(String thencondition) {
        this.thencondition = thencondition;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
