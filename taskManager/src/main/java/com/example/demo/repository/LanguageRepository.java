package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Language;

public interface LanguageRepository extends CrudRepository<Language, Integer> {

}
