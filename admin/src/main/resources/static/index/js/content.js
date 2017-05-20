/**
 * Created by jerry on 2016/11/25.
 */
$(function(){

    $('button.am-icon-search.search-keyWords').click(function(e){
        var keyWords = $("input[name='keyWords']").val();
        var param = { "keyWords":keyWords}
        var link = $(e.target).attr('link');
        pjaxMethodClieck(link,param,"GET");
    });

    $("a.reload").click(function(e){
        var link = getLink(e);
        pjaxClick(link);
    });

    $("button.reload").click(function(e){
        var link = getLink(e);
        pjaxClick(link);
    });
})
