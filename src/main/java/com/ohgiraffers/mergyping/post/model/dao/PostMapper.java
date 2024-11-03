package com.ohgiraffers.mergyping.post.model.dao;

import com.ohgiraffers.mergyping.post.model.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostDTO> postList();
    void updateFavoriteStatus(@Param("postNo") int postNo, @Param("isFavorite") boolean isFavorite);
}
