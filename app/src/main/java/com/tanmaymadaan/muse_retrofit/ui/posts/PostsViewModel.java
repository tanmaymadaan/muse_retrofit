package com.tanmaymadaan.muse_retrofit.ui.posts;

import android.util.Log;

import com.tanmaymadaan.muse_retrofit.data.source.Api;
import com.tanmaymadaan.muse_retrofit.data.models.Post;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsViewModel extends ViewModel {
    private MutableLiveData<List<Post>> postList;

    public LiveData<List<Post>> getPosts() {
        if (postList == null) {
            postList = new MutableLiveData<List<Post>>();
            loadPosts();
        }

        return postList;
    }

    private void loadPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Post>> call = api.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts;
                posts = response.body();
                postList.setValue(posts);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("RetrofitService", t.getMessage());
            }
        });
    }
}
