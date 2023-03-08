function verify() {

    var email = $('#email').val();

    if(email == null || email.length == 0) {
        alert("이메일을 입력하여 주십시오.");
        return;
    }

    const data = {
        to : email
    };

    $.ajax({
        url: "/members/auth",
        type: "GET",
        data: data,
    }).done(function () {
        alert("인증메일이 발송되었습니다.");
    });
}