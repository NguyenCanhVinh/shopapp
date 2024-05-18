package com.example.shopapp.Services;

import com.example.shopapp.dto.UserDTO;
import com.example.shopapp.models.User;

public interface UserService {

  User createUser(UserDTO userDTO);

  String login(String phoneNumber, String password);
}
