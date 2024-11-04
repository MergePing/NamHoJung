package com.ohgiraffers.mergyping.post.model.dao;

import com.ohgiraffers.mergyping.post.model.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostDTO> postList();
    void updateFavoriteStatus(@RequestParam("postNo") int postNo, @RequestParam("isFavorite") boolean isFavorite);
    void insertPost(PostDTO postDTO);
    List<PostDTO> getPostsByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);
}


