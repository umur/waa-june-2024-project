package edu.miu.cs545.project.controller;

import edu.miu.cs545.project.model.entity.Event;
import edu.miu.cs545.project.service.CrudService;
import edu.miu.cs545.project.service.impl.EventServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/events")
@Tag(name = "Events", description = "Events API")
public class EventController extends CrudController<Event, Long> {


    public EventController(EventServiceImpl service) {
        super(service);
    }
}