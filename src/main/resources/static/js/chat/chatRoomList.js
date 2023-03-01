window.onload = function(){
    var length = $(".chatroom-list").children().length;
    if(length < 1){
        $(".chatroom-list").append('<div class="chatroom-empty"><div class="empty-message">채팅목록이 없습니다😢</div></div>');
    }
}