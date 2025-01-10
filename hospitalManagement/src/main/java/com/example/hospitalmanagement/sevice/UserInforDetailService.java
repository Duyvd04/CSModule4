package com.example.hospitalmanagement.sevice;

import com.example.hospitalmanagement.dto.UserInfoUserDetails;
import com.example.hospitalmanagement.model.AppUser;
import com.example.hospitalmanagement.model.UserRole;
import com.example.hospitalmanagement.repository.IUserRepository;
import com.example.hospitalmanagement.repository.IUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInforDetailService implements UserDetailsService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IUserRoleRepository iUserRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find the user by username
        AppUser appUser = iUserRepository.findByUserName(username);
        if (appUser == null) {
            // Throw an exception if the user is not found
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Get all roles for the AppUser
        List<UserRole> userRoles = iUserRoleRepository.findAllByAppUser(appUser);

        // Create and return UserInfoUserDetails
        return new UserInfoUserDetails(appUser, userRoles);
    }
}



