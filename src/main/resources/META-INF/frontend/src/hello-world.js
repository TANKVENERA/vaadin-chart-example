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
        <iron-scroll-threshold id="scrollTheshold" on-lower-threshold="_load" scroll-target="document">
           <iron-swipeable-container  on-iron-swipe="handleSwipe" scroll-target="document">
               <template id="list" is="dom-repeat" items="{{items}}">
                   <div  class="block">
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
               </template>
           </iron-swipeable-container >
        </iron-scroll-threshold>
      `;
    }


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

  }

customElements.define('hello-world', HelloWorld);