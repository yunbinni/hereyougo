function onChangeTest(category){
var parentId = category.value;

const data = {
   firstCategoryId:parentId
};

$.ajax({
     url: "/categories/child",
     data: data,
     type: "GET",
}).done(function (data){
     var html = '<option value="">중분류</option>';
     var len = data.length;
     for ( var i = 0; i < len; i++) {
         html += '<option value="' + data[i].id + '">'+ data[i].categoryName + '</option>';
     }
         $('#secondCategory').html(html);
     });
}