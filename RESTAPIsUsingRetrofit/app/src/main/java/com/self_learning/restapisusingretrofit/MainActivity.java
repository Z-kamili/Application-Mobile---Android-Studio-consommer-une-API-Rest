package com.self_learning.restapisusingretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.self_learning.restapisusingretrofit.model.User;
import com.self_learning.restapisusingretrofit.service.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView _TextViewResults;
    private RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        Intent intent = getIntent();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _TextViewResults = (TextView) findViewById(R.id.textViewResult);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.8.86:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitService = retrofit.create(RetrofitService.class);

        // Injecting The Method getListOfUsersFromTheBasmaOnlineStore();
        getListOfUsersFromTheBasmaOnlineStore();

    }

    private void getListOfUsersFromTheBasmaOnlineStore() {
        Call<List<User>> listCall = retrofitService._GetUsers(LoginActivity.s);
        listCall.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    _TextViewResults.setText("Code : " + response.code());
                    return;
                }
                List<User> users = response.body();
                String _User = "";
                for (User user : users) {
                    _User = " " + user.getUsername() + "\n\n";
                    _TextViewResults.append(_User);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                _TextViewResults.setText(t.getMessage());
            }
        });
    }
}