

let userDetails = document.querySelectorAll("p.userDetails");
let button = document.querySelector("button");



function displayUserInfo(data){

    userDetails[0].textContent = data.firstName;
    userDetails[1].textContent = data.lastName;
    userDetails[2].textContent = data.userName;
    userDetails[3].textContent = data.email;

}



async function  getuserInfo(){

let response = await fetch("/user/userDetails",{method:"POST",credentials:"same-origin"});

if(response.ok){
     
    let jsonData = await response.json();  



       displayUserInfo(jsonData);

}

}

async function logoutUser(){

let response = await fetch("/user/logoutUser",{method:"GET",credentials:"same-origin"});

if(response.ok){


}

}


getuserInfo();


button.addEventListener("click",(e)=>{
 logoutUser();


});






