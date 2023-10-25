
let submitInput = document.querySelector("input[type='submit']");
let boldmessage = document.querySelector("form >b");
let filesInput =  document.querySelector("input[type='file']");
let  imagesHolder = document.querySelector("main > p");
let albumInput = document.querySelector("input[type='text']");
let filesMap = new Map();

filesInput.addEventListener("change",()=>{


 for(let file of Array.from(filesInput.files)){


    if(!filesMap.has(file.name)){

    let fileReader = new FileReader();
    fileReader.addEventListener("load",(event)=>{

     let img = document.createElement("img");
     img.addEventListener("dblclick",(e)=>{
        filesMap.delete(e.target.dataset["ref"]);
        imagesHolder.removeChild(e.target);
        

     });

    

 


     img.title = file.name;
     img.src=  event.target.result;
     img.dataset["ref"] = file.name;
    imagesHolder.appendChild(img);
       filesMap.set(file.name, file);

    });

    fileReader.readAsDataURL(file);

} else {
    continue;
}
}
 




});
function message(text, b=true){

if(b){

boldmessage.style.color ="green";
boldmessage.textContent =text;

} else {
    boldmessage.style.color ="red";
    boldmessage.textContent =text;

}


}




async  function uploadImages(){

let formData = new FormData();
formData.append("albumName",albumInput.value);
filesMap.forEach((value, key) => {
    
    formData.append("photos[]", value);

  });


try{
     
    let response = await fetch("/user/upload",{ method: 'POST',
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    body: formData
});


  if(response.ok){

    message("Uploaded successsfull");



  } else {


    message("failed to upload. Try again",false);
  }


}catch(e){

 message("error uploading.Please try again", false);




}
}


submitInput.addEventListener("click",(e)=>{

e.preventDefault();
uploadImages();

});
