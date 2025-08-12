package com.example.demo.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.demo.entity.*;

public class CustomUserDetails implements UserDetails{
    private final User user;
    @Autowired
    public CustomUserDetails(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Đây là phần cốt lõi
        Set<GrantedAuthority> authorities = new HashSet<>();
        
        // Lấy các quyền (permissions) từ các vai trò (roles) của user
        // và thêm chúng vào danh sách authorities
        user.getRoles().forEach(role -> {
            // Thêm vai trò (ví dụ: "ROLE_ADMIN")
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())); 
            
            // Thêm các quyền hạn cụ thể (ví dụ: "PRODUCT_WRITE")
            role.getPermissions().forEach(permission -> {
                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            });
        });

        return authorities;
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // Các phương thức isAccountNonExpired, isAccountNonLocked...
    // nên lấy trạng thái từ entity User
    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return user.isEnabled(); }
}
