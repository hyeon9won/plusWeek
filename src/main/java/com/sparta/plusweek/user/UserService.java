package com.sparta.plusweek.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void signup(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = passwordEncoder.encode(userRequestDto.getPassword());

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("This username is already used.");
        }

        if (isPasswordValid(userRequestDto)) {
            throw new IllegalArgumentException("Password cannot be contain the username.");
        }

        if (!isPasswordConfirmed(userRequestDto)) {
            throw new IllegalArgumentException("Password does not match.");
        }

        User user = new User(username, password);
        userRepository.save(user);

    }

    public void login(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User dose not exist."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Password does not match.");
        }
    }

    // 유저네임을 포함한 비밀번호 제외 - userService에서 유효성 검증
    public boolean isPasswordValid(UserRequestDto userRequestDto) {
        return userRequestDto.getPassword().contains(userRequestDto.getUsername());
    }

    // 비밀번호와 비밀번호 확인란이 정확하게 일치하는지 검토
    public boolean isPasswordConfirmed(UserRequestDto userRequestDto) {
        return userRequestDto.getPassword().equals(userRequestDto.getConfirmPassword());
    }
}