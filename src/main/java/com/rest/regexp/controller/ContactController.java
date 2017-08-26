package com.rest.regexp.controller;

import com.rest.regexp.dto.ContactList;
import com.rest.regexp.service.ContactService;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class ContactController {

  private ContactService contactService;

  @Autowired
  public void setContactService(ContactService contactService) {
    this.contactService = contactService;
  }

  @PostMapping(value = "/hello/contacts", produces = "application/json")
  public DeferredResult<ContactList> getContactsByNameRegexp(
      @RequestParam(name = "nameFilter") String nameFilter) {
    DeferredResult<ContactList> deferredResult = new DeferredResult<>();
    CompletableFuture a = CompletableFuture
        .supplyAsync(() -> contactService.getContactsByNotMatchNamePattern(nameFilter))
        .whenCompleteAsync((result, throwable) ->
            {
              if (throwable != null) {
                deferredResult.setErrorResult(throwable);
              } else {
                deferredResult.setResult(result);
              }
            }
        );
    return deferredResult;
  }
}
