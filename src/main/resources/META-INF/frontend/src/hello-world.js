import {PolymerElement,html} from '@polymer/polymer';
import '@polymer/paper-slider/paper-slider.js';
import '@polymer/iron-swipeable-container/iron-swipeable-container.js';
import '@polymer/paper-material/paper-material.js';
import '@polymer/paper-card/paper-card.js';
import '@polymer/iron-icon/iron-icon.js';
import '@polymer/paper-button/paper-button.js';
import '@polymer/paper-dialog/paper-dialog.js';

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
//          .block:hover {
//              position: absolute;
//              top:  50%;
//              left: 50%;
//              transform: translate(-50%,-50%);
//          }
        </style>
      <div>
      <label><iron-icon icon="search"></iron-icon> Iron swipeable container</label>
       <iron-swipeable-container>
       <div class="block">
        <paper-card>
           <div class="card-content">
             <div class="cafe-header">Cafe Basilico
               <div class="cafe-location cafe-light">
                 <iron-icon icon="search"></iron-icon>
                 <span>250ft</span>
               </div>
             </div>
             <p>$・Car Price</p>
             <p class="cafe-light">Small plates, salads &amp; sandwiches in an intimate setting with 12 indoor seats plus patio seating.</p>
           </div>
           <div class="card-actions">
             <p>Tonights availability</p>
             <paper-button raised onclick="this.widgetClicked()">More</paper-button>
           </div>
         </paper-card>

         <paper-dialog id="modal">
           <p>Lorem ipsum dolor sit amet, consecte</p>
           <div class="buttons">
             <paper-button dialog-confirm autofocus>Close</paper-button>
           </div>
         </paper-dialog>
         </div>
         <div class="block">
         <paper-card>
                <div class="card-content">
                  <div class="cafe-header">Cafe Basilico
                    <div class="cafe-location cafe-light">
                      <iron-icon icon="communication:location-on"></iron-icon>
                      <span>250ft</span>
                    </div>
                  </div>
                  <p>$・Car Price</p>
                  <p class="cafe-light">Another card, example is next advertisment</p>
                </div>
                <div class="card-actions">
                  <p>Tonights availability</p>
                </div>
          </paper-card>
          </div>
        </iron-swipeable-container>
      </div>
      `;
    }

   widgetClicked() {
     console.log('widget clicked');
     this.$.modal.open();

   }
  }

customElements.define('hello-world', HelloWorld);