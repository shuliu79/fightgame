/**
 * Created by asus-pc on 2016-7-21.
 */

FT = {};

FT.DB_NAME = "FightDB";
FT.TABLE_TOKEN = "TOKEN";

FT.DBConnector = (function () {
    function connector(args) {
        var getTransaction = function (storeName) {
            var readWriteMode = typeof IDBTransaction.READ_WRITE == "undefined" ? "readwrite" : IDBTransaction.READ_WRITE;
            transaction = db.transaction(storeName, readWriteMode);
            return transaction;
        };
        //设置args变量为接收的参数或者为空（如果没有提供的话）
        var args = args || {};
        //设置name参数
        this.name = 'connector';
        //设置pointX的值
        this.version = args.version || 1; //从接收的参数里获取，或者设置为默认值

        var db;

        this.getDB = function () {
            return db;
        };

        var openDBquest = null;

        this.addOpenDBEvent = function (func) {
            openDBquest.addEventListener("success", func, false)
        };

        this.openDB = function (successcallback, errorcallback) {
            var version = this.version;
            var request = indexedDB.open(FT.DB_NAME, version);
            openDBquest = request;
            request.onerror = function (e) {
                console.log(e.currentTarget.error.message);
                if (errorcallback != null) {
                    errorcallback(e)
                }
            };
            request.onsuccess = function (e) {
                db = e.target.result;
                console.log("success");
                if (successcallback != null) {
                    successcallback(e)
                }
            };
            request.onupgradeneeded = function (e) {
                db = e.target.result;
                console.log("create or update objectStore");
                if (!db.objectStoreNames.contains("token")) {
                    db.createObjectStore(FT.TABLE_TOKEN, {keyPath: "id"});
                }
                console.log('DB version changed to ' + version);
            };
        };

        this.deleteDB = function () {
            indexedDB.deleteDatabase(FT.DB_NAME);
        };

        this.closeDB = function () {
            db.closeDB()
        };

        function insert(storeName, data, onsuccess, onerror) {
            var transaction = getTransaction(storeName);
            var store = transaction.objectStore(storeName);
            var addReq = store.add(data);
            addReq.onsuccess = onsuccess;
            addReq.onerror = onerror;
        };

        function put(storeName, data, onsuccess, onerror) {
            var transaction = db.transaction(storeName, 'readwrite');
            var store = transaction.objectStore(storeName);
            var addReq = store.put(data);
            addReq.onsuccess = onsuccess;
            addReq.onerror = onerror;
        };

        function getData(storeName, id, onsuccess, onerror) {
            var transaction = getTransaction(storeName);
            var store = transaction.objectStore(storeName);
            var request = store.get(id);
            request.onsuccess = function (e) {
                var result = e.target.result;
                if (onsuccess != null) {
                    onsuccess(result)
                }
            };
            request.onerror = function (e) {
                console.log(e);
                onerror(e);
            }
        };

        this.addToken = function (token, onsuccess, onerror) {
            put(FT.TABLE_TOKEN, {id: 1, token: token}, onsuccess, onerror)
        }

        this.getToken = function (onsuccess, onerror) {
            getData(FT.TABLE_TOKEN, 1, onsuccess, onerror)
        }

    }

    var instance;

    var _static = {
        name: 'DBConnector',

        //获取实例的方法
        //返回Singleton的实例
        getInstance: function (args) {
            if (instance === undefined) {
                instance = new connector(args);
            }
            return instance;
        }
    };
    return _static;
})();
