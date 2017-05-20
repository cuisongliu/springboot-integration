$(function(){
    if ($.AMUI && $.AMUI.validator) {
        // 增加多个正则
        $.AMUI.validator.patterns = $.extend($.AMUI.validator.patterns, {
            loginName: /^[a-zA-Z\d]\w{3,11}[a-zA-Z\d]$/
        });
    }
    $('select').chosen();
    $('#btnSubmit').click(function (e) {
        var $btn = $(this)
        var link = $(e.target).attr('link');
        $.AMUI.progress.start();
        $btn.button('loading');
        var temp = $('#profileForm').validator('isFormValid');
        if(temp){
            var loginName = $("input[name='loginName']");
            var param={"loginName":loginName.val()}
            var validation =$.ajax({url:loginName.attr('link'),data:param,type:"GET",async:false}).responseText;
            console.log(validation)
            if(validation === 'true'){
                var jsonData = $('#profileForm').serialize();
                pjaxMethodClieck(link,jsonData,"POST");
            }else {
                AMUI.dialog.alert({
                    title: '提示',
                    content: '用户名已经存在,请修改后重试.',
                    onConfirm: function() {
                        console.log('验证用户名的对话框关闭.');
                    }
                });
            }

        }
        $btn.button('reset');
        $.AMUI.progress.done();
    });
    $('#profileForm').validator({});

    $('input[name="userLoginName"]').change(function(e){
        $('#smLoginName').html(e.target.value);
    });
})
