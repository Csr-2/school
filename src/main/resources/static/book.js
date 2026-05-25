// 查询方法
function showSuggest() {
    var keyword = document.getElementById('bookName').value;  // 获取查询条件
    var suggestBox=document.getElementById('suggestBox');
    if (keyword.length===0){
        suggestBox.style.display='none';
        return;
    }
    axios.get('/book/list',{
    params:{bookName:keyword}
    }).then(function (req) {
        var books=req.data.map(function (item) {
            return item.title
        });
        console.log(req.data)
        var html='';
        for (var i = 0; i < books.length; i++) {
            // 高亮匹配的文字
            var highlighted = books[i].replace(new RegExp(keyword, 'g'), '<span style="color:red;">' + keyword + '</span>');
            html += '<div onclick="selectBook(\'' + books[i] + '\')" style="padding:8px; cursor:pointer; border-bottom:1px solid #eee;">' + highlighted + '</div>';

        }
        suggestBox.innerHTML = html;
        suggestBox.style.display = 'block';
    })
        .catch(function(error) {
            console.error('查询失败:', error);
        });
}
// 页面加载完成后自动查询
function selectBook(bookName) {
    document.getElementById('bookName').value = bookName;
    document.getElementById('suggestBox').style.display = 'none';
    // 可选：选中后自动搜索
    // searchBook();
}

// 点击其他地方关闭下拉框
document.addEventListener('click', function(e) {
    if (e.target.id !== 'bookName') {
        document.getElementById('suggestBox').style.display = 'none';
    }
});