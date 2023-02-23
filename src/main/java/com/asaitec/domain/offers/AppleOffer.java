package com.asaitec.domain.offers;

import com.asaitec.domain.Order;
import com.asaitec.domain.OrderLine;
import com.asaitec.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Optional;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppleOffer extends Offer {

    private Integer appleId;
    private Integer appleOfferQuantity;

    public AppleOffer(String name, String description, Integer appleId, Integer appleOfferQuantity) {
        super(name, description);
        this.appleId = appleId;
        this.appleOfferQuantity = appleOfferQuantity;
    }

    @Override
    public void offerImplementation(Order order) {
        Optional<OrderLine> appleOrderLineOpt = order.getOrderLines().stream()
                .filter(orderLine -> orderLine.getProduct().getId().equals(appleId)).findFirst();

        appleOrderLineOpt.ifPresent(appleOrderLine -> {
            if (appleOrderLine.getAmount() >= appleOfferQuantity) {
                Product product = appleOrderLine.getProduct();
                Integer applesBoughtQuantity = appleOrderLine.getAmount();
                Integer extraApplesBought = Math.round(applesBoughtQuantity / appleOfferQuantity);
                order.decreaseValue(product.getPrice() * extraApplesBought);
            }
        });
    }
}
