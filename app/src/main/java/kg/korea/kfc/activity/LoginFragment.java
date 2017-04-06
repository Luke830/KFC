package kg.korea.kfc.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
//                mOAuthLoginInstance.startOauthLoginActivity(getActivity(), mOAuthLoginHandler);
                new RequestApiTask().execute();
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
            Log.e("DEBUG", "accessToken = " + accessToken);
            return mOAuthLoginInstance.requestApi(mContext, accessToken, url);
        }

        protected void onPostExecute(String content) {
            Log.e("DEBUG", "" + content);

            try {
                JSONObject jsonObject = new JSONObject(content);


                String resultcode = jsonObject.getString("resultcode");
                String message = jsonObject.getString("message");
                String response = jsonObject.getString("response");

                JSONObject jsonObject2 = new JSONObject(response);

                String email = jsonObject2.getString("email");
                String name = jsonObject2.getString("name");
                String nickname = jsonObject2.getString("nickname");
//                String ci = jsonObject2.getString("ci");


                Log.e("DBEUG", "resultcode = " + resultcode);
                Log.e("DBEUG", "message = " + message);
                Log.e("DBEUG", "response = " + response);
                Log.e("DBEUG", "email = " + email);
                Log.e("DBEUG", "name = " + name);
                Log.e("DBEUG", "nickname = " + nickname);
//                Log.e("DBEUG", "ci = " + ci);

//                jsonObject2.getString("resultcode");
//                jsonObject2.getString("resultcode");


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
