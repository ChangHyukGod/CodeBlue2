package com.simplecoding.simpledms.controller.auth;

import com.simplecoding.simpledms.service.auth.MemberService;
import com.simplecoding.simpledms.vo.auth.Member;
import com.simplecoding.simpledms.vo.dto.UserReqDto;
import com.simplecoding.simpledms.vo.dto.UserResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : KTE
 * @fileName : MemberController
 * @since : 24. 11. 11.
 * description :
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/api/auth/register")
    public ResponseEntity<?> insert(@RequestBody Member member) {

        memberService.insert(member);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    // 로그인
    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody UserReqDto userReqDto) {

        UserResDto userResDto = memberService.login(userReqDto);

        return new ResponseEntity<>(userResDto, HttpStatus.OK);
    }

    // 이메일 찾기
    @PostMapping("/api/auth/find-email")
    public ResponseEntity<String> findEmail(@RequestBody Member member) {

        // 이메일 찾기 서비스 호출
        String email = memberService.findEmail(member);
        if (email != null) {
            return ResponseEntity.ok(email); // 이메일 반환
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("이메일을 찾을 수 없습니다."); // 실패 응답
    }

    // 비밀번호 찾기
    @PostMapping("/api/auth/find-password")
    public ResponseEntity<String> findPassword(@RequestBody Member member) {

        // 비밀번호 찾기 서비스 호출
        String password = memberService.findPassword(member);
        if (password != null) {
            return ResponseEntity.ok(password); // 이메일 반환
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("비밀번호를 찾을 수 없습니다.");
    }

}
