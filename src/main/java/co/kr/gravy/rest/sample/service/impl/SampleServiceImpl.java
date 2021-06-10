package co.kr.gravy.rest.sample.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.kr.gravy.common.templates.retrofit.RetrofitClient;
import co.kr.gravy.common.util.ModelMapperUtils;
import co.kr.gravy.rest.sample.entity.SampleDTO;
import co.kr.gravy.rest.sample.entity.SampleVO;
import co.kr.gravy.rest.sample.mapper.SampleMapper;
import co.kr.gravy.rest.sample.repository.SampleRepository;
import co.kr.gravy.rest.sample.service.SampleService;
import retrofit2.Call;
import retrofit2.Response;

@Service("sampleService")
public class SampleServiceImpl implements SampleService{
 
	SampleRepository sampleRepository;
	SampleMapper sampleMapper;
	
	@Autowired
	public SampleServiceImpl(SampleRepository sampleRepository, SampleMapper sampleMapper) {
		this.sampleRepository = sampleRepository;
		this.sampleMapper = sampleMapper;
	}
	
	public List<SampleVO> findAll(){
		List<SampleVO> members = new ArrayList<>(); 
		sampleRepository.findAll().forEach(e -> members.add(e)); 
		return members;

	}

	public Optional<SampleVO> findById(Long id){
		Optional<SampleVO> sample = sampleRepository.findById(id);
		return sample;
	}
	
	public void deleteById(Long id) {
		sampleRepository.deleteById(id);
	}
	
	public SampleVO save(SampleDTO sampleDTO) {
		// 이름이 같은 필드명끼리는 ModelMapper를 통해 매핑
		SampleVO sampleVO = ModelMapperUtils.getModelMapper().map(sampleDTO, SampleVO.class);
		sampleVO = sampleRepository.save(sampleVO);
		return sampleVO;
	}
	
	public void updateById(Long id, SampleDTO sampleDTO) {
		Optional<SampleVO> e = sampleRepository.findById(id);
		SampleVO sampleVO = ModelMapperUtils.getModelMapper().map(sampleDTO, SampleVO.class);
		if (e.isPresent()) { 
			//e.get().setId(sample.getId()); 
			//e.get().setName(sample.getName()); 
			sampleRepository.save(sampleVO); 
		}
	}
	
	public List<SampleDTO> mybatisSamplesA(){
		return sampleMapper.sampleSelectA();
	}
	
	public List<SampleDTO> mybatisSamplesB(){
		return sampleMapper.sampleSelectB();
	}
	
	public List<SampleDTO> restCall() {
		Call<List<SampleDTO>> call = RetrofitClient.getBaseLocalApiService().mybatisSamplesB();
		Response<List<SampleDTO>> res = null;
		try {
			res = call.execute();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return res.body();
	}
	
}

