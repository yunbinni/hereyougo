function isMoreSix(){
  password = document.getElementById("password");
  passwordMessage = document.getElementById("password-message");
  var btnStatus = true;

  var passwordLength = password.value.length;
  if(passwordLength >= 6) {
    passwordMessage.style.display = "none";
    document.getElementById('joinBtn').disabled = !btnStatus;
  } else {
    passwordMessage.style.display = "block";
    document.getElementById('joinBtn').disabled = btnStatus;
  }
}

function validateConfirmPassword(){
  var password = document.getElementById("password");
  var confirmPassword = document.getElementById("confirm-password");
  var confirmPasswordLength = confirmPassword.value.length;

  var confirmLengthMessage = document.getElementById("confirm-length");
  var notMatchMessage = document.getElementById("notMatch");
  var btnStatus = true;

  if(confirmPasswordLength >= 6) {
    confirmLengthMessage.style.display = "none";
  } else {
    confirmLengthMessage.style.display = "block";
  }

  if(password.value == confirmPassword.value){
    notMatchMessage.style.display = "none";
  }else{
    notMatchMessage.style.display = "block";
  }

   if(confirmPasswordLength >= 6 && password.value == confirmPassword.value) {
      document.getElementById('joinBtn').disabled = !btnStatus;
   }else{
      document.getElementById('joinBtn').disabled = btnStatus;
   }
}





