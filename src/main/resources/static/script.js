function searchBook(){
var input, filter, cards, cardContainer, title, i;
input = document.getElementById("search");
filter = input.value.toUpperCase();
cardContainer = document.getElementById("cardContainer");
cards = cardContainer.getElementByClassName("col");

for(i = 0; i<cards.length; i++)
{
title = cards[i].querySelector(".card-title");
var firstChar = title.innerSelector(".card-title");
var firstChar = title.innerText.charAt(0).toUppercase();
if(firstChar===filter)
{
cards[i].style.display = "";
}else
{
cards[i].style.display = "none";
}
}
}