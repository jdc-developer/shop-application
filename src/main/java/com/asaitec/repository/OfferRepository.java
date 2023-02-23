package com.asaitec.repository;

import com.asaitec.domain.offers.Offer;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface OfferRepository extends JpaRepositoryImplementation<Offer, Integer> {
}
