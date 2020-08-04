package com.intelligent.controller;

import com.intelligent.model.Language;
import com.intelligent.service.LanguagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LanguageController {
    @Autowired
    public LanguagesService languageService;

    @RequestMapping(value = "languages",method = RequestMethod.GET)
    public List<Language> getLanguages() {
        return languageService.getLanguages();
    }
}
