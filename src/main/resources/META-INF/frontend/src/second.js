import {PolymerElement,html} from '@polymer/polymer';
import '@polymer/paper-slider/paper-slider.js';
import '@polymer/iron-swipeable-container/iron-swipeable-container.js';
import '@polymer/paper-material/paper-material.js';
import '@polymer/paper-card/paper-card.js';
import '@polymer/iron-icon/iron-icon.js';
import '@polymer/paper-button/paper-button.js';
import '@polymer/paper-dialog/paper-dialog.js';
import '@polymer/iron-list/iron-list.js';

class HelloWorld extends PolymerElement {
    static get template() {
        return html`
        <dom-module id="child-element">
          <template>
            <style>
            </style>
            <div>
              <h2>[[name]]</h2> <!-- This will print Pazzle -->
            </div>
          </template>
          <script>
            Polymer({
              is: 'child-element',
              properties: {
                name: {
                  type: String,
                  value: ''
                }
        
              },
            });
          </script>
        </dom-module>
        `;
    }