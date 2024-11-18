package com.example.taskmanagerapp.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.taskmanagerapp.model.Label;
import com.example.taskmanagerapp.model.Status;
import com.example.taskmanagerapp.model.Task;
import com.example.taskmanagerapp.model.User;
import com.example.taskmanagerapp.service.LabelService;
import com.example.taskmanagerapp.service.StatusService;
import com.example.taskmanagerapp.service.TaskService;
import com.example.taskmanagerapp.service.UserService;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private LabelService labelService;

    @GetMapping("/tasks")
    public String listTasks(Model model) {
        List<Task> tasks = taskService.findAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }

    @GetMapping("/tasks/edit/{id}")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.findTaskById(id);
        List<User> users = userService.findAllUsers();
        List<Status> statusList = statusService.findAllStatus();
        model.addAttribute("task", task);
        model.addAttribute("users", users);
        model.addAttribute("statusList", statusList);
        return "tasks/edit";
    }

    @PostMapping("/tasks/edit")
    public String editTask(Task task) {
        taskService.updateTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        Task task = taskService.findTaskById(id);
        taskService.deleteTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/search")
    public String searchTask(@RequestParam(name = "name", required = false) String keyword,
            @RequestParam(name = "statusId", required = false) Long statusId,
            @RequestParam(name = "userId", required = false) Long userId,
            Model model) {
        List<Task> tasks = taskService.findTaskByParams(keyword, statusId, userId);
        List<User> users = userService.findAllUsers();
        List<Status> statusList = statusService.findAllStatus();
        model.addAttribute("users", users);
        model.addAttribute("statusList", statusList);
        model.addAttribute("tasks", tasks);
        return "tasks/search";
    }

    @GetMapping("/tasks/add")
    public String addTaskForm(Model model) {
        Task task = new Task();
        List<Label> labels = labelService.findAllLabels();
        List<User> users = userService.findAllUsers();
        List<Status> statusList = statusService.findAllStatus();
        model.addAttribute("task", task);
        model.addAttribute("labels", labels);
        model.addAttribute("users", users);
        model.addAttribute("statusList", statusList);
        return "tasks/add";
    }

    @PostMapping("/tasks/add")
    public String addTask(Task task, @RequestParam List<Long> labels) {
        Set<Label> labelSet = labels.stream()
                .map(id -> new Label(id))
                .collect(Collectors.toSet());
        taskService.addTask(task, labelSet);
        return "redirect:/tasks";
    }

}
