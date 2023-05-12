package com.keepgoing.website.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.keepgoing.website.entity.FileDto;
import com.keepgoing.website.entity.MailDto;
import com.keepgoing.website.entity.Notice;
import com.keepgoing.website.entity.NoticeView;
import com.keepgoing.website.entity.UserVo;
import com.keepgoing.website.entity.UserVoRole;
import com.keepgoing.website.service.EmailService;
import com.keepgoing.website.service.FileService;
import com.keepgoing.website.service.NoticeService;
import com.keepgoing.website.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private NoticeService service;
	@Autowired
	private UserService userService;
	@Autowired
	private FileService fileService;
	@Autowired
	private EmailService emailService;

	//메인페이지
	@RequestMapping("/main")
	public String main(HttpServletRequest request, Model model) {
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		String field = "title";
		if(field_ != null && !field_.equals(""))
			field = field_;
		
		String query = "";
		if(query_ != null && !query_.equals(""))
			query = query_;
		
		int page = 1;
		if(page_ != null && ! page_.equals(""))
			page = Integer.parseInt(page_);
		
		boolean pub = true;
		
		int count = service.getCount(field,query,pub);
		
		List<NoticeView> list = service.getViewList(page, field, query, pub);
		
		model.addAttribute("list", list);
		model.addAttribute("count",count);
		
		return "/main";
		
	}
	
	//디테일 페이지
	@RequestMapping("/detail")
	public String detail(HttpServletRequest request , HttpServletResponse response, Model model) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		NoticeView noticeView_ = service.getView(id);
		service.viewCountValidation(noticeView_,request,response);
		
		
		NoticeView noticeView = service.getView(id);
		Notice prevNotice = service.getPrev(id);
		Notice nextNotice = service.getNext(id);
		
		String filesId = noticeView.getFiles();
		List<FileDto> files = new ArrayList<>();
		
		if(StringUtils.isEmpty(filesId)||StringUtils.isBlank(filesId)) {
			
		}else {
			/* 파일이 있을경우 */
			/* 파일이 여러개 일 경우 */
			if(filesId.contains(",")) {
				String[] filesIds =filesId.split(",");
				
				List<String> fileIdList = Arrays.asList(filesIds);
				
				
				
				for(String i : fileIdList) {
					FileDto file = fileService.findFile(i);
					files.add(file);
				}
				

			}else {
				/* 파일이 하나 일 경우 */
				FileDto file = fileService.findFile(filesId);
				files.add(file);
				
				
			}
			
		}
		
		model.addAttribute("f", files);
		model.addAttribute("n", noticeView);
		model.addAttribute("n_prev", prevNotice);
		model.addAttribute("n_next", nextNotice);
		
		return "/detail";
	}
	
	//로그인 폼
	@RequestMapping("/loginForm")
	public String loginForm(HttpServletRequest request, Model model) {
		String error= request.getParameter("error");
		String exception = request.getParameter("exception");
		
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		
		return "/login";
	}
	
	//회원가입 폼
	@GetMapping("/join")
	public String joinForm() {
		
		
		return "/join";
	}
	
	//회원가입 process
	@PostMapping("/join")
	public String join(UserVo userVo) {
	
		userService.joinUser(userVo);
		return "redirect:/loginForm";
	}
	
	@GetMapping("/temporaryPassword_form")
	public String temporaryPassword_form() {
		return "/temporaryPassword_form";
	}
	
	@PostMapping("/tempPwd")
	public String tempPwd(HttpServletRequest request, Model model) {
		String email = request.getParameter("username");
		String name = request.getParameter("name");
		
		UserVoRole member = userService.getMemberForTempPwd(email,name);
		
		//조건에 맞는 회원이 없을 경우
		if(member == null) {
			
			model.addAttribute("noMember", true);
			return "/temporaryPassword_form";
		}
		
		//조건에 맞는 회원이 있을 경우
		//임시비밀번호 생성해서 비밀번호 업데이트 하고 해당 이메일로 임시비밀번호 발송
		MailDto mail = emailService.createMailAndChangePassword(member);
		emailService.mailSend(mail);
		
		
		
		return "redirect:/loginForm";
	}
	


}
