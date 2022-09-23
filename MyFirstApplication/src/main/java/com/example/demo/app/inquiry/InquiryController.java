package com.example.demo.app.inquiry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {

	@GetMapping("/form")
	public String form(InquiryForm inquiryForm,
			Model model,
			@ModelAttribute("complete") String complete) {
		model.addAttribute("title", "Inquiry Form");
		return "inquiry/form";
	}

	@PostMapping("/form")
	public String formGoBack(InquiryForm inquiryForm, Model model) {
		model.addAttribute("title", "Inquiry Form");
		return "inquiry/form";
	}


	@PostMapping("/confirm")
	public String confirm(@Validated InquiryForm inquiryForm,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			model.addAttribute("title", "inquiry Form");
		return "inquiry/form";
		}
		model.addAttribute("title", "confirm Page");
		return "inquiry/confirm";
	}

	@PostMapping("/complete")
	public String complete(@Validated InquiryForm inquiryForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectattributes) {
		if(result.hasErrors()) {
			model.addAttribute("title", "InquiryForm");
			return "inquiry/form";
		}
		redirectattributes.addFlashAttribute("complete", "Resisterd!");
		return "redirect:/inquiry/form";
	}

}
