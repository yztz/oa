/**
 */
//写入localStorage
function setLocalStorage(name, value)
{
    window.localStorage.setItem(name, value);
}

//读取localStorage
function getLocalStorage(name)
{
    return window.localStorage.getItem(name);
}

function deleteLocalstorage(name) {
    window.localStorage.removeItem(name);
}

function  getQueryString(name) {
    var  reg =  new  RegExp( "(^|&)"  + name +  "=([^&]*)(&|$)" ,  "i" );
    var  r = window.location.search.substr(1).match(reg);
    if  ( r !=  null  ){
        // return  unescape(r[2]);
        return  decodeURI(r[2]);
    } else {
        return  null ;
    }
}
