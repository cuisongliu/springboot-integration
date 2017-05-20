$(function () {
    $("div.tpl-table-black-operation a.tpl-table-black-operation-del").click(function (e) {
        //var num = $(e.target).attr("num");
        $('#delModal').modal({
            relatedTarget: this,
            onConfirm: function (options) {
                var $link = $(this.relatedTarget);
                //var link = getLink(this.relatedTarget);
                console.log("删除的Url为:"+$link.attr("link"))
                console.log("验证数量为:"+$link.attr("num"))
                if($link.attr("num") > 0){
                    AMUI.dialog.alert({
                        title: '提示',
                        content: '该用户已经被使用,请解除关联后重试.',
                        onConfirm: function() {
                            console.log('删除的对话框关闭.');
                        }
                    });
                }else {
                    var keyWords = $("input[name='keyWords']").val();
                    var param = { "keyWords":keyWords}
                    var link = $link.attr("link");
                    pjaxMethodClieck(link,param,"POST");
                }

            },
            onCancel: function () {
                //alert('算求，不弄了');
            }
        });
    });
})
