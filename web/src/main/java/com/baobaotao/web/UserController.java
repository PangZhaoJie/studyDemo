package com.baobaotao.web;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.baobaotao.UserService;
import com.baobaotao.domain.Dept;
import com.baobaotao.domain.User;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {


	@ModelAttribute("user")
	public User getUser() {
		User user = new User();
		user.setUserId("1001");
		user.setUserName("<>");
		return user;
	}

	@RequestMapping(value = "/handle")
	public String handle(@RequestBody User user) {
		user.setUserName("John");
		return "redirect:handle72.html";
	}

	@RequestMapping(value = "/handle71")
	public String handle71(@ModelAttribute("user") User user) {
		user.setUserName("John");
		return "redirect:handle72.html";
	}

	@RequestMapping(value = "/handle72")
	public String handle72(ModelMap modelMap) {
		User user = (User) modelMap.get("user");
		if (user != null) {
			user.setUserName("Jetty");
		}
		return "/user/showUser";
	}

	@RequestMapping(value = "/handle81")
	public String handle81(@RequestParam("user") User user, ModelMap modelMap) {
		modelMap.put("user", user);
		return "/user/showUser";
	}

	@RequestMapping(value = "/register2")
	public String register2(User user) {
		return "/user/register2";
	}

	@RequestMapping(value = "/handle82")
	public String handle82(User user) {
		return "/user/showUser";
	}

	@RequestMapping(value = "/handle91")
	public String handle91(@Valid User user,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/user/register3";
		} else {
			return "/user/showUser";
		}
	}

	@RequestMapping(value = "/handle92")
	public String handle92(@ModelAttribute("user") User user,
			BindingResult bindingResult) {
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "userName",
				"required");
		if ("aaaa".equalsIgnoreCase(user.getUserName())) {
			bindingResult.rejectValue("userName", "reserved");
		}
		if (bindingResult.hasErrors()) {
			return "/user/register4";
		} else {
			return "/user/showUser";
		}
	}

	@RequestMapping(value = "/welcome")
	public String handle100() {
		return "/user/welcome";
	}

	@RequestMapping(value = "/showUserList")
	public String showUserList(ModelMap mm) {
		Calendar calendar = new GregorianCalendar();
		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setUserName("tom");
		user1.setRealName("汤姆");
		calendar.set(1980, 1, 1);
		user1.setBirthday(calendar.getTime());
		User user2 = new User();
		user2.setUserName("john");
		user2.setRealName("约翰");
		user2.setBirthday(calendar.getTime());
		userList.add(user1);
		userList.add(user2);
		mm.addAttribute("userList", userList);
		return "user/userList";
	}

	@RequestMapping(value = "/showUserListByFtl")
	public String showUserListInFtl(ModelMap mm) {
		Calendar calendar = new GregorianCalendar();
		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setUserName("tom");
		user1.setRealName("汤姆");
		calendar.set(1980, 1, 1);
		user1.setBirthday(calendar.getTime());
		User user2 = new User();
		user2.setUserName("john");
		user2.setRealName("约翰");
		user2.setBirthday(calendar.getTime());
		userList.add(user1);
		userList.add(user2);
		mm.addAttribute("userList", userList);
		return "userListFtl";
	}

	@RequestMapping(value = "/showUserListByXls")
	public String showUserListInExcel(ModelMap mm) {
		Calendar calendar = new GregorianCalendar();

		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setUserName("tom");
		user1.setRealName("汤姆");
		calendar.set(1980, 1, 1);
		user1.setBirthday(calendar.getTime());
		User user2 = new User();
		user2.setUserName("john");
		user2.setRealName("约翰");
		user2.setBirthday(calendar.getTime());
		userList.add(user1);
		userList.add(user2);
		mm.addAttribute("userList", userList);
		return "userListExcel";
	}

	@RequestMapping(value = "/showUserListByPdf")
	public String showUserListInPdf(ModelMap mm) {
		Calendar calendar = new GregorianCalendar();

		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setUserName("tom");
		user1.setRealName("汤姆");
		calendar.set(1980, 1, 1);
		user1.setBirthday(calendar.getTime());
		User user2 = new User();
		user2.setUserName("john");
		user2.setRealName("约翰");
		user2.setBirthday(calendar.getTime());
		userList.add(user1);
		userList.add(user2);
		mm.addAttribute("userList", userList);
		return "userListPdf";
	}

	@RequestMapping(value = "/showUserListByXml")
	public String showUserListInXml(ModelMap mm) {
		Calendar calendar = new GregorianCalendar();
		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setUserName("tom");
		user1.setRealName("汤姆");
		calendar.set(1980, 1, 1);
		user1.setBirthday(calendar.getTime());
		User user2 = new User();
		user2.setUserName("john");
		user2.setRealName("约翰");
		user2.setBirthday(calendar.getTime());
		userList.add(user1);
		userList.add(user2);
		mm.addAttribute("userList", userList);
		return "userListXml";
	}

	@RequestMapping(value = "/showUserListByJson")
	public String showUserListInJson(ModelMap mm) {
		Calendar calendar = new GregorianCalendar();
		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setUserName("tom");
		user1.setRealName("汤姆");
		calendar.set(1980, 1, 1);
		user1.setBirthday(calendar.getTime());
		User user2 = new User();
		user2.setUserName("john");
		user2.setRealName("约翰");
		user2.setBirthday(calendar.getTime());
		userList.add(user1);
		userList.add(user2);
		mm.addAttribute("userList", userList);
		return "userListJson";
	}

	@RequestMapping(value = "/showUserListByJson1")
	public String showUserListInJson1(ModelMap mm) {
		Calendar calendar = new GregorianCalendar();
		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setUserName("tom");
		user1.setRealName("汤姆1");
		calendar.set(1980, 1, 1);
		user1.setBirthday(calendar.getTime());
		User user2 = new User();
		user2.setUserName("john");
		user2.setRealName("约翰");
		user2.setBirthday(calendar.getTime());
		userList.add(user1);
		userList.add(user2);
		mm.addAttribute("userList", userList);
		return "userListJson1";
	}

	@RequestMapping(value = "/showUserListByI18n")
	public String showUserListInI18n(ModelMap mm) {
		Calendar calendar = new GregorianCalendar();
		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setUserName("tom");
		user1.setRealName("汤姆1");
		calendar.set(1980, 1, 1);
		user1.setBirthday(calendar.getTime());
		User user2 = new User();
		user2.setUserName("john");
		user2.setRealName("约翰");
		user2.setBirthday(calendar.getTime());
		userList.add(user1);
		userList.add(user2);
		mm.addAttribute("userList", userList);
		return "userListi18n";
	}

	@RequestMapping(value = "/showUserListMix")
	public String showUserListMix(ModelMap mm) {
		Calendar calendar = new GregorianCalendar();
		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setUserName("tom");
		user1.setRealName("汤姆1");
		calendar.set(1980, 1, 1);
		user1.setBirthday(calendar.getTime());
		User user2 = new User();
		user2.setUserName("john");
		user2.setRealName("约翰");
		user2.setBirthday(calendar.getTime());
		userList.add(user1);
		userList.add(user2);
		mm.addAttribute("userList", userList);
		return "userListMix";
	}

	@RequestMapping(value = "/uploadPage")
	public String updatePage() {	
		return "uploadPage";
	}
	
	
	@RequestMapping(value = "/upload")
	public String updateThumb(@RequestParam("name") String name,
			                  @RequestParam("file") MultipartFile file) throws Exception{
		if (!file.isEmpty()) {
			file.transferTo(new File("d:/temp/"+file.getOriginalFilename()));
			return "redirect:success.html";
		}else{
			return "redirect:fail.html";
		}
	}
	
	@RequestMapping(value = "/throwException")
	public String throwException() {
		if(2>1){
			throw new RuntimeException("ddd");
		}
		return "success";
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String handleException(RuntimeException re, HttpServletRequest request) {
		return "forward:/error.jsp";
	}

	@RequestMapping(value = "/notFound")
	public String notFound() {
		return "successdddd";
	}
	
    @ResponseStatus(HttpStatus.NOT_FOUND)
	public String notFound(HttpServletRequest request) {
		return "forward:/error.jsp";
	}	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// binder.registerCustomEditor(User.class, new UserEditor());
		// binder.setValidator(new UserValidator());
	}

}
