import {PolymerElement,html} from '@polymer/polymer';
import '@polymer/paper-slider/paper-slider.js';
import '@polymer/iron-swipeable-container/iron-swipeable-container.js';
import '@polymer/paper-material/paper-material.js';
import '@polymer/paper-card/paper-card.js';
import '@polymer/iron-icon/iron-icon.js';
import '@polymer/paper-button/paper-button.js';
import '@polymer/paper-dialog/paper-dialog.js';
import '@polymer/iron-list/iron-list.js';
import '@polymer/iron-scroll-threshold/iron-scroll-threshold.js';

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
           .block{
                padding-bottom: 15px;
           }
           iron-swipeable-container{
                width: 40%;
                margin-left: 30%;
                position: absolute;
           }
        </style>
<<<<<<< HEAD
        <iron-scroll-threshold id="scrollTheshold" on-lower-threshold="_load" scroll-target="document">
           <iron-swipeable-container  on-iron-swipe="handleSwipe" scroll-target="document">
               <template id="list" is="dom-repeat" items="{{items}}">
                   <div  class="block">
                       <paper-card>
=======
        
        //
//            <input value="{{hostProperty::_ddd}}" />

            <iron-list id="list" items="{{items::changed}}" as="item" scroll-target="document">
              <template>
              <div on-input="_ddd" class="block">
                   <iron-swipeable-container on-iron-swipe="_handleSwipe">
                        <paper-card>
>>>>>>> 60b02f872fa9da5cee059e81c7b7500b7e9cbf65
                           <div class="card-content">
                             <div class="cafe-header">{{item}}</div>
                             <p>$・Car Price</p>
                             <p class="cafe-light">Small plates, salads &amp; sandwiches in an intimate setting with 12 indoor seats plus patio seating.</p>
                           </div>
                           <div class="card-actions">
                                <p>Tonights availability</p>
                           </div>
<<<<<<< HEAD
                       </paper-card>
                   </div>
               </template>
           </iron-swipeable-container >
        </iron-scroll-threshold>
=======
                        </paper-card>
                   </iron-swipeable-container >
               </div>
              </template>
            </iron-list>
>>>>>>> 60b02f872fa9da5cee059e81c7b7500b7e9cbf65
      `;
    }


<<<<<<< HEAD
     _load (e) {
        this.$.list.items
        this.$server.loadMoreData(this.$.list.items.length);
        setTimeout(() => {
            this.$.scrollTheshold.clearTriggers();
        }, 500);
     }

        handleSwipe(e) {
            var i = this.$.list.indexForElement(e.detail.target)
            const item = this.$.list.items[i];
            this.$server.deleteItem(item);
        }

=======
    _handleSwipe(e) {
        console.log(e.model)
//        this.$.aaa.value = this.$.list.items[e.model.__data.index]
//        this.lololo = this.$.list.items[e.model.__data.index]
        delete this.$.list.items[e.model.__data.index]
//        console.log("After", this.$.aaa.value);
        this.$.list.notifyResize();
    }
>>>>>>> 60b02f872fa9da5cee059e81c7b7500b7e9cbf65
  }

customElements.define('hello-world', HelloWorld);