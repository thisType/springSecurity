

let albums =  document.querySelector("p.albums");
let photos =  document.querySelector("section.photos");
let main = document.querySelector("main");
let photo = document.querySelector(".photo img");
let photoSection = document.querySelector("section.photo");
let  firstAlbumName;
let  imgData = {
       imgName:'',
       imgUrl:''

}




function displayImg(img){

     let cloneImg = img.cloneNode(true);

     photoSection.replaceChild(cloneImg,photo);

     photo = document.querySelector(".photo img");

      // setting href parameters


}




function reCreatePhotosSection(){

    let newSection = document.createElement("section");
    newSection.className ='photos';


    main.replaceChild(newSection,photos);
    photos = document.querySelector("section.photos");





}

function populateAlbums(albums){
     firstAlbumName = albums[0].album;


    if(albums.length()=!0){

for(let Album of albums){
   let img  = document.createElement("img");
   img.title = Album.album;
   img.src = "/picture/"+ Album.id;
   img.dataset['AlbName'] = Album.album;
   img.addEventListener("click",(e)=>{
    reCreatePhotosSection();

    getPhotos(e.target.dataset["Albname"]);


   });
   albums.appendChild(img);



}
    }
}


function populatePhotos(photos){

    for(let photo of photos){

  let img = document.querySelector("img");
  img.title = photo.imgName;
  img.src =   img.src = "/user/picture/"+ photo.id;

  img.addEventListener("click",(e)=>{
                 displayImg(e.target);

  });





    }

}




async function getAlbums(){

let response = fetch("/user/albums",{
 method: "GET"
 ,credentials:"same-origin",

});


 if(response.ok){
    let data = (await response).json();
    
    populateAlbums(data);
   }
}

async function getPhotos(albumName){

    // few things 
    let response = fetch("/user/albumPhotos/"+albumName,{
        method: "GET"
        ,credentials:"same-origin",
       
       });

       if(response.ok){
  let data  = await  response.json();

  populatePhotos(data);

}
}



function start(firstAlbum){

getAlbums();
getPhotos(firstAlbum);

}

start(firstAlbumName);


