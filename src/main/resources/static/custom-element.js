class CustomElement extends HTMLElement {
  constructor() {
    super();
    this.attachShadow({ mode: 'open' });
    this.shadowRoot.innerHTML = `
      <style>
        :host {
          display: block;
          padding: 10px;
          border: 1px solid #ccc;
        }
      </style>
      <div id="content">initial content</div>
    `;
  }

  setText(text) {
    const contentDiv = this.shadowRoot.getElementById('content');
    contentDiv.textContent = text;
  }

  getText() {
    const contentDiv = this.shadowRoot.getElementById('content');
    return contentDiv.textContent;
  }
}

customElements.define('custom-element', CustomElement);