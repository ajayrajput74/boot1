package com.shared.Dao;

import org.springframework.stereotype.Component;

import com.shared.Dto.UserDTO;

@Component
public interface UserDAO {

	UserDTO getUserByUserName(String userName);

	UserDTO getUserByUserId(Integer clientUserId);

}
