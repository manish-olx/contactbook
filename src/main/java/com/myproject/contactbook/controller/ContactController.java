package com.myproject.contactbook.controller;

import com.myproject.contactbook.pojo.request.UserContactPayload;
import com.myproject.contactbook.service.ContactService;
import com.myproject.contactbook.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;
    @Autowired
    private ApiResponse apiResponse;


    @PostMapping("${controller.path.contact}")
    public ResponseEntity<?> addContact(@Validated @RequestBody UserContactPayload userContactPayload) {
        return apiResponse.send(contactService.addContact(userContactPayload));
    }

    @GetMapping("${controller.path.contactItem}")
    public ResponseEntity<?> getContactItemDetail(@PathVariable int id) {
        return apiResponse.send(contactService.getContactItemDetail(id));
    }

    @GetMapping("${controller.path.contactSearch}")
    public ResponseEntity<?> searchContact(@PageableDefault(value = 10, page = 0)Pageable pageable,
                                           @RequestParam(value = "email", required = false) String email,
                                           @RequestParam(value = "name", required = false) String name)
    {
        return apiResponse.send(contactService.searchContact(pageable, email, name));
    }

    @DeleteMapping("${controller.path.contactItem}")
    public ResponseEntity<?> deleteContact(@PathVariable int id) {
        return apiResponse.send(contactService.deleteContact(id));
    }

    @PutMapping("${controller.path.contactItem}")
    public ResponseEntity<?> editContactItemDetail(@PathVariable int id,
                                                   @Validated @RequestBody UserContactPayload userContactPayload)
    {
        return apiResponse.send(contactService.editContactItemDetail(id, userContactPayload));
    }

}
