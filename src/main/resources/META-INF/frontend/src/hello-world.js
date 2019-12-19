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
          .cafe-header { @apply --paper-font-headline; }
          .cafe-light { color: var(--paper-grey-600); }
          .cafe-location {
                float: right;
                font-size: 15px;
                vertical-align: middle;
          }
           .block:hover {
                cursor: pointer;
                    }
          iron-list {
             width: 50%;
             margin-left: 25%;
             position: absolute;
           }
           .block{
            padding-bottom: 15px;
           }
        </style>
            <iron-list id="list" items="{{items}}" scroll-target="document">
              <template>
              <div class="block">
               <iron-swipeable-container >
                    <div on-track="_track">
                        <paper-card>
                           <div class="card-content">
                             <div class="cafe-header">{{item}}</div>
                             <p>$・Car Price</p>
                             <p class="cafe-light">Small plates, salads &amp; sandwiches in an intimate setting with 12 indoor seats plus patio seating.</p>
                           </div>
                           <div class="card-actions">
                                <p>Tonights availability</p>
                           </div>
                        </paper-card>
                    </div>
                 </iron-swipeable-container >
               </div
              </template>
            </iron-list>
      `;
    }

    _track(e) {
           switch(e.detail.state) {
                case 'track':
                   console.log("WWWW", e.detail.dx);
                    break;
                case 'end':
                 if (e.detail.dx > e.currentTarget.offsetWidth/2  || e.detail.dx*(-1) > e.currentTarget.offsetWidth/2) {
                 console.log("DDDDD ", e.currentTarget.offsetWidth, 'AAAA', e.detail.dx)
                    delete this.$.list.items[e.model.__data.index]
                    this.$.list.notifyResize();
                 }
                  break;
           }
    }
  }

customElements.define('hello-world', HelloWorld);