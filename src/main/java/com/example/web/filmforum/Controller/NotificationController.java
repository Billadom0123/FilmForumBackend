package com.example.web.filmforum.Controller;

import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Service.Notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/list")
    public DataResponse list(@RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "10") int size) {
        return notificationService.list(page, size);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/count")
    public DataResponse count() {
        return notificationService.unreadCount();
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @PostMapping("/{id}/read")
    public DataResponse read(@PathVariable("id") Long id) {
        return notificationService.markRead(id);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @PostMapping("/readAll")
    public DataResponse readAll() {
        return notificationService.markAllRead();
    }
}
