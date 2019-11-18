package com.cap.demo.cbd.web.rest;

import java.util.List;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cap.demo.cbd.message.OutboundChannel;
import com.cap.demo.cbd.model.Product;
import com.cap.demo.cbd.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProductController {

	private MessageChannel outboundChannelStream;
	private ProductRepository productRepository;

	public ProductController(OutboundChannel outboundChannel, ProductRepository productRepository) {
		this.outboundChannelStream = outboundChannel.outgoing();
		this.productRepository = productRepository;
	}

	@PostMapping("/cbd-msg-service/products")
	public void registerNewProduct(@RequestBody Product product) {
		log.info("Received new product {}", product);
		outboundChannelStream.send(MessageBuilder.withPayload(product).build());
		log.info("Received new product {} is pushed to outbound channel", product);
	}

	@GetMapping("/cbd-msg-service/products")
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	@GetMapping("/cbd-msg-service/products/{id}")
	public Product getAllProduct(@PathVariable Long id) {
		return productRepository.findById(id).get();
	}
}
