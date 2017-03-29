package kg.korea.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by itsm02 on 2017. 3. 28..
 */

public interface ApiService {

    public String SERVER_IP_REAL = "https://devoffpay.inicis.com/";

    @FormUrlEncoded
    @POST("offpay/chatbot/paymentList")
    Call<ResponseBody> queryPayMethod(
            @Field("paymethod") String paymethod,
            @Field("mailBuyer") String mailBuyer,
            @Field("lastCardNum") String lastCardNum,
            @Field("paymentMonth") String paymentMonth,
            @Field("pageNo") String pageNo,
            @Field("itemCount") String itemCount);
}
