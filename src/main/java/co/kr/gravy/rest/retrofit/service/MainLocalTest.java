package co.kr.gravy.rest.retrofit.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import co.kr.gravy.common.templates.retrofit.RetrofitClient;
import co.kr.gravy.rest.sample.entity.SampleDTO;
import co.kr.gravy.rest.sample.entity.SampleVO;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@Slf4j
public class MainLocalTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//MainLocalTest.retrofitBody();
		//MainLocalTest.retrofitGetList();
		//MainLocalTest.retrofitFromGetList();
		MainLocalTest.retrofitUploadFile();
	}

	private static void retrofitUploadFile() {
		
		RequestBody requestBody;
		MultipartBody.Part body;
		
		File files[] = {new File("D:\\test_data\\Image 001.png"),new File("D:\\test_data\\Image 002.png")};
		
		
		LinkedHashMap<String, RequestBody> mapRequestBody = new LinkedHashMap<String, RequestBody>();
		List<MultipartBody.Part> arrBody = new ArrayList<>();
		for(File file : files) {
			requestBody =  RequestBody.create(MediaType.parse("multipart/form-data"), file);
			mapRequestBody.put("file\"; filename=\"" + file.getName(), requestBody);
			
			body = MultipartBody.Part.createFormData("fileName", file.getName(), requestBody);
			arrBody.add(body);
		}
		mapRequestBody.put("test", RequestBody.create(MediaType.parse("text/plain"), "gogogogogogogog"));
		
		Call<Map<String, Object>> call = RetrofitClient.getBaseLocalFileApiService().uploadFile(mapRequestBody, arrBody);
		call.enqueue(new Callback<Map<String,Object>>(){
			@Override
			public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
				// TODO Auto-generated method stub
				if(response.isSuccessful()) {
					Map<String, Object> map = response.body();
					log.info("onResponse 성공 : {}" , map.toString());
				}else {
					try {
						log.info("onResponse : 실패 : \n{}", response.errorBody().string());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			@Override
			public void onFailure(retrofit2.Call<java.util.Map<String,Object>> call, Throwable t) {
				log.info("onFailure 시스템 에러 : {}" , t.getMessage());
			}
		});
		
	}
	
	private static void retrofitFromGetList() {
		Call<Object> call = RetrofitClient.getBaseLocalApiService().getFormList("이이롸", 49);
		call.enqueue(new Callback<Object>() {
			@Override
			public void onResponse(Call<Object> call, Response<Object> response) {
				// TODO Auto-generated method stub
				if(response.isSuccessful()) {
					log.info("onResponse : 성골 결과 데이터 : \n{}", response.body());
				}else {
					try {
						log.info("onResponse : 실패 : \n{}", response.errorBody().string());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			}
			
			@Override
			public void onFailure(Call<Object> call, Throwable t) {
				// TODO Auto-generated method stub
				log.info("onFailure 시스템 오류 : {}", t.getMessage());
			}
		});
	}
	
	private static void retrofitGetList() {
		Call<Object> call = RetrofitClient.getBaseLocalApiService().getList("이이롸", 49);
		call.enqueue(new Callback<Object>() {
			@Override
			public void onResponse(Call<Object> call, Response<Object> response) {
				// TODO Auto-generated method stub
				if(response.isSuccessful()) {
					log.info("onResponse : 성골 결과 데이터 : \n{}", response.body());
				}else {
					try {
						log.info("onResponse : 실패 : \n{}", response.errorBody().string());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			}
			
			@Override
			public void onFailure(Call<Object> call, Throwable t) {
				// TODO Auto-generated method stub
				log.info("onFailure 시스템 오류 : {}", t.getMessage());
			}
		});
	}
	
	
	private static void retrofitBody() {
		
		SampleDTO vo = new SampleDTO();
		vo.setAge(32);
		vo.setName("이미자");
		Call<SampleVO> call =  RetrofitClient.getBaseLocalApiService().save(vo);
		
		call.enqueue(new Callback<SampleVO>() {
					@Override
					public void onResponse(Call<SampleVO> call, Response<SampleVO> response) {
						// TODO Auto-generated method stub
						if(response.isSuccessful()) {
							SampleVO sampleVO = response.body();
							log.info("onResponse: 성공, 결과\n{}" , sampleVO.getName());
						}else {
							log.info("onResponse : 실패");
						}
					}
					
					@Override
					public void onFailure(Call<SampleVO> call, Throwable t) {
						// TODO Auto-generated method stub
						log.info("onFailure 시스템 오류 {}", t.getMessage());
					}
		});
	}
}
