package com.example.transmissionproject.Services;

import com.example.transmissionproject.Model.Admin;
import com.example.transmissionproject.PassEncoder;
import com.example.transmissionproject.Repository.AdminRepository;
import com.example.transmissionproject.Request.AdminRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final PassEncoder encodes;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminRepository.findByAdminUserid(username).orElseThrow(()->
                new UsernameNotFoundException("Incorrect details"));
    }
}
