package com.example.resources;

import com.example.services.PersonService;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public class PersonResource {

    @Inject
    PersonService personService;

    // import templates...
    @Location("person.html") // Позволяет подсказать с каким html будем работать
            Template personTemplate; // Template -> Позволяет связать Qute и HTML
    // List<String> list = new ArrayList<>();

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getPostPage() { // TemplateInstance возвращает новый объект
        return personTemplate.data("people", "Пока людей нет");
    }

    @POST
    @Path("/addPerson")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    // addPerson?name=Sergey&age=19 // QueryParams
    // addPerson/Sergey/19 // PathParams
    // Qute (Qute Template Engine) -> шаблонизатор (это динамический HTML, позволяет в зависимости от какого-либо значения подставить в HTML это значение)
    // Аналоги Qute -> Freemarker, Thymeleaf, Mustache, Groovy, JSP...

    public TemplateInstance addPerson(@FormParam("name") String name,
                                      @FormParam("age") int age) {
        personService.add(name, age);
        return personTemplate.data("people", personService.getPeople());
    }

//    @GET
//    @Path("/getPeople")
//    @Produces(MediaType.TEXT_HTML)
//    public String getPeople() {
//        return "<h1>" + personService.getPeople() + "</h1>";
//    }
}
