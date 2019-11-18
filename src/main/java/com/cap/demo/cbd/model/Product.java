package com.cap.demo.cbd.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Product {
	
	private Long id;
	private String name;

}
