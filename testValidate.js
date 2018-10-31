// alert("connected");
function validate() {
    // Validate email -> use HTML5
    var name = document.getElementById('username').value;
    
    var nameFilter = /^[A-Za-z\s]+$/ //returns true if matched, vaidates for a-z and A-Z and white space

    if (!nameFilter.test(name)) {
        alert('Please enter a valid name.');
        return false;
    }

    if (document.querySelector("#password").value === "" || document.querySelector("#email").value === "" || document.querySelector("#phone").value === ""){
        alert('Please enter information.');
        return false;
    }
    
    if (document.querySelector("#userAndTerms").checked == false){
        alert('Please agree the terms and conditions.');
        return false;
    }

    return true;
}