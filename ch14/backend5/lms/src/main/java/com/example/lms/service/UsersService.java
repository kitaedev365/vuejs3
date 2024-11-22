package com.example.lms.service;

import com.example.lms.entity.Users;
import com.example.lms.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    // 회원 목록 조회
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    // 회원 정보 조회 by ID
    public Optional<Users> getUserById(Long id) {
        return usersRepository.findById(id);
    }

    // 로그인 기능 (username과 password 체크)
    public Optional<Users> login(String username, String password) {
        return usersRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password)); // 단순 비교, 실제로는 비밀번호 암호화 필요
    }

    // 아이디 중복 확인
    public boolean isUsernameTaken(String username) {
        return usersRepository.existsByUsername(username);
    }

    // 이메일 중복 확인
    public boolean isEmailTaken(String email) {
        return usersRepository.existsByEmail(email);
    }

    // 회원 가입
    public Users registerUser(Users user) {
        return usersRepository.save(user);
    }

    // 회원 정보 수정
    @Transactional
    public Optional<Users> updateUser(Long id, Users updatedUser) {
        return usersRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword()); // 비밀번호 암호화 필요
            user.setEmail(updatedUser.getEmail());
            user.setName(updatedUser.getName());
            user.setRole(updatedUser.getRole());
            return user;
        });
    }

    // 회원 탈퇴
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

	public Optional<Users> getUserByUsername(String username) {
		return usersRepository.findByUsername(username);
	}
}