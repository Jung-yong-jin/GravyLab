package co.kr.gravy.rest.sample.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.kr.gravy.rest.sample.entity.SampleDTO;
import co.kr.gravy.rest.sample.entity.SampleVO;
import co.kr.gravy.rest.sample.service.SampleService;

@RestController
@RequestMapping("/sample")
public class SampleController {

	SampleService sampleService;
	
	@Value("${globals.sample.img.path}")
	private String globalsSampleImgPath;
	
	@Autowired
	public SampleController(SampleService sampleService) {
		this.sampleService = sampleService;
	}
	
	@RequestMapping(value="/getValidate", method=RequestMethod.POST)
	public SampleDTO getValidate(@Valid @RequestBody SampleDTO sampleDTO) {
		return sampleDTO;
	}
	
	/**
	 * 모든 샘플을 조회
	 * @return
	 */
	@RequestMapping(value="/getGlobalValue", method=RequestMethod.GET)
	public String getString(){
		return globalsSampleImgPath;
	}
	
	/**
	 * 모든 샘플을 조회
	 * @return
	 */
	@RequestMapping(value="/getAllSamples", method=RequestMethod.GET)
	public List<SampleVO> getAllSamples(){
		List<SampleVO> sampleList = sampleService.findAll();
		return sampleList;
	}
	
	/**
	 * 샘플 데이터를 저장한다.
	 * @param req
	 * @param sample
	 * @return
	 */
	@RequestMapping(value="/save" , method=RequestMethod.POST)
	public SampleVO saveSample(HttpServletRequest req, @Valid @RequestBody SampleDTO sample) {
		SampleVO resSample = sampleService.save(sample);
		return resSample;
	}
	
	/**
	 * 모든 샘플을 조회
	 * @return
	 */
	@RequestMapping(value="/mybatisSamplesA", method=RequestMethod.GET)
	public List<SampleDTO> mybatisSamplesA(){
		List<SampleDTO> sampleList = sampleService.mybatisSamplesA();
		return sampleList;
	}
	
	/**
	 * 모든 샘플을 조회
	 * @return
	 */
	@RequestMapping(value="/mybatisSamplesB", method=RequestMethod.GET)
	public List<SampleDTO> mybatisSamplesB(){
		List<SampleDTO> sampleList = sampleService.mybatisSamplesB();
		return sampleList;
	}
	
	/**
	 * 모든 샘플을 조회
	 * @return
	 */
	@RequestMapping(value="/restSample", method=RequestMethod.GET)
	public List<SampleDTO> restSample(){
		List<SampleDTO> sampleList = sampleService.restCall();
		return sampleList;
	}
}
