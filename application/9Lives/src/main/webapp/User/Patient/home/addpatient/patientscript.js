document.getElementById('addPatientForm').addEventListener('submit', function(event) {
    event.preventDefault();
  
    // Validate the form
    const patientName = document.getElementById('patientName').value.trim();
    const age = document.getElementById('age').value;
    const gender = document.getElementById('gender').value;
    const phoneNumber = document.getElementById('phoneNumber').value.trim();
    const address = document.getElementById('address').value.trim();
    const medicalHistory = document.getElementById('medicalHistory').value.trim();
  
    if (!patientName || !age || !gender || !phoneNumber || !address || !medicalHistory) {
      alert("Please fill out all fields.");
      return;
    }
  
    if (!/^\d{10}$/.test(phoneNumber)) {
      alert("Please enter a valid 10-digit phone number.");
      return;
    }
  
    // If all validation passes, you can proceed with form submission or AJAX request
    alert("Patient added successfully!");
  
    // Clear the form after submission
    document.getElementById('addPatientForm').reset();
  });
  