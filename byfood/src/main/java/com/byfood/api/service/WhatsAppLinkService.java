package com.byfood.api.service;

import com.byfood.api.model.Order;
import com.byfood.api.model.OrderItem;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class WhatsAppLinkService {

    public String buildOrderLink(Order order, String restaurantWhatsAppNumber) {
        String message = buildOrderMessage(order);
        String encoded = URLEncoder.encode(message, StandardCharsets.UTF_8);
        return "https://wa.me/" + digitsOnly(restaurantWhatsAppNumber) + "?text=" + encoded;
    }

    private String buildOrderMessage(Order order) {
        StringBuilder sb = new StringBuilder("Olá! Gostaria de confirmar meu pedido:\n\n");
        for (OrderItem item : order.getItems()) {
            sb.append(item.getQuantity())
                    .append("x ")
                    .append(item.getItemName())
                    .append(" - R$ ")
                    .append(item.getUnitPrice())
                    .append("\n");
        }
        sb.append("\nTotal: R$ ").append(order.getTotal());
        sb.append("\n\nNome: ").append(order.getCustomerName());
        sb.append("\nTelefone: ").append(order.getCustomerPhone());
        sb.append("\nEndereço: ").append(order.getCustomerAddress());
        return sb.toString();
    }

    private String digitsOnly(String phone) {
        return phone.replaceAll("\\D", "");
    }
}