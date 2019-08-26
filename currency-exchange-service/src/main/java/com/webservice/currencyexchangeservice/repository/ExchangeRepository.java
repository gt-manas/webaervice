package com.webservice.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webservice.currencyexchangeservice.vo.ExchangeValue;

public interface ExchangeRepository extends JpaRepository<ExchangeValue, Long> {

	ExchangeValue findByFromAndTo(String from, String to);

}
