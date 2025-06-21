package steam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import steam.model.User;
import steam.repository.UserRepository;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        System.out.println("로그인 시도된 userId: " + userId); //  로그 확인
        return userRepository.findByUserId(userId)
                .map(user -> {
                    System.out.println("사용자 존재함: " + user.getUserId()); //  존재 로그
                    
                    System.out.println("찾은 유저: " + user.getUserId());
                    System.out.println("비밀번호: " + user.getPassword());

                    return new org.springframework.security.core.userdetails.User(
                            user.getUserId(),
                            user.getPassword(),
                            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                    );
                })
                .orElseThrow(() -> new UsernameNotFoundException("❌ 사용자를 찾을 수 없습니다: " + userId));
    }
}

