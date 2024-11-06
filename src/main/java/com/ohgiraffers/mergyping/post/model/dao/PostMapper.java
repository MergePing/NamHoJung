package com.ohgiraffers.mergyping.post.model.dao;

import com.ohgiraffers.mergyping.post.model.dto.PostDTO;
import com.ohgiraffers.mergyping.post.model.dto.SelectPostDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostDTO> postList();
    void updateFavoriteStatus(@RequestParam("postNo") int postNo, @RequestParam("isFavorite") boolean isFavorite);
    List<PostDTO> getPostsByPage(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize);
    SelectPostDTO selectById(@RequestParam("postNo") int postNo);
    void incrementScaryCount(@RequestParam("postNo") int postNo, @RequestParam("isScary") boolean isScary);
    void decrementScaryCount(@RequestParam("postNo") int postNo,@RequestParam("isScary") boolean isScary);
    void incrementNotScaryCount(@RequestParam("postNo") int postNo,@RequestParam("isNotScary") boolean isNotScary);
    void decrementNotScaryCount(@RequestParam("postNo") int postNo,@RequestParam("isNotScary") boolean isNotScary);
    int getScaryNumber( @RequestParam("postNo") int postNo);
    int getNotScaryNumber(@RequestParam("postNo") int postNo);
    void insertPost(SelectPostDTO selectPostDTO);
}


