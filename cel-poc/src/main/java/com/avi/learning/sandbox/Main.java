package com.avi.learning.sandbox;

import com.avi.learning.sandbox.model.Order;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static PriceCalculationService service = new PriceCalculationService();
    public static void main(String[] args) throws Exception {
        Order order = new Order();
        order.customerId = "1";
        order.volume = 1;
        System.out.println("Result: " + service.calculatePrice(order));

    }
}