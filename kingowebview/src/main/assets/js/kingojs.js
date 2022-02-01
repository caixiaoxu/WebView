var kingojs = {};
kingojs.os = {};
kingojs.os.isIOS = /iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);
kingojs.os.isAndroid = !xiangxuejs.os.isIOS;
kingojs.callbacks = {}

kingojs.takeNativeAction = function(commandname, parameters){
    console.log("kingojs takenativeaction")
    var request = {};
    // 为了保持ios和android的一致性，所以将请求封装成一个String
    request.name = commandname;
    request.param = parameters;
    if(window.kingojs.os.isAndroid){
        console.log("android take native action" + JSON.stringify(request));
        window.kingowebview.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.kingowebview.postMessage(JSON.stringify(request))
    }
}

kingojs.takeNativeActionWithCallback = function(commandname, parameters, callback) {
    var callbackname = "nativetojs_callback_" +  (new Date()).getTime() + "_" + Math.floor(Math.random() * 10000);
    kingojs.callbacks[callbackname] = callback;

    var request = {};
    request.name = commandname;
    request.param = parameters;
    request.param.callbackname = callbackname;
    if(window.kingojs.os.isAndroid){
        window.kingowebview.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.kingowebview.postMessage(JSON.stringify(request))
    }
}

kingojs.callback = function (callbackname, response) {
   var callbackobject = kingojs.callbacks[callbackname];
   if (callbackobject !== undefined){
       var ret = callbackobject(response);
       if(ret === false){
           return
       }
       delete kingojs.callbacks[callbackname];
   }
}