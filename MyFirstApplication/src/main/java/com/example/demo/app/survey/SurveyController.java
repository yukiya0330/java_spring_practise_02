package com.example.demo.app.survey;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Survey;
import com.example.demo.service.SurveyService;

@Controller
@RequestMapping("/survey")
public class SurveyController {

	private final SurveyService surveyService;

	@Autowired
	public SurveyController(SurveyService surveyService) {
		this.surveyService = surveyService;
	}

	@GetMapping
	public String inddex(Model model) {
		List<Survey> list = surveyService.getAll();

		model.addAttribute("surveyList", list);
		model.addAttribute("title", "Survey Index");

		return "survey/index";
	}

	@GetMapping("/form")
	public String form(SurveyForm surveyform,
			Model model,
			@ModelAttribute("complete") String complete) {
		model.addAttribute("title", "Survey Form");
		return "survey/form";
	}

	@PostMapping("/form")
	public String formGoBack(SurveyForm surveyform, Model model) {
		model.addAttribute("title", "Inquiry Form");
		return "survey/form";
	}

	@PostMapping("/confirm")
	public String confirm(@Validated SurveyForm surveyForm,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			model.addAttribute("title", "survey Form");
		return "survey/form";
		}
		model.addAttribute("title", "confirm Page");
		return "survey/confirm";
	}

	@PostMapping("/complete")
	public String complete(@Validated SurveyForm surveyForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectattributes) {
		if(result.hasErrors()) {
			model.addAttribute("title", "SurveyForm");
			return "survey/form";
		}
		Survey survey = new Survey();
		survey.setAge(surveyForm.getAge());
		survey.setSatisfaction(surveyForm.getSatisfaction());
		survey.setComment(surveyForm.getComment());

		surveyService.save(survey);
		redirectattributes.addAttribute("complete", "Resisterd!");
		return "redirect:/survey/form";
	}

}
