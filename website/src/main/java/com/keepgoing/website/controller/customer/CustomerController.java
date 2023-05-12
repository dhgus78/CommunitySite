package com.keepgoing.website.controller.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.keepgoing.website.comparator.NoticeviewComparator;
import com.keepgoing.website.entity.FileDto;
import com.keepgoing.website.entity.Notice;
import com.keepgoing.website.entity.NoticeView;
import com.keepgoing.website.entity.UserVoRole;
import com.keepgoing.website.function.CurrentDateTime;
import com.keepgoing.website.service.FileService;
import com.keepgoing.website.service.NoticeService;
import com.keepgoing.website.service.UserService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private NoticeService service;
	@Autowired
	private UserService userService;
	@Autowired
	private FileService fileService;

	
		//메인페이지
		@RequestMapping("/main")
		public String main(HttpServletRequest request, Model model, Authentication authentication) {
			if(authentication == null) {
				return "redirect:/main";
			}
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
			//********************************************************************
			
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String customerEmail = userDetails.getUsername();
			
			
			//********************************************************************
			
			int count = service.getCount(field,query,pub);
			
			List<NoticeView> list = service.getViewList(page, field, query, pub);
			
			
			//********************************************************************
			model.addAttribute("list", list);
			model.addAttribute("count",count);
			model.addAttribute("customerEmail",customerEmail);
			
			
			return "customer/main";
			
		}
		
		//디테일 페이지
		@RequestMapping("/detail")
		public String detail(HttpServletRequest request, HttpServletResponse response, Model model, Authentication authentication) {
			if(authentication == null) {
				return "redirect:/main";
			}
			int id = Integer.parseInt(request.getParameter("id"));
			
			NoticeView noticeView_ = service.getView(id);
			service.viewCountValidation(noticeView_,request,response);
			
			NoticeView noticeView = service.getView(id);
			Notice prevNotice = service.getPrev(id);
			Notice nextNotice = service.getNext(id);
			
			String filesId = noticeView.getFiles();
			List<FileDto> files = new ArrayList<>();
			
			
			//********************************************************************

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String customerEmail = userDetails.getUsername();
			
			
			//********************************************************************
			
			
			
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
			model.addAttribute("customerEmail",customerEmail);
			
			return "customer/detail";
		}
		
		//게시물 작성 페이지
		@RequestMapping("/reg")
		public String reg(HttpServletRequest request ,Model model, Authentication authentication) {
			if(authentication == null) {
				return "redirect:/main";
			}
			//********************************************************************
			
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String customerEmail = userDetails.getUsername();
			
			
			//********************************************************************
			
			model.addAttribute("customerEmail",customerEmail);
			
			return "customer/reg";
		}
		
		//게시물 작성 프로세스
		@PostMapping("/reg_process")
		public String reg_process(HttpServletRequest request ,Model model, 
				Authentication authentication, List<MultipartFile> files) throws IOException {
			if(authentication == null) {
				return "redirect:/main";
			}
			String title = request.getParameter("title");
			String content =request.getParameter("content");
			String isOpen_ = request.getParameter("open");
			
			boolean isOpen = false;
			if(isOpen_!=null) {
				isOpen = true;
			}
			
			//********************************************************************

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String customerEmail = userDetails.getUsername();
			
			UserVoRole memberInfo = userService.getMemberByEmail(customerEmail);
			int memberId = memberInfo.getId();
			
			//********************************************************************
			Notice notice = new Notice();
			List<String> fileIds = new ArrayList<>();
			
			if(files != null) {
				for (MultipartFile multipartFile : files) {
					
		            String fileId = fileService.saveFile(multipartFile);
		            fileIds.add(fileId);
		        }
			}
			String fileIds_str = StringUtils.join(fileIds, ",");
			
			notice.setTitle(title);
			notice.setContent(content);
			notice.setPub(isOpen);
			notice.setFiles(fileIds_str);
			notice.setMemberId(memberId);
			
			//새로 추가된 게시글의 id
			int id = service.insert(notice);
			
			fileService.setNoticeId(id,fileIds_str);
			
			return "redirect:/customer/main";
		}
		
		@GetMapping("/customer_info")
		public String customer_info(HttpServletRequest request ,Model model, Authentication authentication) {
			if(authentication == null) {
				return "redirect:/main";
			}
			//********************************************************************

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String customerEmail = userDetails.getUsername();
			
			UserVoRole memberInfo = userService.getMemberByEmail(customerEmail);

			//********************************************************************
			
			model.addAttribute("memberInfo", memberInfo);
			
			return "customer/customer_info";
		}
		
		//회원가입 탈퇴
		@GetMapping("/membershipWithdrawal")
		@ResponseBody
		public String membershipWithdrawal(HttpServletRequest request ,Model model, Authentication authentication) {
			if(authentication == null) {
				return "redirect:/main";
			}
			//********************************************************************

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String customerEmail = userDetails.getUsername();
			
			UserVoRole memberInfo = userService.getMemberByEmail(customerEmail);
			
			int memberId = memberInfo.getId();
			userService.membershipWithdrawal(memberId);

			//********************************************************************
			String resultMsg = "<script>alert('회원탈퇴가 완료 되었습니다.');location.href='/main'</script>";
			
			
			
			
			return resultMsg;
		}
		
		@GetMapping("/myWriting")
		public String myWriting(HttpServletRequest request ,Model model, Authentication authentication) {
			if(authentication == null) {
				return "redirect:/main";
			}
			String page_ = request.getParameter("p");
			
			String field = "memberName";
			
			String query = "";
			
			int page = 1;
			if(page_ != null && ! page_.equals(""))
				page = Integer.parseInt(page_);
			
			boolean pub_true = true;
			boolean pub_false = false;
			//********************************************************************
			
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String customerEmail = userDetails.getUsername();
			
			UserVoRole memberInfo = userService.getMemberByEmail(customerEmail);
			query = memberInfo.getName();
			//********************************************************************
			
			int count;
			int count_true = service.getCount(field,query,pub_true);
			int count_false = service.getCount(field,query,pub_false);
			count = count_true + count_false;
			
			List<NoticeView> list = new ArrayList<>();
			List<NoticeView> list_true = service.getMyViewList(page, field, query, pub_true);
			List<NoticeView> list_false = service.getMyViewList(page, field, query, pub_false);
			list.addAll(list_true);
			list.addAll(list_false);
			
			Collections.sort(list,new NoticeviewComparator().reversed());
			
			//********************************************************************
			model.addAttribute("list", list);
			model.addAttribute("count",count);
			model.addAttribute("customerEmail",customerEmail);
			
			return "customer/myWriting";
		}
		
		
		//디테일 페이지
		@RequestMapping("/myDetail")
		public String myDetail(HttpServletRequest request ,Model model, Authentication authentication) {
			if(authentication == null) {
				return "redirect:/main";
			}
			
			String field = "memberName";
			
			String query = "";
			
			//********************************************************************

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String customerEmail = userDetails.getUsername();
			
			UserVoRole memberInfo = userService.getMemberByEmail(customerEmail);
			query = memberInfo.getName();
			//********************************************************************
			
			
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			NoticeView noticeView = service.getView(id);
			NoticeView prevNotice = service.getMyPrev(field, query, id);
			NoticeView nextNotice = service.getMyNext(field, query, id);
			
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
			model.addAttribute("customerEmail",customerEmail);
			
			return "customer/myDetail";
		}
		
		@PostMapping("/deleteNotice")
		@ResponseBody
		public String noticeDelete(HttpServletRequest request ,Model model, Authentication authentication){
			if(authentication == null) {
				return "redirect:/main";
			}
			
			//********************************************************************

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String customerEmail = userDetails.getUsername();
			
			UserVoRole memberInfo = userService.getMemberByEmail(customerEmail);
			//********************************************************************
			
			
			
			String[] deleteId = request.getParameterValues("deleteId");
			
			
			
			/* 삭제 요청이 있을 경우 */
			if(deleteId != null) {
				String deleteId_ = String.join(",", deleteId);
				int count = 0;
				
				/* 삭제 요청이 여러개 일 경우 */
				if(deleteId_.contains(",")) {
					String[] ids = deleteId_.split(",");
					for(String i : ids) {
						int id = Integer.parseInt(i); 
						count += service.delete(id);
					}
					
					String resultMsg = "<script>alert('"+count+"개의 게시글이 삭제 되었습니다.');location.href='/customer/myWriting'</script>";
					return resultMsg;
					
				}else {
				/* 삭제 요청이 한개 일 경우 */
					int id = Integer.parseInt(deleteId_); 
					count = service.delete(id);
					
					String resultMsg = "<script>alert('"+count+"개의 게시글이 삭제 되었습니다.');location.href='/customer/myWriting'</script>";
					return resultMsg;
					
				}
				
				
			}
				
			
			
			
			

			
			String resultMsg = "<script>alert('선택된 글이 없습니다.');location.href='/customer/myWriting'</script>";
			return resultMsg;
		}
		
		@GetMapping("/myNoticeUpdate")
		public String myNoticeUpdate(HttpServletRequest request ,Model model, Authentication authentication) {
			if(authentication == null) {
				return "redirect:/main";
			}
			
			//********************************************************************

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String customerEmail = userDetails.getUsername();
			
			UserVoRole memberInfo = userService.getMemberByEmail(customerEmail);
			//********************************************************************
			int id = Integer.parseInt(request.getParameter("id"));
			
			NoticeView noticeView = service.getView(id);
			
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
			model.addAttribute("customerEmail",customerEmail);
			
			
			return "customer/detailUpdate";
		}
		
		@PostMapping("/myNoticeUpdateProcess")
		@ResponseBody
		public String myNoticeUpdateProcess(HttpServletRequest request ,Model model, 
				Authentication authentication, List<MultipartFile> files) throws IOException {
			if(authentication == null) {
				return "redirect:/main";
			}
			
			//********************************************************************

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String customerEmail = userDetails.getUsername();
			
			UserVoRole memberInfo = userService.getMemberByEmail(customerEmail);
			//********************************************************************
			String id = request.getParameter("id"); //게시물 가져오기 위한 id
			String title = request.getParameter("title");//수정할 제목
			String content =request.getParameter("content");//수정할 내용
			String isOpen = request.getParameter("open");//수정할 공개여부
			String[] deleteFiles = request.getParameterValues("deleteFile"); // 삭제할 파일 savedName이름
			
			//수정하기전 게시물의 파일 목록 가져오기.
			int id_int = Integer.parseInt(id.trim());
			
			//id로 게시물 얻어오기.
			NoticeView noticeveiw = service.getView(id_int);
			
			
			//------------파일 관련---------------------------------------
			
			
			//해당 게시물의 파일id 값 얻어오기.
			String filesIds = noticeveiw.getFiles();
			if(filesIds == null) {
				filesIds = "";
			}
			
			
			String[] idsArr = filesIds.split(",");
			List<String> idsList_ = Arrays.asList(idsArr);
			
			//Arrays.asList <-는 리스트로 변환 해주지만 크기 변경이 안되기 때문에 ArrayList를 새로 생성해서 값을 옮겨 담음.
			List<String> idsList = new ArrayList<>();
			idsList.addAll(idsList_);
			
			//이제 기존 게시물의 파일 아이디는 [],[1],[1,2],[1,2,3]
			//idsList //<- 기존의 파일ID LIST
			if(idsList.get(0) == "") {
				idsList.remove("");
			}
			
			
			
			
			/* 먼저 추가할 파일을 저장하는 작업 */
			// [], [1], [1,2]
			List<String> addFileIds = new ArrayList<>();
			
			if(files != null) {
				for (MultipartFile multipartFile : files) {
					
		            String fileId = fileService.saveFile(multipartFile);
		            addFileIds.add(fileId);
		        }
			}
			
			//addFileIds <- 추가한 파일 ID LIST
			
			/* 삭제요청온 파일 삭제하기 */
			//[], [1], [1,2]
			List<String> delFileIds = new ArrayList<>();
	
			
			if(deleteFiles != null) {
				for(String savedFile : deleteFiles) {
					String delId = fileService.deleteFile(savedFile);
					delFileIds.add(delId);
				}
			}
			
			//delFileIds <- 삭제한 파일 ID LIST
			
			//((기존 파일 ID LIST) - (삭제한 파일 ID LIST)) + 추가한 파일 ID LIST
			// (( idsList - delFileIds ) + addFileIds ) = 수정된 파일 ID LIST
			
			idsList.removeAll(delFileIds);
			List<String> joinList = new ArrayList<>();
			
			
			joinList.addAll(idsList);
			joinList.addAll(addFileIds);
			
			 // 공백, null 제거
			joinList.removeAll(Arrays.asList("", null));
	        
			
			//joinList <- 최종 파일 ID LIST
			
			String fileIdsResult = String.join(",", joinList);
			
			//fileIdsResult 최종 파일ids 결과.
			
			//------------여기까지 파일 관련---------------------------------------
			
			//파일 공개여부 재설정
			boolean pub;
			if(isOpen == null) {
				pub = false;
				
			}else {
				pub = true;
			}
			
			
			
			noticeveiw.setTitle(title);
			noticeveiw.setContent(content);
			noticeveiw.setPub(pub);
			noticeveiw.setFiles(fileIdsResult);
			
			service.update(noticeveiw);
			fileService.setNoticeId(id_int,fileIdsResult);
			
			String resultMsg = "<script>alert('해당 게시글이 수정 되었습니다.');location.href='/customer/myWriting'</script>";
			return resultMsg;
		}
		
		@GetMapping("/changePassword_form")
		public String changePassword_form(HttpServletRequest request ,Model model, Authentication authentication) {
			if(authentication == null) {
				return "redirect:/main";
			}
			//********************************************************************

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String customerEmail = userDetails.getUsername();
			
			UserVoRole memberInfo = userService.getMemberByEmail(customerEmail);
			//********************************************************************
			
			model.addAttribute("memberInfo", memberInfo);
			
			return "customer/changePassword_check";
		}
		
		@PostMapping("/passwordCheck")
		public String passwordCheck(HttpServletRequest request ,Model model, Authentication authentication) {
			if(authentication == null) {
				return "redirect:/main";
			}
			//********************************************************************

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String customerEmail = userDetails.getUsername();
			
			UserVoRole memberInfo = userService.getMemberByEmail(customerEmail);
			//********************************************************************
			
			String input_password = request.getParameter("password");
			
			boolean checkResult = userService.checkPassword(input_password,memberInfo.getPwd());
			
			if(checkResult) { //패스워드 일치 할 경우
				
				model.addAttribute("memberInfo", memberInfo);
				return "customer/changePassword_modify";
				
				
			}else { //패스워드 불일치 할 경우
				
				String result = "fail";
				model.addAttribute("memberInfo", memberInfo);
				model.addAttribute("pwdFail",result);
				return "customer/changePassword_check";
			}
			
		}
		
		@PostMapping("/changePassword_modify")
		public String passwordModify(HttpServletRequest request ,Model model, Authentication authentication) {
			if(authentication == null) {
				return "redirect:/main";
			}
			//********************************************************************

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String customerEmail = userDetails.getUsername();
			
			UserVoRole memberInfo = userService.getMemberByEmail(customerEmail);
			//********************************************************************
			
			String input_password = request.getParameter("password");
			String input_passwordConfirm = request.getParameter("passwordConfirm");
			
			userService.passwordUpate(memberInfo ,input_password);
			
			
			return "redirect:/logout";
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		/* 테스트 */
		@GetMapping("/test")
		@ResponseBody
		public String test() {
			
			Date time = CurrentDateTime.getCurrentTime();
			System.out.println(time);
			return "test";
		}
		
}
