package com.mycompany.springframework.dto;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch04LoginFormValidator implements Validator {

	// loginForm dto면 true리턴 아니면 false 리턴
	@Override
	public boolean supports(Class<?> clazz) {
		log.info("실행");
		boolean result = Ch04LoginForm.class.isAssignableFrom(clazz); // 검사코드를 안넣어도 상관없는이유
		return result;			
	}

	// 실제로 유효성을 검사하는 메소드
	// supports가 true를 리턴하는 경우에만 실행된다
	@Override
	public void validate(Object target, Errors errors) {
		log.info("실행");
		Ch04LoginForm loginForm = (Ch04LoginForm) target;
		
		//mid 검사
		String mid = loginForm.getMid();
		
		if(mid == null || mid.equals("")){
			errors.rejectValue("mid", "errors.mid.required");
		} else if(mid.length() < 6 || mid.length() > 12) {
			errors.rejectValue("mid", "errors.mid.length", new Object[]{"6", "12"}, null);
		}
		
		
		//mpassword 검사
		String mpassword = loginForm.getMpassword();
		String pattern = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}";
		
		if(mpassword == null || mpassword.equals("")){
			errors.rejectValue("mpassword", "errors.mpassword.required");
		} else if(mpassword.length() < 8 || mpassword.length() > 15) {
			errors.rejectValue("mpassword", "errors.mpassword.length", new Object[]{"8", "15"}, null);
		} else if(!Pattern.matches(pattern, mpassword)){
			errors.rejectValue("mpassword", "errors.mpassword.wrongchar");
		} 
	}

}
