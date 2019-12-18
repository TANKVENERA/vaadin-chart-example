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
        <style>
            :host{
                display: flex;
              }
              paper-card {
                      width: 100%;
                      height: 100%;
                    }
          .cafe-header { @apply --paper-font-headline; }
          .cafe-light { color: var(--paper-grey-600); }
          .cafe-location {
                float: right;
                font-size: 15px;
                vertical-align: middle;
          }
           .block {
              width: 60%;
              padding: 2%;
          }
        </style>
      <div>
           <iron-swipeable-container>
                 <iron-list items="{{items}} as="item">
                    <div class="block">
                        <paper-card>
                           <div class="card-content">
                             <div class="cafe-header">[[item]]
                               <div class="cafe-location cafe-light">
                                 <iron-icon icon="search"></iron-icon>
                               </div>
                             </div>
                             <p>$・Car Price</p>
                             <p class="cafe-light">Small plates, salads &amp; sandwiches in an intimate setting with 12 indoor seats plus patio seating.</p>
                           </div>
                           <div class="card-actions">
                                <p>Tonights availability</p>
                            </div>
                        </paper-card>
                    </div>
                 </iron-list>
           </iron-swipeable-container>
      </div>
      `;
    }

  }

customElements.define('hello-world', HelloWorld);