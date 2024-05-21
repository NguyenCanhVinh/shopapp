package com.example.shopapp.Services;

import com.example.shopapp.dto.UserDTO;
import com.example.shopapp.exception.DataNotFoundException;
import com.example.shopapp.models.User;

public interface UserService {

  User createUser(UserDTO userDTO) throws DataNotFoundException;

  String login(String phoneNumber, String password);
}
