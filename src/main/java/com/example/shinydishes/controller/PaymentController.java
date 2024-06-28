package com.example.shinydishes.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PaymentController {

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @PostMapping("/charge")
    public String charge(@RequestParam String stripeToken, @RequestParam int totalAmount) {
        Stripe.apiKey = stripeSecretKey;

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", totalAmount);
        chargeParams.put("currency", "usd");
        chargeParams.put("description", "Example charge");
        chargeParams.put("source", stripeToken);

        try {
            Charge charge = Charge.create(chargeParams);
            return "Успішний платіж";
        } catch (StripeException e) {
            return "Помилка під час виконання платежу: " + e.getMessage();
        }
    }
}
