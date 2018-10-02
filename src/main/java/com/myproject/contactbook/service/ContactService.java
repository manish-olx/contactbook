package com.myproject.contactbook.service;

import com.google.common.collect.ImmutableMap;
import com.myproject.contactbook.domain.entity.UserContactEntity;
import com.myproject.contactbook.domain.factory.UserContactFactory;
import com.myproject.contactbook.exception.ContactAlreadyExistsException;
import com.myproject.contactbook.exception.ContactNotFoundException;
import com.myproject.contactbook.pojo.request.UserContactPayload;
import com.myproject.contactbook.pojo.response.PageInfo;
import com.myproject.contactbook.pojo.response.userContact.UserContactActionData;
import com.myproject.contactbook.pojo.response.userContact.UserContactData;
import com.myproject.contactbook.pojo.response.userContact.UserContactItemResponse;
import com.myproject.contactbook.pojo.response.userContact.UserContactListResponse;
import com.myproject.contactbook.pojo.response.userContact.UserContactResponse;
import com.myproject.contactbook.util.LoggerUtil;
import com.myproject.contactbook.util.PropertyUtil;
import com.myproject.contactbook.util.Serializer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ContactService {
    @Autowired
    private UserContactFactory userContactFactory;
    @Autowired
    private LoggerUtil loggerUtil;
    @Autowired
    private Serializer serializer;
    @Autowired
    private ErrorService errorService;
    @Autowired
    private ContactAlreadyExistsException contactAlreadyExistsException;
    @Autowired
    private ContactNotFoundException contactNotFoundException;
    @Autowired
    private PropertyUtil propertyUtil;

    public UserContactResponse addContact(UserContactPayload userContactPayload) {
        UserContactResponse userContactResponse = new UserContactResponse();
        UserContactActionData userContactActionData = new UserContactActionData();
        try {
            if (userContactFactory.getByEmail(userContactPayload.getEmail()) == null
                    && userContactFactory.save(0, userContactPayload) != null) {
                userContactActionData.setMessage("Contact added successfully");
            } else {
                throw contactAlreadyExistsException;
            }
            userContactResponse.setData(userContactActionData);
        } catch (ContactAlreadyExistsException e) {
            loggerUtil.error(ImmutableMap.of(
                    "message", e.getMessage(),
                    "exceptionClass", e.getClass()
            ));
            userContactResponse.setError(errorService.setErrorMessage(e.getMessage()));
        } catch (ContactNotFoundException e) {
            loggerUtil.error(ImmutableMap.of(
                    "message", e.getMessage(),
                    "exceptionClass", e.getClass()
            ));
            userContactResponse.setError(errorService.setErrorMessage(e.getMessage()));
        }
        return userContactResponse;
    }

    public UserContactItemResponse getContactItemDetail(int id) {
        UserContactItemResponse userContactItemResponse = new UserContactItemResponse();
        UserContactEntity userContactEntity = new UserContactEntity();
        List<UserContactData> userContactDataList = new ArrayList<UserContactData>();
        UserContactData userContactData = new UserContactData();

        try {
            userContactEntity = userContactFactory.getById(id);

            if (userContactEntity == null) {
                throw contactNotFoundException;
            }

            BeanUtils.copyProperties(userContactEntity, userContactData, propertyUtil.getNullPropertyNames(userContactEntity));
            userContactDataList.add(userContactData);
            userContactItemResponse.setData(userContactDataList);
        } catch (ContactNotFoundException e) {
            loggerUtil.error(ImmutableMap.of(
                    "message", e.getMessage(),
                    "exceptionClass", e.getClass()
            ));
            userContactItemResponse.setError(errorService.setErrorMessage(e.getMessage()));
        }
        return userContactItemResponse;
    }

    public UserContactListResponse searchContact(Pageable pageable, String email, String name) {
        UserContactListResponse userContactListResponse = new UserContactListResponse();

        try {
            PageInfo pageInfo = new PageInfo();
            Page<UserContactData> pagedUserContactData = userContactFactory.searchContact(pageable, pageInfo, email, name);

            userContactListResponse.setData(pagedUserContactData);
            if (pagedUserContactData != null && pageInfo != null) {
                UserContactListResponse.MetaWrapper metaWrapper = new UserContactListResponse.MetaWrapper();
                metaWrapper.setPageInfo(pageInfo);
                userContactListResponse.setMetadata(Arrays.asList(metaWrapper));
            }

        } catch (Exception e) {
            loggerUtil.error(ImmutableMap.of(
                    "message", e.getMessage(),
                    "exceptionClass", e.getClass()
            ));
            userContactListResponse.setError(errorService.setErrorMessage("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR));

        }
        return userContactListResponse;
    }

    public UserContactResponse deleteContact(int id) {
        UserContactResponse userContactResponse = new UserContactResponse();
        UserContactActionData userContactActionData = new UserContactActionData();
        UserContactEntity userContactEntity = new UserContactEntity();

        try {
            userContactFactory.delete(id);
            userContactActionData.setMessage("Contact deleted successfully");
            userContactResponse.setData(userContactActionData);
        } catch (ContactNotFoundException e) {
            loggerUtil.error(ImmutableMap.of(
                    "message", e.getMessage(),
                    "exceptionClass", e.getClass()
            ));
            userContactResponse.setError(errorService.setErrorMessage(e.getMessage()));
        } catch (Exception e) {
            loggerUtil.error(ImmutableMap.of(
                    "message", e.getMessage(),
                    "exceptionClass", e.getClass()
            ));
            userContactResponse.setError(errorService.setErrorMessage("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR));
        }
        return userContactResponse;

    }

    public UserContactResponse editContactItemDetail(int id, UserContactPayload userContactPayload) {
        UserContactResponse userContactResponse = new UserContactResponse();
        UserContactActionData userContactActionData = new UserContactActionData();

        try {
            UserContactEntity userContactEntity = userContactFactory.getById(id);
            UserContactEntity userContactEntity1
                    = userContactFactory.getByEmail(userContactPayload.getEmail());

            if (userContactEntity1 != null
                    && userContactEntity1.getEmail() != userContactEntity.getEmail()) {
                throw contactAlreadyExistsException;
            } else if (userContactFactory.save(id, userContactPayload) != null) {
                userContactActionData.setMessage("User contact modified successfully");
            }
            userContactResponse.setData(userContactActionData);
        } catch (ContactNotFoundException | ContactAlreadyExistsException e) {
            loggerUtil.error(ImmutableMap.of(
                    "message", e.getMessage(),
                    "exceptionClass", e.getClass()
            ));
            userContactResponse.setError(errorService.setErrorMessage(e.getMessage()));
        }
        return userContactResponse;
    }
}
