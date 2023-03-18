function goPopup(){
    var pop = window.open("http://3.38.181.136/jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes");
    //var pop = window.open("jusoPopup","pop","scrollbars=yes, resizable=yes"); // 모바일 웹
}

function jusoCallBack(roadAddrPart1, roadAddrPart2, roadFullAddr, jibunAddr, addrDetail, siNm, sggNm, zipNo)
{
    $("#roadAddrPart1").val(roadAddrPart1);
    $("#roadAddrPart2").val(roadAddrPart2);
    $("#roadFullAddr").val(roadFullAddr);
    $("#jibunAddr").val(jibunAddr);
    $("#addrDetail").val(addrDetail);
    $("#siNm").val(siNm);
    $("#sggNm").val(sggNm);
    $("#zipNo").val(zipNo);
}