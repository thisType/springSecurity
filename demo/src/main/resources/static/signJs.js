

let  signUpForm = document.querySelector("#signUp");
let signInForm = document.querySelector("#signIn");
let  firstMsg = document.querySelector("b.messageOne");
let secondMsg = document.querySelector("b.messageSecond");







async  function signUpUser (){

let urlParameters = new URLSearchParams();
urlParameters.append("firstName", signUpForm.elements.firstName.value);
urlParameters.append("secondName", signUpForm.elements.secondName.value);
urlParameters.append("userName",signUpForm.elements.userName.value);
urlParameters.append("Email", signUpForm.elements.Email.value);
urlParameters.append("Password",signUpForm.elements.Password.value);

let response =  await fetch("/createUser",{
    method:"POST",credentials:"same-origin",
    body:urlParameters

});

if(response.ok){
       firstMsg.textContent = "Account created successfull. We are taking you to your activity.";
      
       setTimeout(()=>{  window.location.href = "Photos.html"  ;  });


}     else  {
     firstMsg.textContent = response.statusText;
     firstMsg.style.color = "red";


}

}


async function signInUser(){

    let urlParameters = new URLSearchParams();
   urlParameters.append("username", signInForm.elements.Email);
   urlParameters.append("password",signInForm.elements.Password);
     
   let response =  await fetch("/loginUser",{
    method:"POST",credentials:"same-origin",
    body:urlParameters

});


    if(response.ok){

       secondMsg.textContent = "Login Successfull. We are redirecting you to resume your activity";
       setTimeout(()=>{  window.location.href = "Photos.html"  ;  });


    } else {

      secondMsg.textContent = "Login failed! Try again";
      secondMsg.style.color = "red";

    }


}




signUpForm.addEventListener("submit",(e)=>{
 e.preventDefault();

if(e.target.elements.Password == e.target.elements.PasswordConfirm){

signUpUser();

} else {

    firstMsg.textContent = "Password Mismatch!";
}



});

signInForm.addEventListener("submit", (e)=>{

signInUser();


});