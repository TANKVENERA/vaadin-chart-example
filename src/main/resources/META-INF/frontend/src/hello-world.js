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
             width: 40%;
             margin-left: 30%;
             position: absolute;
           }
           .block{
                        padding-bottom: 15px;
                       }
        </style>
        
        //
//            <input value="{{hostProperty::_ddd}}" />

            <iron-list id="list" items="{{items::changed}}" as="item" scroll-target="document">
              <template>
              <div on-input="_ddd" class="block">
                   <iron-swipeable-container on-iron-swipe="_handleSwipe">
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
                   </iron-swipeable-container >
               </div>
              </template>
            </iron-list>
      `;
    }

    _ddd() {
        console.log('aaaaaaaa');
    }

    _handleSwipe(e) {
        console.log(e.model)
//        this.$.aaa.value = this.$.list.items[e.model.__data.index]
//        this.lololo = this.$.list.items[e.model.__data.index]
        delete this.$.list.items[e.model.__data.index]
//        console.log("After", this.$.aaa.value);
        this.$.list.notifyResize();
    }
  }

customElements.define('hello-world', HelloWorld);