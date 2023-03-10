function updateRecommend() {

    var url = window.location.href;
    var postId = url.split('/').slice(-1);

    alert("이 게시글을 추천하셨습니다!");
    location.href = "/posts/updateRecommend?postId=" + postId;
}