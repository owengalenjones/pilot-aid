var ghpages = require('gh-pages');

ghpages.publish('resources/public', {
  repo: 'git@github.com:owengalenjones/pilot-aid.git',
  message: 'Auto-generated commit',
  src: [
    'index.html',
    'js/compiled/app.js'
  ]
},
function(err) {
  if (typeof err !== 'undefined') {
    console.error("Error: ", err);
  }
});
