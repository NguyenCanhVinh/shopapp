package com.example.shopapp.Services;

import com.example.shopapp.dto.UserDTO;
import com.example.shopapp.exception.DataNotFoundException;
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
  public User createUser(UserDTO userDTO) throws DataNotFoundException {
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
      .orElseThrow(() -> new DataNotFoundException("role not foud"));
    newUser.setRole(role);
    if (userDTO.getFacebookAccountId()==0 && userDTO.getFacebookAccountId()==0){
      String password= userDTO.getPassword();
//      String encodePasword= passwordEncode.encode(password);
//      newUser.setPassword(encodePasword);

    }
    return userRepository.save(newUser);
  }

  @Override
  public String login(String phoneNumber, String password) {
    return null;
  }
}

