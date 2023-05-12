/**
 * 
 */
//passwordModify_check 함수로 유효성 검사
function passwordModify_check() {
  //변수에 담아주기
  var pwd = document.getElementById("pwd");
  var pwdConfirm = document.getElementById("pwdConfirm");
  
  if (pwd.value == "") {
    alert("비밀번호를 입력하세요.");
    pwd.focus();
    return false;
  };
  
  if (pwdConfirm.value == "") {
    alert("변경할 비밀번호를 재입력 해주세요.");
    pwdConfirm.focus();
    return false;
  };
  
  //비밀번호 영문자+숫자+특수조합(8~25자리 입력) 정규식
  var pwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;

  if (!pwdCheck.test(pwd.value)) {
    alert("비밀번호는 영문자+숫자+특수문자 조합으로 8~25자리 사용해야 합니다.");
    pwd.focus();
    return false;
  };

  if (pwdConfirm.value !== pwd.value) {
    alert("비밀번호가 일치하지 않습니다..");
    pwdConfirm.focus();
    return false;
  };

  //입력 값 전송
  document.passwordModify_form.submit(); //유효성 검사의 포인트   
}