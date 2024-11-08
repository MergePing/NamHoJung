package com.ohgiraffers.mergyping.post.model.service;

import com.ohgiraffers.mergyping.post.model.dao.PostMapper;
import com.ohgiraffers.mergyping.post.model.dto.PostDTO;
import com.ohgiraffers.mergyping.post.model.dto.SelectPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class PostService {

    private final PostMapper postMapper;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    public List<PostDTO> postList() {
        List<PostDTO> postList = postMapper.postList();

        return postList;
    }


    //즐겨찾기 상태
    public void updateFavoriteStatus(int postNo, boolean isFavorite) {
        postMapper.updateFavoriteStatus(postNo, isFavorite);
    }


    // 세부 게시글 조회
    public SelectPostDTO selectById(int postNo) {
        return postMapper.selectById(postNo);
    }


    // 게시글 목록 조회
    public List<PostDTO> getPostList() {
        return postMapper.postList();
    }


    //무서워요 상태
    public void updateScaryStatus(int postNo, boolean isScary) {
        if (isScary) {
            postMapper.incrementScaryCount(postNo, true);
        } else {
            postMapper.decrementScaryCount(postNo, false);
        }
    }

    public void updateNotScaryStatus(int postNo, boolean isNotScary) {
        if (isNotScary) {
            postMapper.incrementNotScaryCount(postNo, true);
        } else {
            postMapper.decrementNotScaryCount(postNo, false);
        }
    }

    // 무서워요 숫자
    public int getScaryNumber(int postNo) {
        return postMapper.getScaryNumber(postNo);
    }

    public int getNotScaryNumber(int postNo) {
        return postMapper.getNotScaryNumber(postNo);
    }


    public void createPost(SelectPostDTO selectPostDTO) {
        postMapper.insertPost(selectPostDTO);
    }

    public int getPostCount() {
        return postMapper.getPostCount();
    }

    public int getMaxPostNo() {
        return postMapper.getMaxPostNo();
    }
}