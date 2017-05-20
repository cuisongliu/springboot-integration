/**
 * Created by jerry on 2016/11/23.
 */
$(function(){
    // pjax
    var contentLink  = $("#content").attr('link');
    pjaxMethodClieck(contentLink,{},"GET");
    //左侧菜单
    $("li a.pjax").click(function(  e){
        var link = getLink(e);
        menuAjax(e,link);
    });
    //profile
    $("a.indexAjax").click(function(e){
        var link = getLink(e);
        pjaxClick(link);
    });

    $('#exitHref').click(function(){
        $('#exit-system').modal({
            relatedTarget: this,
            onConfirm: function(options) {
                var $link = $(this.relatedTarget).attr('link');
                window.location.href=$link;
            },
            onCancel: function() {
            }
        });
    });
})

function menuAjax(e,link){
    $('.sidebar-nav-sub-title').unbind();
    $.AMUI.progress.start();
    var dom = $(e.target);
    htmlobj=$.ajax({url: link,async:false});
    $.AMUI.progress.done();
    $("a.active").removeClass("active");
    dom.addClass("active");
    $("#content").html(htmlobj.responseText);
    $('.sidebar-nav-sub-title').unbind();
    $('.sidebar-nav-sub-title').bind('click', function() {
        $(this).siblings('.sidebar-nav-sub').slideToggle(80)
            .end()
            .find('.sidebar-nav-sub-ico').toggleClass('sidebar-nav-sub-ico-rotate');
    })
}
