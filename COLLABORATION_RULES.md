# Branch
<br/>

## master 브랜치
### 최종 배포를 위한 브랜치
### develop 브랜치에서 문제가 없이 병합됐고 기능에 문제가 없을 경우, 해당 브랜치에 최종 병합

<br/>

## develop 브랜치
### 기능 생성후 기본적인 병합(merge)이 이루어지는 브랜치
1. 작업 전 develop 브랜치에서 pull
2. 병합은 최소 두명의 리뷰어가 확인하고 comment를 통해 확인되면 merge
3. 최종 병합은 형상 관리자가 행함

## feature 브랜치
### 각 팀원이 기본적으로 기능 하나당 하나씩 생성하는 브랜치
1. 명명규칙 : feature/#번호-기능(영문)
2. 대문자 금지
3. 1기능 당 한개의 feature 브랜치 생성

<br/>

# issue
<br/>

## issue 작성
1. 명명 규칙 예시 : [대문자][첫글자만 대문자]기능설명(한글)
2. [FEATURE][Function]기능 설명(한글)
3. 명명 규칙에서 카멜케이스가 필요할 경우 카멜케이스로 작성
4. 1 기능당 1개의 이슈 생성
5. pull request까지 마치고 merge로 관련된 이슈는 close
6. Assigness와 Label 빼먹지 않고 작성
7. 템플릿에 맞게 작성하고 템플릿에 내용들 빼먹지 않기
8. 템플릿에서 필요없어진 부분은 알아서 제거 후 깔끔하게 정리

# pull request
<br/>

## Pull Request 작성
1. 명명 규칙 예시 : [대문자][첫글자만 대문자]기능설명(한글)
2. [FEATURE][PullRequest]기능
3. 명명 규칙에서 카멜케이스가 피룡할 경우 카멜케이스로 작성
4. pull request의 reviewers, assigness, labels 빼먹지 않고 작성
5. pull request의 템플릿에 맞게 작성 (공백이 생길 경우 잘 정리)
6. 자신이 리뷰어가 아니어도 확인했다면 comment
7. 2인 이상의 comment가 달리면 형상관리자가 merge

# commit
<br/>
1. 명명 규칙
헤더 : type 작성
본문 : 기능 설명 (영어로 - 카멜케이스)
푸터 : 날짜 작성

type 종률
## master 브랜치
### 최종 배포를 위한 브랜치
### develop 브랜치에서 문제가 없이 병합됐고 기능에 문제가 없을 경우, 해당 브랜치에 최종 병합

<br/>

## develop 브랜치
### 기능 생성후 기본적인 병합(merge)이 이루어지는 브랜치
1. 작업 전 develop 브랜치에서 pull
2. 병합은 최소 두명의 리뷰어가 확인하고 comment를 통해 확인되면 merge
3. 최종 병합은 형상 관리자가 행함

## feature 브랜치
### 각 팀원이 기본적으로 기능 하나당 하나씩 생성하는 브랜치
1. 명명규칙 : feature/#번호-기능(영문)
2. 대문자 금지
3. 1기능 당 한개의 feature 브랜치 생성

<br/>

# issue
<br/>

## issue 작성
1. 명명 규칙 예시 : [대문자][첫글자만 대문자]기능설명(한글)
2. [FEATURE][Function]기능 설명(한글)
3. 명명 규칙에서 카멜케이스가 필요할 경우 카멜케이스로 작성
4. 1 기능당 1개의 이슈 생성
5. pull request까지 마치고 merge로 관련된 이슈는 close
6. Assigness와 Label 빼먹지 않고 작성
7. 템플릿에 맞게 작성하고 템플릿에 내용들 빼먹지 않기
8. 템플릿에서 필요없어진 부분은 알아서 제거 후 깔끔하게 정리

# pull request
<br/>

## Pull Request 작성
1. 명명 규칙 예시 : [대문자][첫글자만 대문자]기능설명(한글)
2. [FEATURE][PullRequest]기능
3. 명명 규칙에서 카멜케이스가 피룡할 경우 카멜케이스로 작성
4. pull request의 reviewers, assigness, labels 빼먹지 않고 작성
5. pull request의 템플릿에 맞게 작성 (공백이 생길 경우 잘 정리)
6. 자신이 리뷰어가 아니어도 확인했다면 comment
7. 2인 이상의 comment가 달리면 형상관리자가 merge

# commit
<br/>
1. 명명 규칙
헤더 : type 작성
본문 : 기능 설명 (영어로 - 카멜케이스)
푸터 : 날짜 작성

2. type 종류
feat : 새로운 기능
fix : 버그 수정
build : 빌드 관련 수정
chore : 기타 작은 수정
docs : 문서 수정
style : 코드 스타일 혹은 포맷 수정
refactor : 코드 리팩토링 수정
test : 테스트 시

3. 예시
feat:PlusFunction/20241021

