$(function(){
    $('select').chosen();
    $('#btnSubmit').click(function (e) {
        var $btn = $(this)
        var link = $(e.target).attr('link');
        $.AMUI.progress.start();
        $btn.button('loading');
        var temp = $('#serialTypeForm').validator('isFormValid');
        if(temp){
            var serialTypeCode = $("input[name='serialTypeCode']");
            var serialType={"serialTypeCode":serialTypeCode.val()}
            var validation =$.ajax({url:serialTypeCode.attr('link'),data:serialType,type:"GET",async:false}).responseText;
            console.log(validation)
            if(validation === 'true'){
                var jsonData = $('#serialTypeForm').serialize();
                pjaxMethodClieck(link,jsonData,"POST");
            }else {
                AMUI.dialog.alert({
                    title: '提示',
                    content: '序列类型编码已经存在,请修改后重试.',
                    onConfirm: function() {
                        console.log('验证序列类型编码的对话框关闭.');
                    }
                });
            }

        }
        $btn.button('reset');
        $.AMUI.progress.done();
    });
    $('#serialTypeForm').validator({});
})
