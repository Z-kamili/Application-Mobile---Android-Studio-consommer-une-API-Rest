package com.self_learning.restapisusingretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.self_learning.restapisusingretrofit.model.LoginRequest;
import com.self_learning.restapisusingretrofit.model.User;
import com.self_learning.restapisusingretrofit.service.RetrofitService;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private RetrofitService retrofit_Service;
    public static String s;


    private TextView _TextViewResults;
    private Button _LoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _TextViewResults = (TextView) findViewById(R.id._DisplayResults);
        _LoginButton = (Button) findViewById(R.id.login_Button);


//        OkHttpClient okHttpClient = new OkHttpClient
//                .Builder()
//                .addInterceptor(new Interceptor() {
//                    @NotNull
//                    @Override
//                    public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
//                        Request.Builder builder = chain.request().newBuilder();
//                        builder.addHeader("Authorization", s);
//                        return chain.proceed(builder.build());
//                    }
//                })
//                .build();
        // retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.8.86:8080/")
                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
                .build();
        retrofit_Service = retrofit.create(RetrofitService.class);
//        _GoToTheRegisterProcess();
        _GoToThePUTProcess();


        _LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _GoToTheLOGINProcess();
                goToTheMainActivity();
            }
        });
    }


    private void _GoToTheLOGINProcess() {
        LoginRequest loginRequest = new LoginRequest("Jamal_", "0KjQ8qP9KO");
        Call<User> loginMethod = retrofit_Service._LOGINMethod(loginRequest);
        loginMethod.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    _TextViewResults.setText("Code : " + response.code());
                    return;
                }
                User bodyResponse = response.body();
                Headers headerResponse = response.headers();

                String content_ = "";
                content_ += "CODE : " + response.code() + "\n";
                content_ += "JWT-TOKEN : " + headerResponse.get("Jwt-Token") + "\n";
                s = headerResponse.get("Jwt-Token");
                _TextViewResults.setText(content_);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                _TextViewResults.setText(t.getMessage());
            }
        });
    }


    private void goToTheMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("token", s);
        startActivity(intent);
    }

    private void _GoToTheRegisterProcess() {
        User _user = new User("Jamal_v01", "Amimi_v01", "0762987000", "AmimiJamal_v01@simplon.co", "Jamal__v01");
        Map<String, String> feilds = new HashMap<>();
        feilds.put("firstname", "Jamal");
        feilds.put("lastname", "Amimi");
        feilds.put("phone", "0762987763");
        feilds.put("email", "AmimiJamal@simplon.co");
        feilds.put("username", "Jamal_");
        // method 1
        Call<User> userCall = retrofit_Service._REGISTERMethod(_user);
//        // method 2
//        Call<User> userCall_FormData = retrofit_Service._REGISTERMethod("Jamal","Amimi", "0762987763", "AmimiJamal@simplon.co", "Jamal_");
//        // method 3
//        Call<User> userCall_FeildsMap = retrofit_Service._REGISTERMethod(feilds);
//
        // userCall_FormData, userCall_FeildsMap, userCall
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    _TextViewResults.setText("Code : " + response.code());
                    return;
                }
                User bodyResponse = response.body();
                String content_ = "";
                content_ += "CODE : " + response.code() + "\n";
                content_ += "User_Id : " + bodyResponse.getUser_id() + "\n";
                content_ += "User_Key : " + bodyResponse.getUser_key() + "\n";
                content_ += "FIRSTNAME : " + bodyResponse.getFirstname() + "\n";
                content_ += "LASTNAME : " + bodyResponse.getLastname() + "\n";
                content_ += "PHONE : " + bodyResponse.getPhone() + "\n";
                content_ += "EMAIL : " + bodyResponse.getEmail() + "\n";
                content_ += "USERNAME : " + bodyResponse.getUsername() + "\n";
                content_ += "PASSWORD : " + bodyResponse.getPassword() + "\n";
                content_ += "ROLE : " + bodyResponse.getRole() + "\n";
                _TextViewResults.setText(content_);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                _TextViewResults.setText(t.getMessage());
            }
        });
    }

    private void _GoToThePUTProcess() {
        Call<User> userPUTCall = retrofit_Service._PUTRequestMethod("Test_1", "Test_1", "0762987762", "Test_1@email.co", "Test_1_belbhiria", true, "Jamal__v01", "ROLE_SUPER_ADMIN");
        userPUTCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    _TextViewResults.setText("Code : " + response.code());
                    return;
                }
                User bodyResponse = response.body();
                String content_ = "";
                content_ += "CODE : " + response.code() + "\n";
                content_ += "FIRSTNAME : " + bodyResponse.getFirstname() + "\n";
                content_ += "LASTNAME : " + bodyResponse.getLastname() + "\n";
                content_ += "PHONE : " + bodyResponse.getPhone() + "\n";
                content_ += "EMAIL : " + bodyResponse.getEmail() + "\n";
                content_ += "USERNAME : " + bodyResponse.getUsername() + "\n";
                content_ += "ROLE : " + bodyResponse.getRole() + "\n";
                _TextViewResults.setText(content_);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                _TextViewResults.setText(t.getMessage());
            }
        });

    }


}