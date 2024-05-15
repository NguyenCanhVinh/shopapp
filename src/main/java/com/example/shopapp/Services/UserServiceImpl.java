package com.example.shopapp.Services;

import com.example.shopapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;

  public UserServiceImpl( UserRepository userRepository) {
    this.userRepository = userRepository;
  }
}
