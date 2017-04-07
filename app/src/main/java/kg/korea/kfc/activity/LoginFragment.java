package kg.korea.kfc.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

import org.json.JSONException;
import org.json.JSONObject;

import kg.korea.kfc.R;
import kg.korea.kfc.databinding.FragmentLoginBinding;

/**
 * Created by itsm02 on 2017. 4. 6..
 */

public class LoginFragment extends Fragment implements View.OnClickListener {

    private FragmentLoginBinding fragmentLoginBinding;

    /**
     * client 정보를 넣어준다.
     * https://developers.naver.com/apps/#/myapps/6T0_ciojDFEbBZRNlhkb/overview
     */
    private static String OAUTH_CLIENT_ID = "6T0_ciojDFEbBZRNlhkb";
    private static String OAUTH_CLIENT_SECRET = "Zi1r6yqNNj";
    private static String OAUTH_CLIENT_NAME = "(주)KFC";

    private OAuthLogin mOAuthLoginInstance;
    private Context mContext;

    private String accessToken;
    private String refreshToken;
    private long expiresAt;
    private String tokenType;

    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
                accessToken = mOAuthLoginInstance.getAccessToken(mContext);
                refreshToken = mOAuthLoginInstance.getRefreshToken(mContext);
                expiresAt = mOAuthLoginInstance.getExpiresAt(mContext);
                tokenType = mOAuthLoginInstance.getTokenType(mContext);

//                mOauthAT.setText(accessToken);
//                mOauthRT.setText(refreshToken);
//                mOauthExpires.setText(String.valueOf(expiresAt));
//                mOauthTokenType.setText(tokenType);
//                mOAuthState.setText(mOAuthLoginInstance.getState(mContext).toString());

                Log.e("DEBUG", "accessToken = " + accessToken);
                Log.e("DEBUG", "refreshToken = " + refreshToken);
                Log.e("DEBUG", "expiresAt = " + expiresAt);
                Log.e("DEBUG", "tokenType = " + tokenType);

            } else {

                /*

                    024             Authentication failed / 인증에 실패했습니다.
                    028	            Authentication header not exists / OAuth 인증 헤더(authorization header)가 없습니다.
                    403	            Forbidden / 호출 권한이 없습니다.	    API 요청 헤더에 클라이언트 ID와 Secret 값을 정확히 전송했는지 확인해보시길 바랍니다.
                    404             Not Found / 검색 결과가 없습니다.
                    500             Internal Server Error / 데이터베이스 오류입니다.
                    invalid_request             invalid_request
                    unauthorized_client	        인증받지 않은 인증 코드(authorization code)로 요청했습니다.
                    unsupported_response_type	정의되지 않은 반환 형식으로 요청했습니다.
                    server_error	            네이버 인증 서버의 오류로 요청을 처리하지 못했습니다.

                 */

                String errorCode = mOAuthLoginInstance.getLastErrorCode(mContext).getCode();
                String errorDesc = mOAuthLoginInstance.getLastErrorDesc(mContext);
                Toast.makeText(mContext, "errorCode:" + errorCode + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }
        }

        ;
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        fragmentLoginBinding.btnLoginNaver.setOnClickListener(this);


        mContext = getContext();

        mOAuthLoginInstance = OAuthLogin.getInstance();
        mOAuthLoginInstance.init(getContext(), OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME);


        return fragmentLoginBinding.getRoot();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_login_naver:

                if (!TextUtils.isEmpty(mOAuthLoginInstance.getAccessToken(mContext))) {
                    new RequestApiTask().execute();
                } else {
                    /*

                    5.2. startOAuthLoginActivity() 메서드를 이용한 로그인
                        OAuthLogin.startOAuthLoginActivity() 메서드를 직접 실행해 로그인하면 먼저 갱신 토큰이 있는지 확인합니다.
                        갱신 토큰이 있으면 접근 토큰의 갱신을 시도합니다.
                        − 갱신에 성공하면 OAuthLoginHandler 객체가 호출됩니다.
                        − 갱신에 실패하면 로그인 창이 나타납니다.
                        갱신 토큰이 없으면 로그인 창이 나타납니다.
                        * 접근 토큰 갱신 관련 주의: 접근토큰은 일정 시간(현재 1시간)이 지나면 만료되기 때문에 만료시간이 지난 경우 refreshAccessToken() 을 호출해서 access token 을 갱신해줘야 합니다.
                        또한 refreshAccessToken() 을 사용하시는 경우 메쏘드의 실행이 끝나면 access token 을 받을 수 있기 때문에 좀 더 편리하게 개발하실 수 있습니다.

                     */
                    mOAuthLoginInstance.startOauthLoginActivity(getActivity(), mOAuthLoginHandler);
                }
                break;
        }
    }

    private class RequestApiTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(Void... params) {
//            String url = "https://openapi.naver.com/v1/nid/getUserProfile.xml";
            String url = "https://openapi.naver.com/v1/nid/me";
            String accessToken = mOAuthLoginInstance.getAccessToken(mContext);
            long expiresAt = mOAuthLoginInstance.getExpiresAt(mContext);
            Log.e("DEBUG", "accessToken = " + accessToken);
//            Log.e("DEBUG", "expiresAt = " + expiresAt / (3600 * 24));
            return mOAuthLoginInstance.requestApi(mContext, accessToken, url);
        }

        protected void onPostExecute(String content) {
            Log.e("DEBUG", "" + content);

              /*

                    024             Authentication failed / 인증에 실패했습니다.
                    028	            Authentication header not exists / OAuth 인증 헤더(authorization header)가 없습니다.
                    403	            Forbidden / 호출 권한이 없습니다.	    API 요청 헤더에 클라이언트 ID와 Secret 값을 정확히 전송했는지 확인해보시길 바랍니다.
                    404             Not Found / 검색 결과가 없습니다.
                    500             Internal Server Error / 데이터베이스 오류입니다.
                    invalid_request             invalid_request
                    unauthorized_client	        인증받지 않은 인증 코드(authorization code)로 요청했습니다.
                    unsupported_response_type	정의되지 않은 반환 형식으로 요청했습니다.
                    server_error	            네이버 인증 서버의 오류로 요청을 처리하지 못했습니다.

                 */

            try {
                JSONObject jsonObject = new JSONObject(content);


                String resultcode = jsonObject.getString("resultcode");
                String message = jsonObject.getString("message");

                Log.e("DBEUG", "resultcode = " + resultcode);
                Log.e("DBEUG", "message = " + message);

                if ("00".equals(resultcode)) {
                    String response = jsonObject.getString("response");
                    JSONObject jsonObject2 = new JSONObject(response);

                    String email = jsonObject2.getString("email");
                    String name = jsonObject2.getString("name");
                    String nickname = jsonObject2.getString("nickname");
//                String ci = jsonObject2.getString("ci");


                    Log.e("DBEUG", "response = " + response);
                    Log.e("DBEUG", "email = " + email);
                    Log.e("DBEUG", "name = " + name);
                    Log.e("DBEUG", "nickname = " + nickname);
                }
//                Log.e("DBEUG", "ci = " + ci);

//                jsonObject2.getString("resultcode");
//                jsonObject2.getString("resultcode");


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
