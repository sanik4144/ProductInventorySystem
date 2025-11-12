package com.anik.productInventorySystem.Services;

import com.anik.productInventorySystem.DTO.RegisterRequest;
import com.anik.productInventorySystem.Entities.Role;
import com.anik.productInventorySystem.Entities.User;
import com.anik.productInventorySystem.Repositories.RoleRepo;
import com.anik.productInventorySystem.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
//    @Autowired
//    PasswordEncoder passwordEncoder;

    public String registerUser(RegisterRequest request){
        if(userRepo.findByUsername(request.getUsername()).isPresent()){
            return "Username already taken";
        }

        Role role = roleRepo.findByName(request.getRoleName())
                        .orElseThrow(() -> new RuntimeException("Role Not Found"));


//        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User();
        user.setUsername(request.getUsername());
//        user.setPassword(encodedPassword);
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        userRepo.save(user);

        return "User Registered Successfully";
    }
}
