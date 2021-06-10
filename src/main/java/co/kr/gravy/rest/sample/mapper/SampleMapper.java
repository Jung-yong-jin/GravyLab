package co.kr.gravy.rest.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import co.kr.gravy.rest.sample.entity.SampleDTO;

@Repository("sampleMapper")
@Mapper
public interface SampleMapper {

	List<SampleDTO> sampleSelectA();
	List<SampleDTO> sampleSelectB();
}
