package com.mycompany.springframework.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch05")
public class Ch05Controller {
	@GetMapping("/header")
	public String header(
			@RequestHeader("User-Agent") String userAgent, //헤더값을 얻는 방법1
			HttpServletRequest request,
			Model model) {
		log.info("userAgent: " + userAgent);
		
		//헤더값을 얻는 방법2
		userAgent = request.getHeader("User-Agent");
		
		String browser = null;
		if(userAgent.contains("Edg")) {
			browser = "Edg";
		} else if(userAgent.contains("Chrome")) {
			browser = "Chrome";
		}
		
		model.addAttribute("browser", browser);
		
		String clientIp = request.getRemoteAddr();
		log.info("clientIp: " + clientIp);
		model.addAttribute("clientIp", clientIp);
		
		model.addAttribute("chNum", "ch05");
		return "ch05/header";
	}
	
	@GetMapping("/createCookie")
	public String createCookie(HttpServletResponse response) {
		// 쿠키 생성
		Cookie cookie1 = new Cookie("mid", "user1");
//		cookie1.setMaxAge(60); // 클라이언트 파일 시스템에 저장하는 기간 설정(없으면 브라우저 메모리에 저장)
//		cookie1.setDomain(".samsung.com"); // *.samsung.com 이러면 쿠키 전송
//		cookie1.setSecure(true); // 브라우저에서 수정x, https에서만 쿠키 전송
//		cookie1.setHttpOnly(true); //http에서는 cookie를 수정/읽기 못함, https에서는 가능  / 브라우저의 자바스크립트로 쿠키에 접근을 허용하지 않음
		
		Cookie cookie2 = new Cookie("memail", "user1@mycompany.com");
		
		//setMaxAge
		// 쿠키를 응답헤더에 추가해서 브라우저로 보냄
		// 브라우저는 이 쿠키를 메모리, 파일시스템 중 하나에 저장한다
		// setMaxAge가 있으면 파일시스템에
		// 없으면 브라우저 메모리에 저장한다
		// 브라우저 메모리에 저장한 쿠키는 브라우저(?)가 살아있을 동안만 가지고 있디
		// setMaxAge에 저장된 쿠키는 지정된 시간까진 브라우저를 껐켰하든 계속 서버로 전송을 한다
		
		// setSecure() 브라우저에서 쿠키를 수정할 수 없다
		
		
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		
		// 애플리케이션에서 넘겨준 쿠키만 브라우저가 다시 넘겨줄 수 있다 
		// 하지만 브라우저는 A애플리케이션에서 받은 쿠키를 B애플리케이션에 넘겨주고 싶을 수 있다
		return "redirect:/";
	}
	
	@GetMapping("/readCookie")
	//@CookieValue("mid") -> mid의 값을 읽어서 String mid에 넣어준다
	public String readCookie(
			@CookieValue("mid") String mid,
			@CookieValue("memail") String memail,
			Model model) {
		log.info("mid: " + mid);
		log.info("memail: " + memail);
		
		model.addAttribute("mid", mid);
		model.addAttribute("memail", memail);
		
		return "ch05/readCookie";
	}
}






