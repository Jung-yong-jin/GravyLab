package co.kr.gravy.web.sample.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/web")
public class WebSampleController {

	@ResponseBody
	@RequestMapping("/getList")
	public List<String> getList(HttpServletRequest request, Model model,@RequestParam String name, @RequestParam int age) {
		System.out.println("name : " + name);
		System.out.println("age : " + age);
		List<String> listString = Arrays.asList("A","B");
		model.addAttribute("List", listString);
		return listString;
	}
	
	
	@ResponseBody
	@RequestMapping("/uploadFile")
	public Map<String, Object> uploadFile(MultipartHttpServletRequest request) {
		System.out.println(request.getParameter("test"));
		List<MultipartFile> multipartFileList = request.getFiles("file");
		for(MultipartFile file : multipartFileList) {
			try {
				file.transferTo(new File("D:\\test_data\\uploadFile\\" + file.getOriginalFilename()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.getFileMap().keySet().forEach(s -> System.out.println("file : " + s));
		Enumeration<String> em =   request.getParameterNames();
		while(em.hasMoreElements()) {
			System.out.println(em.nextElement());
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("SSS", "성공");
		
		return resultMap;
	}
}
