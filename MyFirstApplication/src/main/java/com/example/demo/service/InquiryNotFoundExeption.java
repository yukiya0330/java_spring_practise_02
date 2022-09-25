package com.example.demo.service;

public class InquiryNotFoundExeption extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InquiryNotFoundExeption(String messege) {
		super(messege);
	}

}
