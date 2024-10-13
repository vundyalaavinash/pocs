package com.avi.learning.sandbox;

import com.avi.learning.sandbox.model.Order;
import com.avi.learning.sandbox.model.RateCardMetadata;
import com.avi.learning.sandbox.pricingengine.CelPricingEngine;
import com.avi.learning.sandbox.pricingengine.SpelPricingEngine;
import com.avi.learning.sandbox.utils.RateCardMetadataLoader;

import java.math.BigDecimal;

public class PriceCalculationService {
    private RateCardMetadataLoader rateCardMetadataLoader;
    private SpelPricingEngine celPricingEngine;

//    private PricingContextService pricingContextService;

    public PriceCalculationService() {
        this.rateCardMetadataLoader = new RateCardMetadataLoader();
        this.celPricingEngine = new SpelPricingEngine();
//        this.pricingContextService = new PricingContextService();
    }

    public BigDecimal calculatePrice(Order order) throws Exception {
        RateCardMetadata rateCardMetadata = rateCardMetadataLoader.loadRateCardMetadata();
//        PricingContext context = pricingContextService.getPricingContext(order.getCustomerId());
        return celPricingEngine.calculatePrice(order, rateCardMetadata);
    }
}
