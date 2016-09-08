package com.liushu.game.fight

import grails.converters.JSON

class ExceptionHandlerController {

    def index() {
        try {
            if (true) {
//            if (request.getHeader("x-requested-with").equals("XMLHttpRequest")) {
                Exception exception = request.exception
                response.setStatus(200)
                def message = MsgUtils.errorMsg(exception.getMessage())
                render message
                return
            }
        }catch (Exception e){
            render ([success:false,msg:e.getMessage()] as JSON)
            return
        }
        response.withFormat {
            json {
                response.setHeader("Rest-Exception-Handler","true")
                try {
                    def exception = request.exception
                    if (exception) {
                        def message = new HashMap(
                                status: request.'javax.servlet.error.status_code',
                                url: request.forwardURI ?: request.'javax.servlet.error.request_uri',
                                message: exception.message,
                                developerMessage: exception.cause.class.name,
                                stackTrace: exception.stackTrace
                        )
                        render message as JSON
                    } else {
                        render new HashMap(
                                status: request.'javax.servlet.error.status_code',
                                url: request.forwardURI ?: request.'javax.servlet.error.request_uri',
                                message: 'no exception'
                        ) as JSON
                    }
                } catch (Exception ex) {
                    render new HashMap(message: "can't handler error:" + ex.getMessage()) as JSON
                }
            }
            '*' { render(view: '/error') }
        }

        /*if (request.getHeader("Accept").contains("application/json")) {//todo
            try {
                def exception = request.exception
                if (exception) {
                    def message = new RestExceptionMessage(
                            status: request.'javax.servlet.error.status_code',
                            url: request.forwardURI ?: request.'javax.servlet.error.request_uri',
                            message: exception.message,
                            developerMessage: exception.cause.class.name,
                            stackTrace: exception.stackTrace
                    )
                    render message as JSON
                } else {
                    render new HashMap(
                            status: request.'javax.servlet.error.status_code',
                            url: request.forwardURI ?: request.'javax.servlet.error.request_uri',
                            message: 'no exception'
                    ) as JSON
                }
            } catch (Exception e) {
                render new HashMap(message: "can't handler error") as JSON
            }
        } else {
//            def map = new HashMap()
            render(view: '/error')
        }*/
    }
}

