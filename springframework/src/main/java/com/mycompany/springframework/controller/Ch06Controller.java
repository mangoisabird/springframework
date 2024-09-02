package com.mycompany.springframework.controller;import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.dto.Ch06Cart;
import com.mycompany.springframework.dto.Ch06Item;
import com.mycompany.springframework.dto.Ch06Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch06")
public class Ch06Controller {
	@GetMapping("/forward")
	public String forward(Model model, HttpServletRequest request){
		Ch06Member member = new Ch06Member();
		member.setMid("user1");
		member.setMname("사용자1");
		member.setMemail("user1@mycompany.com");
		
		//방법1
		model.addAttribute("member1", member);
		
		//방법2
		request.setAttribute("member2", member);
		
		
		model.addAttribute("chNum", "ch05");
		return "ch06/forward";

	}
	
	@GetMapping("/redirect")
	public String redirect(HttpSession session){
		Ch06Member member = new Ch06Member();
		
		member.setMid("user1");
		member.setMname("사용자1");
		member.setMemail("user1@mycompany.com");
		
		session.setAttribute("member", member);
		
		return "redirect:/ch06/sessionData";
	}
	
	
	@GetMapping("/sessionData")
	public String sessionData(HttpSession session, Model model){
		//세션에서 찾아오기(읽기)
		Ch06Member member = (Ch06Member)session.getAttribute("member");
		log.info(member.toString());
		
		log.info("mid: " + member.getMid());
		log.info("mname: " + member.getMname());
		log.info("memail: " + member.getMemail());
		
		model.addAttribute("chNum", "ch05");
		return "ch06/sessionData";
	}
	
	@GetMapping("/cartview")
	public String cartview(HttpSession session, Model model){
		//세션에서 카트를 가져오는 코드
		//Ch06Cart cart = (Ch06Cart) session.getAttribute("cart");
		// 처음엔 cart가 null이므로  필요있다 없다?
		// 세션에 있는 cart를 찾아서 쓰기만 하면되는건데..? 
		// 에러가 안난다?
		// el이 존재하지않으면(request, session범위에 그냥 아무것도 출력이 안된다 에러는 아닌다!
		
		/*
		if(cart == null){
			session.setAttribute("cart", new Ch06Cart());
			
			
			cart = (Ch06Cart) session.getAttribute("cart");
			
			Ch06Item item = new Ch06Item();
			item.setPno("p1");
			item.setPname("상품1");
			cart.addItem(item);
			
			item = new Ch06Item();
			item.setPno("p2");
			item.setPname("상품2");
			cart.addItem(item);
			
		}
		*/
		model.addAttribute("chNum", "ch05");
		return "ch06/cartview";
	}
	
	@GetMapping("/productlist")
	public String productlist(Model model){
		List<Ch06Item> productList = new ArrayList<>();
		
		for(int i=1; i<=5; i++){
			Ch06Item item = new Ch06Item();
			item.setPno("p" + i);
			item.setPname("상품" + i);
			productList.add(item);
		}
		
		model.addAttribute("productList", productList);
		model.addAttribute("chNum", "ch05");
		return "ch06/productlist";
	}
	
	@GetMapping("/cartadd")
	public String cartadd(Ch06Item item, HttpSession session){
		//세션에서 Ch06Cart 가져오기
		Ch06Cart cart = (Ch06Cart) session.getAttribute("cart");
		
		//만약 Ch06Cart 객체 없다면 새로 생성해서 세션에 저장
		
		if(cart == null) {
			cart = new Ch06Cart();
			session.setAttribute("cart", cart);
			
		}
		
		//상품 아이템을 Ch06Cart에 추가
		cart.addItem(item);
		
		return "redirect:/ch06/cartview";
	}
	
	
	
	@GetMapping("/deleteitem")
	public String deleteitem(Ch06Item item, HttpSession session){
		//세션에서 Ch06Cart 가져오기
		Ch06Cart cart = (Ch06Cart) session.getAttribute("cart");
		
		//만약 Ch06Cart 객체 없다면 새로 생성해서 세션에 저장
		
		cart.removeItem(item);
		return "redirect:/ch06/cartview";
	}
	
	
	/*
	@GetMapping("/deleteitem")
	public String deleteitem(String pno, HttpSession session){
		//세션에서 Ch06Cart 가져오기
		Ch06Cart cart = (Ch06Cart)session.getAttribute("cart");
		
		//삭제할 pid 찾아서 장바구니에서 제거
		Iterator<Ch06Item> iterator = cart.getContents().iterator();
		
		while(iterator.hasNext()){
			Ch06Item item = iterator.next();
			if(item.getPno().equals(pno)){
				iterator.remove();
			}
		}
		
		return "redirect:/ch06/cartview";
	}
	*/
}






