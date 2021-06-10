package co.kr.gravy.rest.retrofit;

import java.io.IOException;

import co.kr.gravy.common.templates.retrofit.RetrofitClient;
import co.kr.gravy.rest.retrofit.dto.PostResultDTO;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Slf4j
public class MainTest {

	public static void main(String[] args) {
		
		//Call<List<SampleVO>> getTest = RetrofitClient.getApiService().getTest();
		//Call<Object> getTest = RetrofitClient.getApiService().getUser();
		try {
			Call<PostResultDTO> call = RetrofitClient.getBaseJsonApiService().getPosts("13");
			//Enqueue로 비동기 통신실행 * 통신 완료 후 이벤트 처리 위한 callback 리스너 등록
			call.enqueue(retrofitProccess()); 			
			
			Call<Object> callUser = RetrofitClient.getReqResApiService().getUser();
			System.out.println(callUser.execute().body());
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static Callback<PostResultDTO> retrofitProccess(){
		
		Callback<PostResultDTO> callback = new Callback<PostResultDTO>() {
														/**
														 *onResponse 통신 성공시 callback
														 */
														@Override
														public void onResponse(Call<PostResultDTO> call, Response<PostResultDTO> response) {
															if(response.isSuccessful()) { //정상 2xx 인지 확인
																// 정상적으로 통신이 성공된 경우
																if(call.isExecuted()) {System.out.println("isExecuted()");}
																PostResultDTO result = response.body();
																log.info("onResponse: 성공, 결과\n{}" , result.toString() );
															}else {
																//통신이 실패한 경우 (응답 코드 3xx, 4xx 등)
																log.error("onResponse : 실패");
															}
														}
											
														/**
														 *onFailure 통신 실패시 callback
														 */
														@Override
														public void onFailure(Call<PostResultDTO> call, Throwable t) {
															// 통신 실패 (인터넷 끊김, 예외 발생 등 시스템적인 이유)
															log.info("onFailure: {}", t.getMessage() );
														}
												}; 
		
		return callback;
	}

}
