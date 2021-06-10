package co.kr.gravy.rest.sample.service;

import java.util.List;
import java.util.Optional;

import co.kr.gravy.rest.sample.entity.SampleDTO;
import co.kr.gravy.rest.sample.entity.SampleVO;

public interface SampleService {
	
	
	public List<SampleVO> findAll();

	public Optional<SampleVO> findById(Long id);
	
	public void deleteById(Long id);
	
	public SampleVO save(SampleDTO sampleDTO);
	
	public void updateById(Long id, SampleDTO sampleDTO);
	
	public List<SampleDTO> mybatisSamplesA();
	
	public List<SampleDTO> mybatisSamplesB();
	
	public List<SampleDTO> restCall();
}
