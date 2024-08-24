document.addEventListener('DOMContentLoaded', () => {
    const loginBtn = document.querySelector("#login");
    const registerBtn = document.querySelector("#register");
    const loginForm = document.getElementById('login-form');
    const registerForm = document.getElementById('register-form');
    const forgotPasswordLink = document.getElementById('forgot-password');
    const forgotPasswordForm = document.getElementById('password-recovery-form');
    
    const emailInput = document.getElementById('sign-in-email');
    const passwordInput = document.getElementById('sign-in-password');
    const signInEmailError = document.getElementById('sign-in-email-error');
    const signInPasswordError = document.getElementById('sign-in-password-error');
    const signInButton = document.getElementById('sign-in-button');
    const loginMessage = document.getElementById('login-message');
 
    const sendOtpButton = document.getElementById('send-otp-button');
    const otpSection = document.getElementById('otp-box');
    const otpTimer = document.getElementById('otp-timer');
    const verifyOtpButton = document.getElementById('submit-password-button');
    const otpInput = document.getElementById('otp');
    const passwordResetSection = document.getElementById('new-password-box');
    const newPasswordInput = document.getElementById('new-password');
    const confirmPasswordInput = document.getElementById('reenter-password');
    const resetPasswordButton = document.getElementById('submit-password-button');
    
    // Variables for registration form validation
    const registerEmailInput = document.querySelector('.register-form .input-field[placeholder="Email"]');
    const registerNameInput = document.querySelector('.register-form .input-field[placeholder="Name"]');
    const registerDoBInput = document.querySelector('.register-form .input-field[type="date"]');
    const registerGenderInput = document.querySelector('.register-form select');
    const registerPasswordInput = document.querySelector('.register-form .input-field[placeholder="Password"]');
    const registerButton = document.getElementById('register-button');
    
    let otpTimerInterval;

    loginBtn.addEventListener('click', () => {
        loginBtn.style.backgroundColor = "#21264D";
        registerBtn.style.backgroundColor = "rgba(255, 255, 255, 0.2)";
        
        loginForm.style.left = "50%";
        registerForm.style.left = "-50%";
        
        loginForm.style.opacity = 1;
        registerForm.style.opacity = 0;

        document.querySelector(".col-1").style.borderRadius = "0 30% 20% 0";
    });

    registerBtn.addEventListener('click', () => {
        loginBtn.style.backgroundColor = "rgba(255, 255, 255, 0.2)";
        registerBtn.style.backgroundColor = "#21264D";
        
        loginForm.style.left = "150%";
        registerForm.style.left = "50%";
        
        loginForm.style.opacity = 0;
        registerForm.style.opacity = 1;

        document.querySelector(".col-1").style.borderRadius = "0 20% 30% 0";
    });

    // Set max date for DoB input
    const today = new Date().toISOString().split('T')[0];
    registerDoBInput.setAttribute('max', today);
    
    registerButton.addEventListener('click', (e) => {
        e.preventDefault();

        const email = registerEmailInput.value.trim();
        const name = registerNameInput.value.trim();
        const dob = registerDoBInput.value;
        const gender = registerGenderInput.value;
        const password = registerPasswordInput.value.trim();
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        
        let valid = true;
        let errorMessage = '';

        if (!name) {
            errorMessage += 'Name is required.\n';
            valid = false;
        }

        if (!email || !emailRegex.test(email)) {
            errorMessage += 'Please enter a valid email address.\n';
            valid = false;
        }

        if (!dob) {
            errorMessage += 'Date of Birth is required.\n';
            valid = false;
        }

        if (!gender) {
            errorMessage += 'Gender is required.\n';
            valid = false;
        }

        if (!password) {
            errorMessage += 'Password is required.\n';
            valid = false;
        }

        if (valid) {
            alert('Registration successful. Please log in.');
            window.location.href = 'login.html';
        } else {
            alert(errorMessage);
        }
    });

    forgotPasswordLink.addEventListener('click', (e) => {
        e.preventDefault();
        loginForm.classList.add('hidden');
        registerForm.classList.add('hidden');
        forgotPasswordForm.classList.remove('hidden');
    });

    loginBtn.addEventListener('click', () => {
        loginForm.classList.remove('hidden');
        registerForm.classList.add('hidden');
        forgotPasswordForm.classList.add('hidden');
    });

    registerBtn.addEventListener('click', () => {
        registerForm.classList.remove('hidden');
        loginForm.classList.add('hidden');
        forgotPasswordForm.classList.add('hidden');
    });

    emailInput.addEventListener('input', () => {
        const email = emailInput.value;
        if (!email.includes('@')) {
            sendOtpButton.disabled = true;
            signInEmailError.style.display = 'block';
        } else {
            sendOtpButton.disabled = false;
            signInEmailError.style.display = 'none';
        }
    });

    passwordInput.addEventListener('input', () => {
        if (passwordInput.value === '') {
            signInPasswordError.textContent = 'Password is required.';
            signInPasswordError.style.display = 'block';
        } else {
            signInPasswordError.style.display = 'none';
        }
    });

    document.getElementById('sign-in-button').addEventListener('click', (e) => {
        e.preventDefault();

        const email = emailInput.value.trim();
        const password = passwordInput.value.trim();
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        // Clear previous errors
        signInEmailError.style.display = 'none';
        signInPasswordError.style.display = 'none';

        let valid = true;

        if (!email) {
            signInEmailError.textContent = 'Email is required.';
            signInEmailError.style.display = 'block';
            valid = false;
        } else if (!emailRegex.test(email)) {
            signInEmailError.textContent = 'Please enter a valid email address.';
            signInEmailError.style.display = 'block';
            valid = false;
        }

        if (!password) {
            signInPasswordError.textContent = 'Password is required.';
            signInPasswordError.style.display = 'block';
            valid = false;
        }

        if (valid) {
            window.location.href = '../User/Patient/home/user_home/index.html';
        }
    });

    // Forgot password logic
    const forgotPasswordEmailInput = document.getElementById('forgot-password-email');
    const forgotPasswordSubmitButton = document.getElementById('forgot-password-submit');
    const forgotPasswordMessage = document.getElementById('forgot-password-message');

    forgotPasswordSubmitButton.addEventListener('click', (e) => {
        e.preventDefault();

        const email = forgotPasswordEmailInput.value.trim();
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (emailRegex.test(email)) {
            forgotPasswordMessage.textContent = 'A password reset link has been sent to your email address.';
            forgotPasswordMessage.style.color = 'green';
        } else {
            forgotPasswordMessage.textContent = 'Please enter a valid email address.';
            forgotPasswordMessage.style.color = 'red';
        }
    });
});