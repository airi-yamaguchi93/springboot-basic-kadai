package com.example.springkadaiform.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaiform.form.ContactForm;

@Controller
public class ContactFormController {

    private static final Logger log = LoggerFactory.getLogger(ContactFormController.class);

    
    @GetMapping("/form")
    public String showForm(Model model) {

        if (!model.containsAttribute("contactForm")) {
            model.addAttribute("contactForm", new ContactForm());
        }
        log.info("★ GET /form Model keys = {}", model.asMap().keySet());
        return "contactFormView";
    }

    @PostMapping("/form")
    public String submitForm(
            @Validated @ModelAttribute("contactForm") ContactForm contactForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (bindingResult.hasErrors()) {

        	
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.contactForm", bindingResult);
            redirectAttributes.addFlashAttribute("contactForm", contactForm);
            log.info("★ POST /form -> NG errors={}", bindingResult.getAllErrors());
            return "redirect:/form";
        }

        log.info("★ POST /form -> OK 確認画面へ");
        model.addAttribute("contactForm", contactForm);
        return "confirmView";
    }
}