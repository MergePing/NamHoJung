package com.ohgiraffers.mergyping.post.model.service;

import com.ohgiraffers.mergyping.comment.model.dto.CommentDTO;
import com.ohgiraffers.mergyping.post.model.dao.PostMapper;
import com.ohgiraffers.mergyping.post.model.dto.PostDTO;
import com.ohgiraffers.mergyping.post.model.dto.SelectPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

            // 무서워요 상태가 true인 경우 무서워요 수 증가
            if (isScary) {
                postMapper.incrementScaryCount(postNo, true);
            }

            // 무서워요 상태가 false인 경우 무서워요 수 감소
            else {
                postMapper.decrementScaryCount(postNo, false);
            }
        }


        // 안무서워요 상태 업데이트
        public void updateNotScaryStatus(int postNo, boolean isNotScary) {

            // 안무서워요 상태가 true인 경우 안무서워요 수 증가
            if (isNotScary) {
                postMapper.incrementNotScaryCount(postNo, true);
            }

            // 안무서워요 상태가 false인 경우 안무서워요 수 감소
            else {
                postMapper.decrementNotScaryCount(postNo, false);
            }
        }


        // 무서워요 숫자 조회
        public int getScaryNumber(int postNo) {

            // 게시물 번호를 통해 매퍼로 특정 게시물의 무서워요 수 조회 후 반환
            return postMapper.getScaryNumber(postNo);
        }


        // 안무서워요 숫자 조회
        public int getNotScaryNumber(int postNo) {

            // 게시물 번호를 통해 매퍼로 특정 게시물의 안무서워요 수 조회 후 반환

            return postMapper.getNotScaryNumber(postNo);
        }


        // 새로운 게시물 생성
        public void createPost(SelectPostDTO selectPostDTO) {

            // 컨트롤러에서 만들어 놓은 selectPostDTO 를 매퍼를 통해 삽입
            postMapper.insertPost(selectPostDTO);
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
}

