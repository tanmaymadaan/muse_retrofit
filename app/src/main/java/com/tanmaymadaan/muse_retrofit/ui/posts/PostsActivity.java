package com.tanmaymadaan.muse_retrofit.ui.posts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.tanmaymadaan.muse_retrofit.R;
import com.tanmaymadaan.muse_retrofit.data.models.Post;

import java.util.List;

public class PostsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView;
        textView = findViewById(R.id.textView);

        PostsViewModel viewModel = ViewModelProviders.of(this).get(PostsViewModel.class);

        viewModel.getPosts().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                for (Post post : posts) {
                    String postContent = "";
                    postContent += "\nUserID: " + post.getUserId();
                    postContent += "\nId: " + post.getId();
                    postContent += "\nTitle: " + post.getTitle();
                    postContent += "\nBody: " + post.getBody() + "\n\n";
                    textView.append(postContent);
                }
            }
        });
    }
}