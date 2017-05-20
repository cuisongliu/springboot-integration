$(function(){
    $('select').chosen();
    $('input[name="createDate"]').datetimepicker({
        language:  'zh-CN',
        format: 'yyyy-mm-dd hh:ii:ss',
        autoclose: true,
        todayBtn: true,
        minuteStep: 1
        //pickerPosition: 'bottom-left'
    });

    $('#btnSubmit').click(function (e) {
        var $btn = $(this)
        var link = $(e.target).attr('link');
        $.AMUI.progress.start();
        $btn.button('loading');
        var temp = $('#recordForm').validator('isFormValid');
        if(temp){
            var jsonData = $('#recordForm').serializeObject();
            console.log(jsonData);
            pjaxMethodClieck(link,jsonData,"POST");
        }
        $btn.button('reset');
        $.AMUI.progress.done();
    });
    $('#recordForm').validator({});
})
