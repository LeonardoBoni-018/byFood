package com.byfood.api.service;

import com.byfood.api.model.Order;
import com.byfood.api.model.OrderItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.Locale;

@Service
public class WhatsAppLinkService {

    private static final Locale PT_BR = Locale.forLanguageTag("pt-BR");

    public String buildOrderLink(Order order, String restaurantName, String restaurantWhatsAppNumber) {
        String message = buildOrderMessage(order, restaurantName);
        String encoded = URLEncoder.encode(message, StandardCharsets.UTF_8);
        return "https://wa.me/" + digitsOnly(restaurantWhatsAppNumber) + "?text=" + encoded;
    }

    private String buildOrderMessage(Order order, String restaurantName) {
        StringBuilder sb = new StringBuilder("Olá! ").append(restaurantName).append("\n\n");
        sb.append("Gostaria de confirmar meu pedido:\n\n");
        for (OrderItem item : order.getItems()) {
            BigDecimal lineTotal = item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            sb.append(item.getQuantity())
                    .append("x ")
                    .append(item.getItemName())
                    .append(" - ")
                    .append(formatPrice(item.getUnitPrice()))
                    .append(" = ")
                    .append(formatPrice(lineTotal))
                    .append("\n");
        }
        sb.append("\nTotal: ").append(formatPrice(order.getTotal()));
        sb.append("\n\nNome: ").append(order.getCustomerName());
        sb.append("\nTelefone: ").append(order.getCustomerPhone());
        sb.append("\nEndereço: ").append(order.getCustomerAddress());
        return sb.toString();
    }

    private String formatPrice(BigDecimal value) {
        return NumberFormat.getCurrencyInstance(PT_BR).format(value);
    }

    private String digitsOnly(String phone) {
        return phone.replaceAll("\\D", "");
    }
}