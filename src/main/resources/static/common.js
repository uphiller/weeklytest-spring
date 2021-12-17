$.ajaxPrefilter(function( options, originalOptions, jqXHR ) {
    if(localStorage.getItem('token')) {
        jqXHR.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
    }
});

function checkLogin(){
    if(!localStorage.getItem('token')) {
        alert("로그인이 필요합니다.")
        return false;
    }
    return true;
}
