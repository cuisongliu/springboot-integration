$(function(){
    $('select').chosen();
    $('#btnSubmit').click(function (e) {
        var $btn = $(this)
        var link = $(e.target).attr('link');
        $.AMUI.progress.start();
        $btn.button('loading');
        var temp = $('#paramForm').validator('isFormValid');
        if(temp){
            var paramCode = $("input[name='paramCode']");
            var param={"paramCode":paramCode.val()}
            var validation =$.ajax({url:paramCode.attr('link'),data:param,type:"GET",async:false}).responseText;
            console.log(validation)
            if(validation === 'true'){
                var jsonData = $('#paramForm').serialize();
                pjaxMethodClieck(link,jsonData,"POST");
            }else {
                AMUI.dialog.alert({
                    title: '提示',
                    content: '参数已经存在,请修改后重试.',
                    onConfirm: function() {
                        console.log('验证参数的对话框关闭.');
                    }
                });
            }

        }
        $btn.button('reset');
        $.AMUI.progress.done();
    });
    $('#paramForm').validator({});
})
