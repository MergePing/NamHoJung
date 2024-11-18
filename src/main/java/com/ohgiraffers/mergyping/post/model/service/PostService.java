package com.ohgiraffers.mergyping.post.model.service;

import com.ohgiraffers.mergyping.comment.model.dto.CommentDTO;
import com.ohgiraffers.mergyping.post.model.dao.PostMapper;
import com.ohgiraffers.mergyping.post.model.dto.InsertPostDTO;
import com.ohgiraffers.mergyping.post.model.dto.PostDTO;
import com.ohgiraffers.mergyping.post.model.dto.SelectPostDTO;
import com.ohgiraffers.mergyping.user.model.dto.MyPageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService {


    // 매퍼 주입
    private final PostMapper postMapper;


    // 매퍼 주입을 위한 생성자
    @Autowired
    public PostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }


    // 게시글 목록 조회하는 메소드
    public List<PostDTO> postList() {
        List<PostDTO> postList = postMapper.postList();

        return postList;
    }

    // 게시글 정렬하는 메소드
    public List<PostDTO> postListSort(String orderBy, String category, int page, int pageSize) {
        int offset =  (page - 1) * pageSize;
        Map<String, Object> params = new HashMap<>();
        params.put("orderBy", orderBy);
        params.put("category", category);
        params.put("offset", offset);
        params.put("pageSize", pageSize);

        return postMapper.postListSort(params);
    }

    // 게시글 정렬하는 메소드
    public List<PostDTO> postListSort1(String orderBy, String category, int page, int pageSize) {
        int offset =  (page - 1) * pageSize;
        Map<String, Object> params = new HashMap<>();
        params.put("orderBy", orderBy);
        params.put("category", category);
        params.put("offset", offset);
        params.put("pageSize", pageSize);

        return postMapper.postListSort(params);
    }



    //즐겨찾기 상태 업데이트
    public void updateFavoriteStatus(int postNo, boolean isFavorite) {

        // 게시글 번호와 즐겨찾기 상태룰 매퍼를 통해 업데이트
        postMapper.updateFavoriteStatus(postNo, isFavorite);
    }




    // 게시글 번호로 세부 게시글 조회
    public SelectPostDTO selectById(int postNo) {

        // 게시글 번호를 통해 매퍼로 특정 게시물 조회, 반환
        return postMapper.selectById(postNo);
    }


    // 게시글 목록 조회
    public List<PostDTO> getPostList() {

        // 매퍼를 통해 게시물 전체 목록 반환
        return postMapper.postList();
    }


    //무서워요 상태 업데이트
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
    public void updateLikeStatus(int commentNo, boolean isLike) {

        if (isLike) {
            postMapper.incrementLikeCount(commentNo, true);
        } else {
            postMapper.decrementLikeCount(commentNo, false);
        }
    }

    public int getLikeNumber(int commentNo){
        return postMapper.getLikeNumber(commentNo);
    }







    // 새로운 게시물 생성
    public void createPost(InsertPostDTO insertPostDTO) {

        // 컨트롤러에서 만들어 놓은 selectPostDTO 를 매퍼를 통해 삽입
        postMapper.insertPost(insertPostDTO);
    }


    // 게시물 개수 조회
    public int getPostCount() {

        // 매퍼를 통해 게시물의 개수 반환
        return postMapper.getPostCount();
    }


    // 최대 게시물 번호 조회
    public int getMaxPostNo() {

        // 매퍼를 통해 게시물 번호 중에 가장 큰 값 반환
        return postMapper.getMaxPostNo();
    }

    public List<PostDTO> searchPost(String keyword) {
        System.out.println("keyword = " + keyword);
        // 여기도 잘나옴
        return postMapper.searchPost("%" + keyword + "%");
    }

    public List<PostDTO> getPostsByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return postMapper.getPostsByPage(offset, pageSize);
    }

    public List<CommentDTO> getCommentsByPostNo(int postNo) {
        return postMapper.selectCommentsByPostNo(postNo);
    }


    public void updatePost(InsertPostDTO postDTO) {
        postMapper.updatePost(postDTO);
    }

//    public void deletePost(int postNo) {
//        postMapper.deletePost(postNo);
//    }


    public boolean addComment(CommentDTO commentDTO) {

        // 매퍼 메서드 호출하여 댓글 추가하고 성공 여부 반환
        int result = postMapper.insertComment(commentDTO);
        return result > 0;  // 결과가 0보다 크면 성공, 그렇지 않으면 실패
    }

    public boolean incrementCommentCount(int postNo) {
        try {
            postMapper.incrementCommentCount(postNo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Transactional
    public boolean deleteComment(int commentNo) {
        try {
            int result = postMapper.deleteComment(commentNo);
            return result > 0;  // 삭제된 행이 있으면 true 반환
        } catch (Exception e) {
            e.printStackTrace();
            return false;  // 삭제 실패 시 false 반환
        }

    }

    public void decreaseCommentCount(int postNo) {
        postMapper.decreaseCommentCount(postNo);
    }

    @Transactional
    public boolean updateComment(int commentNo, String commentContent, int userNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("commentNo", commentNo);
        params.put("commentContent", commentContent);
        params.put("userNo", userNo);

        int updatedRows = postMapper.updateComment(params);
        return updatedRows > 0;
    }



    @Transactional
    public boolean deletePost(int postNo) {
        try {
            int result = postMapper.deletePost(postNo);
            return result > 0;  // 삭제된 행이 있으면 true 반환
        } catch (Exception e) {
            e.printStackTrace();
            return false;  // 삭제 실패 시 false 반환
        }
    }

    public SelectPostDTO selectPostWriter(int postNo) {
            return postMapper.selectPostWriter(postNo);
    }




}
