package com.ohgiraffers.mergyping.post.model.service;

import com.ohgiraffers.mergyping.post.model.dao.PostMapper;
import com.ohgiraffers.mergyping.post.model.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class PostService {

    private final PostMapper postMapper;

    @Autowired
    public PostService(PostMapper postMapper){
        this.postMapper=postMapper;
    }

    public List<PostDTO> postList() {
        List<PostDTO> postList = postMapper.postList();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (PostDTO post : postList) {
            post.setFormattedDate(formatter.format(post.getPostDate()));
        }
        return postList;
    }


    public void updateFavoriteStatus(int postNo, boolean isFavorite) {
        postMapper.updateFavoriteStatus(postNo, isFavorite);
    }


    public List<PostDTO> getPostsByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<PostDTO> posts = postMapper.getPostsByPage(offset, pageSize);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (PostDTO post : posts) {
            post.setFormattedDate(formatter.format(post.getPostDate()));
        }
        return posts;
    }

}
