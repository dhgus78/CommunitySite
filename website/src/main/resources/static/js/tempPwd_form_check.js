/**
 * 
 */
//tempPwd_form_check 함수로 유효성 검사
function tempPwd_form_check() {
  //변수에 담아주기
  var username = document.getElementById("email");
  var name = document.getElementById("name");
  
  if (username.value == "") {
    alert("이메일을 입력하세요.");
    username.focus();
    return false;
  };
  
  if (name.value == "") {
    alert("이름을 입력하세요.");
    name.focus();
    return false;
  };

  //입력 값 전송
  document.tempPwd_form.submit(); //유효성 검사의 포인트   
}