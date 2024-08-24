const sidebar = document.querySelector(".sidebar");
const sidebarClose = document.querySelector("#sidebar-close");
const menu = document.querySelector(".menu-content");
const menuItems = document.querySelectorAll(".submenu-item");
const subMenuTitles = document.querySelectorAll(".submenu .menu-title");

sidebarClose.addEventListener("click", () => sidebar.classList.toggle("close"));

menuItems.forEach((item, index) => {
  item.addEventListener("click", () => {
    menu.classList.add("submenu-active");
    item.classList.add("show-submenu");
    menuItems.forEach((item2, index2) => {
      if (index !== index2) {
        item2.classList.remove("show-submenu");
      }
    });
  });
});

subMenuTitles.forEach((title) => {
  title.addEventListener("click", () => {
    menu.classList.remove("submenu-active");
  });
});

document.addEventListener('DOMContentLoaded', () => {
  const patients = ['John Doe', 'Jane Smith', 'Alice Johnson'];
  const doctors = ['Dr. Brown', 'Dr. White', 'Dr. Black'];
  const appointments = ['John Doe - Dr. Brown - 10:00 AM', 'Jane Smith - Dr. White - 11:00 AM'];

  const patientsList = document.getElementById('patients-list');
  const doctorsList = document.getElementById('doctors-list');
  const appointmentsList = document.getElementById('appointments-list');

  patients.forEach(patient => {
      const li = document.createElement('li');
      li.textContent = patient;
      patientsList.appendChild(li);
  });

  doctors.forEach(doctor => {
      const li = document.createElement('li');
      li.textContent = doctor;
      doctorsList.appendChild(li);
  });

  appointments.forEach(appointment => {
      const li = document.createElement('li');
      li.textContent = appointment;
      appointmentsList.appendChild(li);
  });
});


console.log(menuItems, subMenuTitles);
