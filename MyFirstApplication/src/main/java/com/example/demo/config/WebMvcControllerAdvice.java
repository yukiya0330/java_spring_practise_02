package com.example.demo.config;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.example.demo.service.InquiryNotFoundExeption;

@ControllerAdvice
public class WebMvcControllerAdvice {

	/*
	 * This method changes empty character to null
	 * こちらのメソッドを用意しておくと送信された空文字はnull に変換されます
	 */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String handleExeption(EmptyResultDataAccessException e, Model model) {
    	model.addAttribute("messege", e);
    	return "error/CustomPage";
    }

	@ExceptionHandler(InquiryNotFoundExeption.class)
	public String handlExeption(InquiryNotFoundExeption e, Model model) {
		model.addAttribute("message", e);
		return "error/CustomPage";
	}

}