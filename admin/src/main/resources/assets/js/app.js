$(function() {
    // 读取body data-type 判断是哪个页面然后执行相应页面方法，方法在下面。
    var dataType = $('body').attr('data-type');
    console.log(dataType);
    autoLeftNav();
    $(window).resize(function() {
        autoLeftNav();
        console.log($(window).width())
    });
    $('.sidebar-nav-sub-title').unbind();
    $('.sidebar-nav-sub-title').bind('click', function() {
        $(this).siblings('.sidebar-nav-sub').slideToggle(80)
            .end()
            .find('.sidebar-nav-sub-ico').toggleClass('sidebar-nav-sub-ico-rotate');
    })
})


// 侧边菜单开关
function autoLeftNav() {
    $('.tpl-header-switch-button').on('click', function() {
        if ($('.left-sidebar').is('.active')) {
            if ($(window).width() > 1024) {
                $('.tpl-content-wrapper').removeClass('active');
            }
            $('.left-sidebar').removeClass('active');
        } else {

            $('.left-sidebar').addClass('active');
            if ($(window).width() > 1024) {
                $('.tpl-content-wrapper').addClass('active');
            }
        }
    })

    if ($(window).width() < 1024) {
        $('.left-sidebar').addClass('active');
    } else {
        $('.left-sidebar').removeClass('active');
    }
}


// 侧边菜单
function pjaxClick(url){
    $.AMUI.progress.start();
    htmlobj=$.ajax({url:url,async:false});
    $.AMUI.progress.done();
    $("#content").html(htmlobj.responseText);
}
function pjaxMethodClieck(url,param,method){
    $.AMUI.progress.start();
    htmlobj=$.ajax({url:url,data:param,type:method,async:false});
    $.AMUI.progress.done();
    $("#content").html(htmlobj.responseText);
}

function  getLink(e){
    var link = $(e.target).attr('link');
    if(link === undefined){
        link = $(e.target).parent().attr('link');
    }
    return link;
}

function bindMenuSub(){
    $('.sidebar-nav-sub-title').unbind();
    $('.sidebar-nav-sub-title').bind('click', function() {
        $(this).siblings('.sidebar-nav-sub').slideToggle(80)
            .end()
            .find('.sidebar-nav-sub-ico').toggleClass('sidebar-nav-sub-ico-rotate');
    })
}