package co.kr.gravy.rest.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.kr.gravy.rest.sample.entity.SampleVO;

@Repository("sampleRepository")
public interface SampleRepository extends JpaRepository<SampleVO, Long>{
	
	// findBy뒤에 컬럼명을 붙여주면 이를 이용한 검색이 가능하다 
	public List<SampleVO> findById(String id);

	public List<SampleVO> findByName(String name);
	
	//like검색도 가능 
	public List<SampleVO> findByNameLike(String keyword);

}
