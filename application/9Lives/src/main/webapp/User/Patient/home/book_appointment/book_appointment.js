document.addEventListener('DOMContentLoaded', function() {
  const departmentSelect = document.getElementById('department');
  const doctorSelect = document.getElementById('doctor');
  const appointmentDateInput = document.getElementById('appointment-date');
  const timeSlotSelect = document.getElementById('timeslot');
  const bookAppointmentForm = document.getElementById('bookAppointmentForm');

  // Set minimum date to today
  const today = new Date().toISOString().split('T')[0];
  appointmentDateInput.setAttribute('min', today);

  // Handle form submission
  bookAppointmentForm.addEventListener('submit', function(event) {
      event.preventDefault();
      
      const selectedDate = appointmentDateInput.value;
      const selectedTimeSlot = timeSlotSelect.value;

      if (selectedDate && selectedTimeSlot) {
          // Create and display the confirmation overlay and popup
          const overlay = document.createElement('div');
          overlay.className = 'overlay';
          
          const confirmationPopup = document.createElement('div');
          confirmationPopup.className = 'confirmation-popup';
          confirmationPopup.innerHTML = `
              <div class="popup-content">
                  <h2>✨ Your booking is confirmed! ✨</h2>
                  <p>See you on <strong>${selectedDate}</strong> at <strong>${selectedTimeSlot}</strong>.</p>
                  <button id="closePopup">OK</button>
              </div>
          `;
          
          document.body.appendChild(overlay);
          document.body.appendChild(confirmationPopup);

          // Close popup and remove overlay on button click
          document.getElementById('closePopup').addEventListener('click', function() {
              document.body.removeChild(confirmationPopup);
              document.body.removeChild(overlay);
          });
      } else {
          alert('Please select a department, doctor, date, and time slot.');
      }
  });
});
