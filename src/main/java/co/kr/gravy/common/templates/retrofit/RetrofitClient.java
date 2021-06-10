package co.kr.gravy.common.templates.retrofit;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.kr.gravy.rest.retrofit.service.RetrofitService;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
	
	private static final String BASE_LOCAL_URL = "http://localhost:5454/";
	private static final String BASE_OPENAPI_URL = "https://openapi.airkorea.or.kr/";
	private static final String BASE_JSON_URL = "https://jsonplaceholder.typicode.com/";
	private static final String BASE_REQRES_URL = "https://reqres.in/";
	
	/**
	 * @return
	 */
	public static RetrofitService getBaseJsonApiService() {
		return getInstance(BASE_JSON_URL).create(RetrofitService.class);
	}
	
	
	/**
	 * @return
	 */
	public static RetrofitService getReqResApiService() {
		return getInstance(BASE_REQRES_URL).create(RetrofitService.class);
	}
	
	
	public static RetrofitService getBaseLocalApiService() {
		return getInstance(BASE_LOCAL_URL).create(RetrofitService.class);
	}
	
	public static RetrofitService getBaseLocalFileApiService() {
		return getMultipartInstance(BASE_LOCAL_URL).create(RetrofitService.class);
	}
	/**
	 * @param baseUrl
	 * @return
	 */
	private static Retrofit getInstance(String baseUrl) {
		Gson gson = new GsonBuilder()
						.setLenient()
						.create();
		
		return new Retrofit
				.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(GsonConverterFactory.create(gson)) //json을 변환해줄 Gson 변환 클래스 등록
				.build();
				
	}
	
	
	private static Retrofit getMultipartInstance(String baseUrl) {
		
		OkHttpClient defaultHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
	        @Override
	        public Response intercept(Chain chain) throws IOException {
	            Request request = chain.request().newBuilder()
	                    .addHeader("Content-Type","image/png")
	                    .build();
	            return chain.proceed(request);

	        }
	    }).build();
		
		
		Gson gson = new GsonBuilder()
				.setLenient()
				.create();

		return new Retrofit
				.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(GsonConverterFactory.create(gson)) //json을 변환해줄 Gson 변환 클래스 등록
				.client(defaultHttpClient)
				.build();
	}
}
