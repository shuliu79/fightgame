class UrlMappings {

	static mappings = {

        "/f/$action?"(controller: "commonInterface")

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }


        "/"(view:"/index")
//        "500"(view:'/error')
        "500"(controller: 'exceptionHandler' ,action: 'index')

    }
}
