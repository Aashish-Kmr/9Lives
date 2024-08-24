document.addEventListener('DOMContentLoaded', () => {
    const doctors = [
        { id: 1, name: 'Dr. Brown', specialization: 'Cardiology', contact: '123-456-7890' },
        { id: 2, name: 'Dr. White', specialization: 'Neurology', contact: '987-654-3210' },
        { id: 3, name: 'Dr. Black', specialization: 'Pediatrics', contact: '456-789-0123' }
    ];

    const doctorTable = document.getElementById('doctor-table');

    doctors.forEach(doctor => {
        const tr = document.createElement('tr');

        tr.innerHTML = `
            <td>${doctor.id}</td>
            <td>${doctor.name}</td>
            <td>${doctor.specialization}</td>
            <td>${doctor.contact}</td>
        `;

        doctorTable.appendChild(tr);
    });
});
