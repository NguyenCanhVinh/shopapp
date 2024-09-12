package com.example.shopapp.Services;

import com.example.shopapp.components.jwtTokenUtil;
import com.example.shopapp.dto.UserDTO;
import com.example.shopapp.exception.DataNotFoundException;
import com.example.shopapp.models.Role;
import com.example.shopapp.models.User;
import com.example.shopapp.repositories.RoleRepository;
import com.example.shopapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private  final jwtTokenUtil jwtTokenUtil;
  private  final AuthenticationManager authenticationManager;

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
    if (userDTO.getFacebookAccountId()==0 && userDTO.getGoogleAccountId()==0){
      String password= userDTO.getPassword();
      String encodePasword= passwordEncoder.encode(password);
      newUser.setPassword(encodePasword);

    }
    return userRepository.save(newUser);
  }

  @Override
  public String login(String phoneNumber, String password) throws Exception {
    Optional<User> optionalUser =userRepository.findByPhoneNumber(phoneNumber);
    if (optionalUser.isEmpty()){
      throw new DataNotFoundException("invalid phonenumber/ password");
    }
    User existingUser= optionalUser.get();

    if (existingUser.getFacebookAccountId() == 0 && existingUser.getGoogleAccountId()==0) {

      if (!passwordEncoder.matches(password, existingUser.getPassword())){
        throw  new BadCredentialsException("wrong phone number or password");
      }

    }
      //check password
    UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(
      phoneNumber, password, existingUser.getAuthorities());
    //authenticate with java spring sercurity
    authenticationManager.authenticate(authenticationToken);
    return jwtTokenUtil.generateToken(optionalUser.get());
  }
}

