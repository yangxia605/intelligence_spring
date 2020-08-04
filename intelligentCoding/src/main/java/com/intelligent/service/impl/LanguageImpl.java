package com.intelligent.service.impl;
import com.intelligent.dao.LanguageDao;
import com.intelligent.model.Language;
import com.intelligent.service.LanguagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageImpl implements LanguagesService {
    @Autowired
    private LanguageDao languageDao;
    public List<Language> getLanguages(){
        return languageDao.findAll();
    }
}
