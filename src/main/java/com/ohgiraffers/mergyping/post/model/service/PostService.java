package com.ohgiraffers.mergyping.post.model.service;

import com.ohgiraffers.mergyping.post.model.dao.PostMapper;
import com.ohgiraffers.mergyping.post.model.dto.PostDTO;
import com.ohgiraffers.mergyping.post.model.dto.SelectPostDTO;
import com.ohgiraffers.mergyping.user.model.dto.MyPageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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


    public MyPageDTO findNickName(int userNo) {
        return postMapper.findNickName(userNo);
    }

    public Integer getUserAttendanceCount(int userNo) {
        return postMapper.getUserAttendanceCount(userNo);
    }

    public int calculateLevel(Integer attendanceCount) {
        if (attendanceCount >= 161) {
            return 10; // 고인물 정원사
        } else if (attendanceCount >= 141) {
            return 9;  // 최고의 정원사
        } else if (attendanceCount >= 121) {
            return 8;  // 역사에 남을 정원사
        } else if (attendanceCount >= 101) {
            return 7;  // 베테랑 정원사
        } else if (attendanceCount >= 81) {
            return 6;  // 성공한 정원사
        } else if (attendanceCount >= 61) {
            return 5;  // 최상급 정원사
        } else if (attendanceCount >= 41) {
            return 4;  // 고급 정원사
        } else if (attendanceCount >= 21) {
            return 3;  // 중급 정원사
        } else if (attendanceCount >= 11) {
            return 2;  // 초보 정원사
        } else {
            return 1;  // 신입 정원사
        }
    }

    public void updateUserLevel(int userNo, int levelNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("userNo", userNo);
        params.put("levelNo", levelNo);
        postMapper.updateUserLevel(params);
    }

    public String getLevelName(int levelNo) {
        return postMapper.getLevelName(levelNo);
    }
}