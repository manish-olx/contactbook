package com.myproject.contactbook.mapper;

import com.myproject.contactbook.domain.entity.UserContactEntity;
import com.myproject.contactbook.pojo.response.userContact.UserContactData;
import org.springframework.stereotype.Component;

@Component
public class UserContactDataEntityMapper extends Mapper<UserContactEntity, UserContactData> {

    @Override
    public UserContactData map(UserContactEntity userContactEntity) {
        UserContactData userContactData = new UserContactData();

        userContactData.setId(userContactEntity.getId());
        userContactData.setName(userContactEntity.getName());
        userContactData.setPassword(userContactEntity.getPassword());
        userContactData.setEmail(userContactEntity.getEmail());
        userContactData.setPhone(userContactEntity.getPhone());
        userContactData.setCreatedDate(userContactEntity.getCreatedDate());
        userContactData.setModifiedDate(userContactEntity.getModifiedDate());
        userContactData.setStatus(userContactEntity.getStatus());
        return userContactData;
    }
}
