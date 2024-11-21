class DwcExample extends HTMLElement {
  constructor() {
    super();
    const shadow = this.attachShadow({ mode: 'open' });

    const container = document.createElement('div');
    container.textContent = 'Hello from dwc-example!';
    container.style.cssText = `
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
      font-family: Arial, sans-serif;
      background: #f9f9f9;
    `;
    shadow.appendChild(container);
  }
}

customElements.define('dwc-example', DwcExample);