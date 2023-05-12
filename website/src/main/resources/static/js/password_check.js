/**
 * 
 */
//password_check 함수로 유효성 검사
function password_check() {
  //변수에 담아주기
  var pwd = document.getElementById("pwd");
  if (pwd.value == "") {
    alert("비밀번호를 입력하세요.");
    pwd.focus();
    return false;
  };

  //입력 값 전송
  document.password_form.submit(); //유효성 검사의 포인트   
}