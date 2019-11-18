package com.cap.demo.cbd.message;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.cap.demo.cbd.model.Product;
import com.cap.demo.cbd.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageSubscriber {

	private ProductRepository productRepository;

	public MessageSubscriber(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@StreamListener(Sink.INPUT)
	public void readMessage(Message<Product> productMsg) {
		Product payload = productMsg.getPayload();
		log.info("Received {} at subscriber end", payload);
		productRepository.save(payload);

	}

}
