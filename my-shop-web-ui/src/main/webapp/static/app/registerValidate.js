$(function(){
    //对用户名进行相应的验证.
    $("#username").mouseleave(function(){
        userNameValidate();
    });
    //对密码进行验证
    $("#password").mouseleave(function(){
        passwordValidate();
    });
    //对重复密码进行验证
    $("#rePassword").mouseleave(function(){
        repasswordValidate();
    });
    //对手机号进行相应的验证
    $("#phone").mouseleave(function(){
        telValidate();
    });
    //对邮箱进行相应的验证
    $("#email").mouseleave(function(){
        emailValidate();
    });
});
//用户名的相关验证
function userNameValidate(){
    //先判断是否为空
    let value=$("#username").val();
    if(value.length==0)
    {
        $("#userNameTitle").text('通行证用户名不能为空');
        $("#userNameTitle").removeClass('success').addClass('danger');
        return false;
    }
    //定义正则表达式
    let pattern=/^[A-Za-z][A-Za-z0-9]{3,11}$/;
    if(!pattern.test(value))
    {
        $("#userNameTitle").text('通行证用户名格式错误，请重新输入');
        $("#userNameTitle").removeClass('success').addClass('danger');
        return false;
    }else{
        $("#userNameTitle").text('通行证用户名输入正确');
        $("#userNameTitle").removeClass('danger').addClass('success');
        return true;
    }
}
//密码的相关验证
function passwordValidate(){
    //先判断是否为空
    let value=$("#password").val();
    if(value.length==0)
    {
        $("#passwordTitle").text('密码不能为空');
        $("#passwordTitle").removeClass('success').addClass('danger');
        return false;
    }
    //定义正则表达式
    let pattern=/^[A-Za-z0-9]{6,16}$/;
    if(!pattern.test(value))
    {
        $("#passwordTitle").text('密码不符合格式，请重新输入');
        $("#passwordTitle").removeClass('success').addClass('danger');
        return false;
    }else{
        $("#passwordTitle").text('密码输入正确');
        $("#passwordTitle").removeClass('danger').addClass('success');
        return true;
    }
}
//重复密码的相关验证
function repasswordValidate(){
    //先判断是否为空
    let value=$("#rePassword").val();
    if(value.length==0)
    {
        $("#repasswordTitle").text('重复密码不能为空');
        $("#repasswordTitle").removeClass('success').addClass('danger');
        return false;
    }
    //判断值是否相同
    if(!(value==$("#password").val()))
    {
        $("#repasswordTitle").text('两次输入的密码不一致，请重新输入');
        $("#repasswordTitle").removeClass('success').addClass('danger');
        return false;
    }else{
        $("#repasswordTitle").text('两次密码一致');
        $("#repasswordTitle").removeClass('danger').addClass('success');
        return true;
    }
}
//手机号的相关验证
function telValidate(){
    //先判断是否为空
    let value=$("#phone").val();
    if(value.length==0)
    {
        $("#telTitle").text('手机号不能为空');
        $("#telTitle").removeClass('success').addClass('danger');
        return false;
    }
    //判断值是否相同
    let pattern=/^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
    if(!pattern.test(value))
    {
        $("#telTitle").text('手机号码格式错误，请重新输入');
        $("#telTitle").removeClass('success').addClass('danger');
        return false;
    }else{
        $("#telTitle").text('手机号输入正确');
        $("#telTitle").removeClass('danger').addClass('success');
        return true;
    }
}
//邮箱的相关验证
function emailValidate(){
    //先判断是否为空
    let value=$("#email").val();
    if(value.length==0)
    {
        $("#emailTitle").text('邮箱不能为空');
        $("#emailTitle").removeClass('success').addClass('danger');
        return false;
    }
    //判断值是否相同
    let pattern=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if(!pattern.test(value))
    {
        $("#emailTitle").text('邮箱格式错误，请重新输入');
        $("#emailTitle").removeClass('success').addClass('danger');
        return false;
    }else{
        $("#emailTitle").text('邮箱输入正确');
        $("#emailTitle").removeClass('danger').addClass('success');
        return true;
    }
}
function checkbox() {
    return $('#checkbox').is(':checked');
}
function formValidate(){
    return userNameValidate()&&passwordValidate()&&repasswordValidate()
        &&telValidate()&&emailValidate()&&checkbox();
}