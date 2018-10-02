package com.myproject.contactbook.domain.factory;

import com.myproject.contactbook.configuration.ApplicationConfig;
import com.myproject.contactbook.domain.entity.UserContactEntity;
import com.myproject.contactbook.domain.repository.UserContactRepository;
import com.myproject.contactbook.exception.ContactNotFoundException;
import com.myproject.contactbook.mapper.UserContactDataEntityMapper;
import com.myproject.contactbook.pojo.request.UserContactPayload;
import com.myproject.contactbook.pojo.response.PageInfo;
import com.myproject.contactbook.pojo.response.userContact.UserContactData;
import com.myproject.contactbook.util.DateUtil;
import com.myproject.contactbook.util.PropertyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserContactFactory {

    @Autowired
    private UserContactRepository userContactRepository;
    @Autowired
    private DateUtil dateUtil;
    @Autowired
    private ApplicationConfig applicationConfig;
    @Autowired
    private ContactNotFoundException contactNotFoundException;
    @Autowired
    private PropertyUtil propertyUtil;
    @Autowired
    private UserContactDataEntityMapper userContactDataEntityMapper;

    public UserContactEntity getById(int id) throws ContactNotFoundException {
        Optional<UserContactEntity> userContactEntity = userContactRepository.findById(id);
        userContactEntity.orElseThrow(() -> contactNotFoundException);
        return userContactEntity.get();
    }

    public UserContactEntity getByEmail(String email) {
        UserContactEntity  userContactEntity = userContactRepository.findByEmail(email);
        return userContactEntity;
    }

    public UserContactEntity save(int id, UserContactPayload userContactPayload) throws ContactNotFoundException {
        UserContactEntity userContactEntity = new UserContactEntity();
        try {
            if (id > 0) {
                userContactEntity = getById(id);
            }
            BeanUtils.copyProperties(userContactPayload, userContactEntity, propertyUtil.getNullPropertyNames(userContactPayload));

            if (id <= 0) {
                //In case of Adding
                userContactEntity.setCreatedDate(dateUtil.getDate());
            }
            userContactEntity.setModifiedDate(dateUtil.getDate());

        } catch (ContactNotFoundException e) {
            throw contactNotFoundException;
        }
        return userContactRepository.save(userContactEntity);
    }

    public Page<UserContactData> searchContact(Pageable pageable, PageInfo pageInfo, String email, String name) {

        Page<UserContactEntity> userContactPagedObjects = null;

        if ((email != null && !email.isEmpty()) && (name != null && !name.isEmpty())) {
            userContactPagedObjects = userContactRepository.findByEmailAndName(pageable, email, name);
        } else if ((email != null && !email.isEmpty()) && (name == null || name.isEmpty())) {
            userContactPagedObjects = userContactRepository.findByEmail(pageable, email);
        } else if ((email == null || email.isEmpty()) && (name != null && !name.isEmpty())) {
            userContactPagedObjects = userContactRepository.findByName(pageable, name);
        } else {
            userContactPagedObjects = userContactRepository.findAll(pageable);
        }
        pageInfo.updatePageInfo(userContactPagedObjects, pageable);

        // Generate Inventory DTO from Query Results
        List<UserContactData> userContactList = userContactPagedObjects.getContent().stream()
                .map(this::getUserContactData).collect(Collectors.toList());

        return new PageImpl<>(userContactList);
    }

    private UserContactData getUserContactData(UserContactEntity userContactEntity) {
        return userContactDataEntityMapper.map(userContactEntity);
    }


    public void delete(int id) throws ContactNotFoundException {
        UserContactEntity userContactEntity = new UserContactEntity();
        try {
            if (id > 0) {
                userContactEntity = getById(id);
            }

            userContactRepository.delete(userContactEntity);
        } catch (ContactNotFoundException e) {
            throw contactNotFoundException;
        }
    }
}
