package com.asaitec.service;

import com.asaitec.domain.Product;
import com.asaitec.domain.offers.AppleOffer;
import com.asaitec.domain.offers.Offer;
import com.asaitec.repository.OfferRepository;
import com.asaitec.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DatabaseService {

    private final ProductRepository productRepository;

    private final OfferRepository offerRepository;

    public DatabaseService(ProductRepository productRepository, OfferRepository offerRepository) {
        this.productRepository = productRepository;
        this.offerRepository = offerRepository;
    }

    public void populateDatabase() {
        Product product = new Product("Apple", 1.20);
        Product product1 = new Product("Orange", 1.50);
        Product product2 = new Product("Pear", 1.65);

        Offer appleOffer = new AppleOffer("Apple Offer", "Buy 3 apples and pay 2", 1, 3);

        offerRepository.saveAll(Arrays.asList(appleOffer));
        productRepository.saveAll(Arrays.asList(product, product1, product2));
    }
}
