$(function () {
    $("div.tpl-table-black-operation a.tpl-table-black-operation-del").click(function (e) {
        $('#delModal').modal({
            relatedTarget: this,
            onConfirm: function (options) {
                var $link = $(this.relatedTarget);
                console.log("删除的Url为:"+$link.attr("link"))
                var keyWords = $("input[name='keyWords']").val();
                var param = { "keyWords":keyWords}
                var link = $link.attr("link");
                pjaxMethodClieck(link,param,"POST");
            },
            onCancel: function () {
                //alert('算求，不弄了');
            }
        });
    });
})
