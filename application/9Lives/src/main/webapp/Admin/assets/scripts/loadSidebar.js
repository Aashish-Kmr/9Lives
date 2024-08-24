
// loadSidebar.js
document.addEventListener("DOMContentLoaded", function() {
    fetch('sidebar.html')
      .then(response => response.text())
      .then(data => {
        document.getElementById('sidebar-container').innerHTML = data;
        // Optionally, you can execute or reinitialize any JavaScript related to the sidebar here
        const btnToggler = window.document.querySelector(".navbar-toggler"); 
        const navbar = window.document.querySelector(".navbar");
        const element=document.getElementsByClassName('dashboard')[0];

        //events
        btnToggler.addEventListener('click', () => {
            navbar.classList.toggle('active'); 
            element.classList.toggle('actives');
            
        });
        
       
      });
  });
 