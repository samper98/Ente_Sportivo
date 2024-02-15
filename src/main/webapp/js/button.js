/**
 * 
 */
function setupButton(){
 const b = document.getElementById("btn");
    b.addEventListener("mouseover", function() {
        b.style.backgroundColor = "white";
    });
    b.addEventListener("mouseout", function() {
        b.style.backgroundColor = "green";
    });
    }