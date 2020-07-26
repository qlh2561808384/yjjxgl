/**
 * Created by Lustin on 2017/3/13.
 */
var AM={
    ajax:function (options) {
        var _beforSend=options.beforeSend;
        options.beforeSend=function (req) {
            req.setRequestHeader("key",top.currkey);
            req.setRequestHeader("sessID",top.sessId);
           if(_beforSend){
               _beforSend(req);
           }
        }
        jQuery.ajax(options);
    }

};