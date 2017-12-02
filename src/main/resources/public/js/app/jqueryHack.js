/**
 * 
 */
var originDeferred = $.Deferred;
$.Deferred = function () {
    var def = originDeferred.apply(this, arguments);

    promise = def.promise;

    def.promise = (obj) => {
        var objPromise = promise(obj);

        objPromise.success = objPromise.done;
        objPromise.error = objPromise.fail;

        return objPromise;
    }

    return def;
}