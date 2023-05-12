/**
 * 
 */
//joinform_check 함수로 유효성 검사
function loginform_check() {
  //변수에 담아주기
  var email = document.getElementById("email");
  var pwd = document.getElementById("pwd");

  if (email.value == "") { //해당 입력값이 없을 경우 같은말: if(!email.value)
    alert("이메일을 입력하세요.");
    email.focus(); //focus(): 커서가 깜빡이는 현상, blur(): 커서가 사라지는 현상
    return false; //return: 반환하다 return false:  아무것도 반환하지 말아라 아래 코드부터 아무것도 진행하지 말것
  };

  if (pwd.value == "") {
    alert("비밀번호를 입력하세요.");
    pwd.focus();
    return false;
  };

  //입력 값 전송
  document.login_form.submit(); //유효성 검사의 포인트   
}