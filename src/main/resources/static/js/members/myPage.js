function removeFavoriteCategory(id){
    var result = confirm("삭제하시겠습니까?");

    if(result){
        var categoryId = id
        const data = {
            categoryId : categoryId
        }

        $.ajax({
            url: "/favorites/delete/"+ id,
            data : data,
            type: "POST",
        }).done(function (data){
            alert('삭제되었습니다')
            location.reload();
        });
    }
}


function cancelAppointment(appointmentId){
    var result = confirm("약속을 취소 하시겠습니까?");

    if(result){
        var appointmentId = appointmentId

        $.ajax({
            url: "/appointments/"+appointmentId+ "/status/cancel",
            type: "PUT",
        }).done(function (data){
            alert('취소되었습니다')
            location.reload();
        });
    }
}