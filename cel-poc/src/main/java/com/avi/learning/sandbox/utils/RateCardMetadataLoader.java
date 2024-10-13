package com.avi.learning.sandbox.utils;

import com.avi.learning.sandbox.model.Rate;
import com.avi.learning.sandbox.model.RateCardMetadata;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class RateCardMetadataLoader {

    public RateCardMetadata loadRateCardMetadata() {

        RateCardMetadata rdm = new RateCardMetadata();
        rdm.setId("3");
        rdm.setEffectiveDate(new Date());
        rdm.setExpiryDate(new Date());
        rdm.setTenantId("tenant3");
        rdm.setPricingRule("#rates[0].value * #order.volume");
        rdm.setRates(getRates());
        return rdm;
    }

    public List<Rate> getRates() {
        Rate rate = new Rate();
        rate.setCurrency("USD");
        rate.setType("flat");
        rate.setValue(BigDecimal.valueOf(100.0));
        return Collections.singletonList(rate);
    }

}
