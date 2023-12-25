package com.koustubh.eccomerce.controller;

import com.koustubh.eccomerce.configs.JwtProvider;
import com.koustubh.eccomerce.exception.UserException;
import com.koustubh.eccomerce.model.Cart;
import com.koustubh.eccomerce.model.User;
import com.koustubh.eccomerce.repository.UserRepository;
import com.koustubh.eccomerce.request.LoginRequest;
import com.koustubh.eccomerce.response.AuthResponse;
import com.koustubh.eccomerce.service.CartService;
import com.koustubh.eccomerce.service.CustomUserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserServiceImplementation customUserServiceImplementation;
    @Autowired
    private CartService cartService;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException{
        String email = user.getEmail();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName  = user.getLastName();

        User isEmailExist = userRepository.findByEmail(email);

        // check wheter there is any existing user with same email
        if(isEmailExist != null){
            throw new UserException("Email already used by another account");
        }

        User createNewUser = new User();
        createNewUser.setEmail(email);
        createNewUser.setPassword(passwordEncoder.encode(password));
        createNewUser.setFirstName(firstName);
        createNewUser.setLastName(lastName);
        createNewUser.setCreatedAt(LocalDateTime.now());

        // save user into database
        User savedUser = userRepository.save(createNewUser);

        // create cart for newly created user
        Cart cart = cartService.createCart(savedUser);

        // Create new users authentication object and set into security context
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Signup Success");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) throws UserException{
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        System.out.println(email);
        System.out.println(password);

        Authentication authentication = authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse(token,"Login Success");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customUserServiceImplementation.loadUserByUsername(email);

        if(userDetails == null){
            throw new BadCredentialsException("Invalid Username ...");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password ...");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
