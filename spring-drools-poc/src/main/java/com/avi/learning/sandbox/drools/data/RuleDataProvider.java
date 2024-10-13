package com.avi.learning.sandbox.drools.data;

import com.avi.learning.sandbox.drools.models.Rule;

import java.util.ArrayList;
import java.util.List;

public class RuleDataProvider {

    public static List<Rule> getRules() {
        List<Rule> rules = new ArrayList<>();

        Rule rule1 = new Rule();
        rule1.setId(1);
        rule1.setVersion(1);
        rule1.setIfcondition("orderObject : Order(cardType==\"ICICI\" && price > 10000);");
        rule1.setThencondition("orderObject.setDiscount(10);");
        rules.add(rule1);

        Rule rule2 = new Rule();
        rule2.setId(2);
        rule2.setVersion(1);
        rule2.setIfcondition("orderObject : Order(cardType==\"DBS\" && price > 15000);");
        rule2.setThencondition("orderObject.setDiscount(15);");
        rules.add(rule2);

        return rules;
    }
}
