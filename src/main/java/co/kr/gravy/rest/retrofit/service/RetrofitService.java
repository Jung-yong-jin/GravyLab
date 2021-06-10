package co.kr.gravy.rest.retrofit.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import co.kr.gravy.rest.retrofit.dto.PostResultDTO;
import co.kr.gravy.rest.sample.entity.SampleDTO;
import co.kr.gravy.rest.sample.entity.SampleVO;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitService {
	
	@GET("/sample/mybatisSamplesB")
    Call<List<SampleDTO>> mybatisSamplesB();
	
	
	@GET("/sample/getAllSamples")
    Call<List<SampleVO>> getTest();
	
	@GET("/api/users/2")
	Call<Object> getUser();
	
	// @GET( EndPoint-자원위치(URI) )
   	@GET("posts/{post}")
 	Call<PostResultDTO> getPosts(@Path("post") String post);
   	
   	@POST("sample/save")
   	Call<SampleVO> save(@Body SampleDTO sampleDTO);
   	
   	@POST("web/getList")
   	Call<Object> getList(@Query("name") String name, @Query("age") int age);
   	
   	@FormUrlEncoded
   	@POST("web/getList")
   	Call<Object> getFormList(@Field("name") String name, @Query("age") int age);
   	
   	
   	@GET("web/queryMap")
   	Call<Map<String, Object>> queryMap(@QueryMap Map<String, Object> option);
   	
   	@Multipart
    @POST("web/uploadFile")
   	Call<Map<String, Object>> uploadFile(@PartMap() LinkedHashMap<String, RequestBody> partMap, @Part List<MultipartBody.Part> names);
   	
}
