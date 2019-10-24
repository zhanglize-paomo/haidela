function fnLogin() {
    var oUname = document.getElementById("uname")
    var oUpass = document.getElementById("upass")
    var oError = document.getElementById("error_box")
    var isError = true;
    if (oUname.value.length > 20 || oUname.value.length < 6) {
        oError.innerHTML = "用户名请输入6-20位字符";
        isError = false;
        return;
    }else if((oUname.value.charCodeAt(0)>=48) && (oUname.value.charCodeAt(0)<=57)){
        oError.innerHTML = "首字符必须为字母";
        return;
    }else for(var i=0;i<oUname.value.charCodeAt(i);i++){
        if((oUname.value.charCodeAt(i)<48)||(oUname.value.charCodeAt(i)>57) && (oUname.value.charCodeAt(i)<97)||(oUname.value.charCodeAt(i)>122)){
            oError.innerHTML = "必须为字母跟数字组成";
            return;
        }
    }

    if (oUpass.value.length > 20 || oUpass.value.length < 6) {
        oError.innerHTML = "密码请输入6-20位字符"
        isError = false;
        return;
    }
    window.alert("登录成功")
}


/**
 *
 * @authors Your Name (you@example.org)
 * @date    2015-12-25 17:25:23
 * @version $Id$
 */

$(function(){
    // input表单
    $("input").focus(function(event) {
        var moren = $(this).attr('inp-val');
        if ($(this).val()== moren) {
            $(this).css('color','#333');
            $(this).val('');
        };
    }).blur(function(event) {
        if ($(this).val()=='') {
            var moren = $(this).attr('inp-val');
            $(this).css('color','#b7b7b7');
            $(this).val(moren);
        }else{
            $(this).css('color','#333')
        };
    });

    $(function() {
        $(".order_top1 ul li").hover(function() {
// 				$(this).addClass('current').siblings().removeClass('current');
// 				$(".order_top1 ul li img.img_2").hide();
// 				$(".order_top1 ul li img.img_1").show();
// 				$(this).find('.img_2').show();
// 				$(this).find('.img_1').hide();
            $(".order_top1 ul li .order_topcontmain").hide();
            $(this).find('.order_topcontmain').show();
        }, function() {
            $(".order_top1 ul li .order_topcontmain").hide();
        });
        $(".order_top1 ul li a").hover(function() {
// 		 		$(this).addClass('yellow').siblings().removeClass('yellow');

        });
    });


    $(".main_tabel ul li a.mark").click(function () {
        $("#bg").css({
            display: "block", height: $(document).height()
        });
        $(".marker").show();
    });

});
