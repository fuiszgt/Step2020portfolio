// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */

$(document).ready(init);

function init(){
  initCardImgs();
  initClasses();
}

function initCardImgs(){
  $(".card-img").each( function(){
    src = 'url("images/' + $(this).attr('id') +'.jpg")';
    $(this).css("background-image", src);
  });
}


function initClickHandlers() {
  $(".card").click(openCard);
}

 function openCard(event){
   const card = event.target;
   $(card).addClass("open-card");
   $(card).click(closeCard);
 }

 function closeCard(event){
  const card = event.target;
  $(card).removeClass("open-card");
  $(card).click(openCard);
}