$(function(){
    $('select').chosen();
    $('#btnSubmit').click(function (e) {
        var $btn = $(this)
        var link = $(e.target).attr('link');
        $.AMUI.progress.start();
        $btn.button('loading');
        var jsonData = [];
        pjaxMethodClieck(link,jsonData,"GET");
        $btn.button('reset');
        $.AMUI.progress.done();
    });
})
