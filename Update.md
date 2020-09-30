
# Update

Web_Project Blog Update

## 09/10
### Toast editor를 이용해서 서버에 글쓰기 구현
1. 에디터에서 image base64코드를 배열로 저장
2. 저장한 배열과 에디터 데이터를 서버로 전달
3. 전달받은 배열을 변환해서 서버 폴더에 저장
4. 에디터에서 전달받은 데이터에서 이미지에 대한 코드를 서버 폴더 위치로 치환
5. DB에 치환한 데이터를 저장
6. 클라이언트에서 요청시 DB 데이터를 넘겨주고 이미지를 정상적으로 받아오는지 확인

## 09/17
### Post page 구성
1. Toast View를 이용해서 원하는 Post 정상적으로 띄우는지 확인

## 09/20
### PostList Page 초기 화면 구성
1. Blog의 틀 완성하기
	- 임시 Post 데이터를 만들어서 PostList 받아오고 Paging 구현
	- PostList에서 Post 클릭시 View페이지로 이동
	- login한 사용자와 사용자의 Blog 매칭
	
2. APIMessage 제거
	- APIMessage format 대신 ResponseEntity를 사용
	
## 09/24
### Post의 이미지 저장 위치 변경
1. 각 포스트별 저장된 이미지 위치 변경 어플리케이션 내부 -> 외부
	- 추후 또 다른 경로로 위치를 변경하려면 application.properties에서 변경 가능