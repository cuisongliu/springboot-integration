$(function(){
    $('select').chosen();
    $('#btnSubmit').click(function (e) {
        var $btn = $(this)
        var link = $(e.target).attr('link');
        $.AMUI.progress.start();
        $btn.button('loading');
        var temp = $('#phoneForm').validator('isFormValid');
        if(temp){
            var phoneSerial = $("select[name='phoneSerial']");
            var param={"phoneSerial":phoneSerial.val()}
            console.log(param)
            console.log(phoneSerial.attr('link'))
            var validation =$.ajax({url:phoneSerial.attr('link'),data:param,type:"GET",async:false}).responseText;
            console.log(validation)
            if(validation === 'true'){
                var jsonData = $('#phoneForm').serialize();
                pjaxMethodClieck(link,jsonData,"POST");
            }else {
                AMUI.dialog.alert({
                    title: '提示',
                    content: '手持机序列已经存在,请修改后重试.',
                    onConfirm: function() {
                        console.log('验证参数的对话框关闭.');
                    }
                });
            }

        }
        $btn.button('reset');
        $.AMUI.progress.done();
    });
    $('#phoneForm').validator({});
})
