/* 
 * Javascript file that handles the quantity buttons in user items and their cart
 * increases and decreases the value in the quantity textfield for the item number
 */

function decreaseValue () {
    var value = parseInt(document.getElementById('addCart_quantity').value, 10);
    value = isNaN(value) ? 0 : value;
    if (value > 1) {
        value--;
    }
    document.getElementById('addCart_quantity').value = value;
}

function incrementValue () {
    var value = parseInt(document.getElementById('addCart_quantity').value, 10);
    value = isNaN(value) ? 0 : value;
    value++;
    document.getElementById('addCart_quantity').value = value;
}
