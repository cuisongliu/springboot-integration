$(function(){
    $('select').chosen();
    $('#btnSubmit').click(function (e) {
        var $btn = $(this)
        var link = $(e.target).attr('link');
        $.AMUI.progress.start();
        $btn.button('loading');
        var temp = $('#storeForm').validator('isFormValid');
        if(temp){
            var jsonData = $('#storeForm').serialize();
            pjaxMethodClieck(link,jsonData,"POST");
        }
        $btn.button('reset');
        $.AMUI.progress.done();
    });
    $('#storeForm').validator({});
})
