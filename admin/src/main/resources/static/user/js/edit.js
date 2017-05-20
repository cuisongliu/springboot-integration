$(function(){
    $('select').chosen();
    $('#btnSubmit').click(function (e) {
        var $btn = $(this)
        var link = $(e.target).attr('link');
        $.AMUI.progress.start();
        $btn.button('loading');
        var temp = $('#profileForm').validator('isFormValid');
        if(temp){
            var jsonData = $('#profileForm').serialize();
            pjaxMethodClieck(link,jsonData,"POST");
        }
        $btn.button('reset');
        $.AMUI.progress.done();
        var profile = $('#profile').val();
        console.log(temp && profile ==true);
        if(temp && profile ==true)
            window.location.href='/';
    });
    $('#profileForm').validator({});
})
