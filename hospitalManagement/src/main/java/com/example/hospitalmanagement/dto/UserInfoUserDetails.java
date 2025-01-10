package com.example.hospitalmanagement.dto;
import com.example.hospitalmanagement.model.AppUser;
import com.example.hospitalmanagement.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserInfoUserDetails implements UserDetails {


    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoUserDetails(AppUser appUser, List<UserRole> userRoles) {
        this.username = appUser.getUserName();
        this.password = appUser.getEncryptedPassword();
        this.authorities = new ArrayList<>();

        // Nếu userRoles là null hoặc trống, có thể thêm một quyền mặc định như "ROLE_USER"
        if (userRoles != null && !userRoles.isEmpty()) {
            for (UserRole userRole : userRoles) {
                // ROLE_USER, ROLE_ADMIN,..
                authorities.add(new SimpleGrantedAuthority(userRole.getAppRole().getRoleName()));
            }
        } else {
            // Thêm quyền mặc định nếu không có role nào
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
