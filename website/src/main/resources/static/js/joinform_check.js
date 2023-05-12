/**
 * 
 */
//joinform_check 함수로 유효성 검사
function joinform_check() {
  //변수에 담아주기
  var email = document.getElementById("email");
  var name = document.getElementById("name");
  var pwd = document.getElementById("pwd");
  var confirm_pwd = document.getElementById("confirm_pwd");


  if (email.value == "") { //해당 입력값이 없을 경우 같은말: if(!email.value)
    alert("이메일을 입력하세요.");
    email.focus(); //focus(): 커서가 깜빡이는 현상, blur(): 커서가 사라지는 현상
    return false; //return: 반환하다 return false:  아무것도 반환하지 말아라 아래 코드부터 아무것도 진행하지 말것
  };
  
  if (name.value == "") {
    alert("이름을 입력하세요.");
    name.focus();
    return false;
  };

  if (pwd.value == "") {
    alert("비밀번호를 입력하세요.");
    pwd.focus();
    return false;
  };

  //비밀번호 영문자+숫자+특수조합(8~25자리 입력) 정규식
  var pwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;

  if (!pwdCheck.test(pwd.value)) {
    alert("비밀번호는 영문자+숫자+특수문자 조합으로 8~25자리 사용해야 합니다.");
    pwd.focus();
    return false;
  };

  if (confirm_pwd.value !== pwd.value) {
    alert("비밀번호가 일치하지 않습니다..");
    confirm_pwd.focus();
    return false;
  };


  //입력 값 전송
  document.join_form.submit(); //유효성 검사의 포인트   
}