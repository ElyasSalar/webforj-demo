const addonsScript = document.createElement("script");
addonsScript.setAttribute("src", "/files/basis/addons-components/dwc-addons.esm.js");
addonsScript.setAttribute("type", "module");

const head = document.getElementsByTagName("head");
document.head.appendChild(addonsScript);