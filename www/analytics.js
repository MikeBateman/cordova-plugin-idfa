 

var analytics = window.analytics || {};
  
analytics.getAdId = function(success, error) {
  cordova.exec(success, error, 'GAPluginAdId', 'getAdId', []);
};

window.analytics = analytics;
