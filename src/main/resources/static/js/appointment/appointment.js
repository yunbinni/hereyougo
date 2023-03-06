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

function completeAppointment(appointmentId){
    var result = confirm("완료하시겠습니까?");

    if(result){
        var appointmentId = appointmentId

        $.ajax({
            url: "/appointments/"+appointmentId+ "/status/complete",
            type: "PUT",
        }).done(function (data){
            alert('완료되었습니다')
            location.reload();
        });
    }
}