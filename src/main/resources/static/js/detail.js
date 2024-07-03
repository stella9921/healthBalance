function goUpdate(){
    if(!confirm('게시글을 수정하시겠습니까?')){
        return;
    }
    var boardId = document.querySelector('input[name="boardId"]').value;
    window.location.href='/board/edit/' + boardId;
//수정해야하는 그 글로 이동해야하니깐 boardId 가져옴

}

function goDelete(){
    if(confirm('게시글을 삭제하시겠습니까?')){
        //확인인 경우 삭제 절차 진행
        var boardId=document.querySelector('input[name="boardId"]').value;
        //form 을 통해 post 요청으로 서버에 삭제 요청하도록 변경
        var form=document.createElement('form');
        form.method='POST';
        form.action='/board/delete/'+boardId;
        document.body.appendChild(form);
        form.submit();

    }
    //취소 누른 경우 아무것도 안해요
}

//타임리프는 서버 사이드 템플릿 엔진임. 서버에서 html에 랜더링 할 때 사용
//ajax는 클라이언트 측에서 실행됨. th 문법은 사용 불가
const loginId=$('input[name="loginId"]').val();

//댓글 관련 ajax(jquery 안에 있는 문법)
//날짜 포맷
function formatDate(dateString){
    const now=new Date();
    const commentDate=new Date(dateString);
    //문자열을 date 객체로 변환함.

    //각각 now 에서 년달일 가져오는거임
    const nowYear=now.getFullYear();
    const nowMonth=now.getMonth();
    const nowDate=now.getDate();

    //각각 댓글 단 날짜에서 똑같이 년달일 가져온 거임
    const commentYear=commentDate.getFullYear();
    const commentMonth=commentDate.getMonth();
    const commentDateDate=commentDate.getDate();

    let displayText="";

    //년 월 일 이 모두 같은 경우 오늘 로 표시
    if(nowYear === commentYear&&nowMonth ===commentMonth&&nowDate === commentDate){
        displayText = "오늘";
    }else{
        //그 외의 경우 정해진 포맷으로 표시

        //마지막 두 자리 가져옴 거임
        const yy=commentYear.toString().slice(-2);
        //월은 0부터 시작하니깐 1 더해야함
        const M=commentMonth+1;
        const d=commentDateDate;
        //두 자리수 인경우 앞에 0을 붙인 다는뜻
        const HH=commentDate.getHours().toString().padStart(2,'0');
        const mm=commentDate.getMinutes().toString().padStart(2,'0');

        displayText=`${yy}년 ${M}월 ${d}일 ${HH}시 ${mm}분`;
    }

    return displayText;
}

//페이지가 처음 로드될 때 댓글 목록 조회함숙 실행되도록 한다.
$(document).ready(function (){
    let boardId=$('input[name="boardId"]').val();
    getComments(boardId);
})

//댓글 목록 조회함수
function getComments(boardId){
    $.ajax({

        method:'get',
        url:'/comments/' + boardId,
        success:function(data){
            let commentListArea=$('.comment-list')

            //댓글이 작성될 해당 섹션 비우기
            commentListArea.empty();

            //댓글 없을 때 표시할 html
            if(data.length === 0){
                commentListArea.append(
                    `<div class="alert alert-info">
                        첫번째 댓을 남기시오!
                        </div>
                `
                );
            }

            //댓 있을 때 목록 뿌려줄 반복문
            data.forEach(function(comment){
                let commentDate=formatDate(comment.commentRegisterDate);
                let buttons='';
                let editStr='';

                //작성일 수정일 비교하여 html 에 다른 모양으로 표시
                if(comment.commentUpdateDate !== comment.commentRegisterDate){
                    commentDate=formatDate(comment.commentUpdateDate);
                    editStr='(수정)';
                }

                //현재로그인 된 계정과 댓글 작성자가 동일하다면 만들어줄 버튼
                if(loginId === comment.providerId){

                }

            }
        }
    })
}