// var listOfAppointment=[
//     {
//           name:"Jonh doe",
//           time:"04:09 PM",
//           type:"Regular checkup"
//     },
//     {

//     },
//     {

//     },
//     {

//     }
// ]

function displaylist(tagname){


    // Get all buttons with class="btn" inside the container
    
  
    var btns=document.getElementsByClassName('tag');
    console.log('btns');
    // Loop through the buttons and add the active class to the current/clicked button
    for (var i = 0; i < btns.length; i++) {
       var l=btns[i].getAttribute('value')
       if(l==tagname)
       {
          btns[i].classList.remove("hidden");
          btns[i].classList.add("active:border-black");
       }
       else{
        btns[i].classList.add('hidden');
       }
    }

}