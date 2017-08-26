package com.rest.regexp.repository;

import com.rest.regexp.model.Contact;
import com.rest.regexp.repository.custom.ContactRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface ContactRepository extends JpaRepository<Contact, Long>, QueryDslPredicateExecutor,
    ContactRepositoryCustom {

}
