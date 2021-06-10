package co.kr.gravy.rest.sample.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor(access = AccessLevel.PROTECTED) 
@Entity(name="tb_gl_sample")
public class SampleVO {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private int age;
}
