document.addEventListener('DOMContentLoaded', () => {
    const patients = [
        { id: 1, name: 'John Doe', age: 45, gender: 'Male', contact: '123-456-7890' },
        { id: 2, name: 'Jane Smith', age: 34, gender: 'Female', contact: '987-654-3210' },
        { id: 3, name: 'Alice Johnson', age: 29, gender: 'Female', contact: '456-789-0123' }
    ];

    const patientTable = document.getElementById('patient-table');

    patients.forEach(patient => {
        const tr = document.createElement('tr');

        tr.innerHTML = `
            <td>${patient.id}</td>
            <td>${patient.name}</td>
            <td>${patient.age}</td>
            <td>${patient.gender}</td>
            <td>${patient.contact}</td>
        `;

        patientTable.appendChild(tr);
    });
    
});