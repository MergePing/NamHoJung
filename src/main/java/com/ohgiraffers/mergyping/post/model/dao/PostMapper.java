package com.ohgiraffers.mergyping.post.model.dao;

import com.ohgiraffers.mergyping.comment.model.dto.CommentDTO;
import com.ohgiraffers.mergyping.post.model.dto.InsertPostDTO;
import com.ohgiraffers.mergyping.post.model.dto.PostDTO;
import com.ohgiraffers.mergyping.post.model.dto.SelectPostDTO;
import com.ohgiraffers.mergyping.user.model.dto.MyPageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {

    // 게시글 전체 목록조회
    List<PostDTO> postList();

    // 게시글 정렬
    List<PostDTO> postListSort(Map<String, Object> params);

    // 게시글 번호와 즐겨찾기 여부로 즐겨 찾기 상태 업데이트
    void updateFavoriteStatus(@RequestParam("postNo") int postNo, @RequestParam("isFavorite") boolean isFavorite);

    // 시작인덱스와 한페이지에 표시할 게시물의 수로 페이징을 위한 게시글 목록 조회
    List<PostDTO> getPostsByPage(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize);

    // 게시글 번호로 게시글 세부 조회
    SelectPostDTO selectById(@RequestParam("postNo") int postNo);

    // 게시글 번호와 무서워요 여부로 무서워요 개수 증가
    void incrementScaryCount(@RequestParam("postNo") int postNo, @RequestParam("isScary") boolean isScary);
    void decrementScaryCount(@RequestParam("postNo") int postNo,@RequestParam("isScary") boolean isScary);
    void incrementNotScaryCount(@RequestParam("postNo") int postNo,@RequestParam("isNotScary") boolean isNotScary);
    void decrementNotScaryCount(@RequestParam("postNo") int postNo,@RequestParam("isNotScary") boolean isNotScary);
    int getScaryNumber( int postNo);
    int getNotScaryNumber(int postNo);
    int getLikeNumber(int commentNo);
    void incrementLikeCount(@RequestParam("commentNo") int commentNo,@RequestParam("isLike") boolean isLike);
    void decrementLikeCount(@RequestParam("commentNo") int commentNo,@RequestParam("isLike") boolean isLike);


    // 새로만든 selectPostDTO를 통해 새로운 게시물 생성
    void insertPost(InsertPostDTO insertPostDTO);

    //  전체 게시물의 개수 조회
    int getPostCount();

    // 현재 게시물 번호의 최대값 조회
    int getMaxPostNo();

    void editPost(@RequestParam("postNo") int postNo, @RequestParam("postTitle") String postTitle, @RequestParam("postContent") String postContent);

    void updatePost(InsertPostDTO postDTO);

//    void deletePost(@RequestParam("postNo") int postNo);

    List<PostDTO> searchPost(@RequestParam("keyword") String keyword);


    List<CommentDTO> selectCommentsByPostNo(int postNo);


    int insertComment(CommentDTO commentDTO);

    void incrementCommentCount(int postNo);

    int deleteComment(int commentNo);

    void decreaseCommentCount(int postNo);

    int updateComment(Map<String, Object> params);




    SelectPostDTO selectPostWriter(int postNo);

    int deletePost(int postNo);


//    CommentDTO getCommentByNo(int commentNo);
//
//    int updateComment(CommentDTO comment);

}
