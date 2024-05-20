package com.example.shopapp.Services;

import com.example.shopapp.dto.UserDTO;
import com.example.shopapp.models.Role;
import com.example.shopapp.models.User;
import com.example.shopapp.repositories.RoleRepository;
import com.example.shopapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  @Override
  public User createUser(UserDTO userDTO) {
    String phoneNumber= userDTO.getPhoneNumber();
    if (userRepository.existsByPhoneNumber(phoneNumber)){
      throw  new DataIntegrityViolationException("phone number khong ton tai!");
    }
    //conver từ userDto sang user
    User newUser= User.builder()
      .fullName(userDTO.getFullName())
      .phoneNumber(userDTO.getPhoneNumber())
      .password(userDTO.getPassword())
      .address(userDTO.getAddress())
      .dateOfBirth(userDTO.getDateBirth())
      .facebookAccountId(userDTO.getFacebookAccountId())
      .googleAccountId(userDTO.getGoogleAccountId())
      .build();
    Role role= roleRepository.findById(userDTO.getRoleId())
      .orElseThrow(() ->)
    return null;
  }

  @Override
  public String login(String phoneNumber, String password) {
    return null;
  }
}

