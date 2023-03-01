function onChangeTest(category){
var parentId = category.value;
const data = {
   parentId:parentId
};

$.ajax({
     url: "/categories/second",
     data: data,
     type: "GET",
}).done(function (data){
    console.log(JSON.stringify(data))
    alert(JSON.stringify(data))
    var html = '<option value="">중분류</option>';
    var len = data.length;
    for ( var i = 0; i < len; i++) {
        html += '<option value="' + data[i].id + '">'+ data[i].categoryName + '</option>';
    }
        html += '</option>';
        $('#secondCategory').html(html);
    });
}