package com.mycompany.springframework.exception;

// Exception 상속 ==> 반드시 try-catch? / RuntimeException 상속 --> try-catch 안함 ===> 둘의 차이 

public class Ch10CustomException extends Exception{
	//기본 생성자
	public Ch10CustomException(){
		
	}
	
	//예외 메세지 생성자
	public Ch10CustomException(String message){
		super(message);
	}	
	
	
	
}
