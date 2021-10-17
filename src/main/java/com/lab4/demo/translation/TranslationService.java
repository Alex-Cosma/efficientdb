package com.lab4.demo.translation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class TranslationService {

    private final TranslationRepository translationRepository;

    public Translation findByCodeAndLanguage(String code, String language) {
        return translationRepository.findByCodeAndLanguage(code, language)
                .orElseThrow(() -> new EntityNotFoundException(
                        format("Translation with code %s and language %s not found", code, language))
                );
    }

}
