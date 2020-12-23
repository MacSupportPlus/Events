package com.macsupport.Controllers;

import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macsupport.Models.Comment;
import com.macsupport.Models.Event;
import com.macsupport.Models.User;
import com.macsupport.Services.CommentService;
import com.macsupport.Services.EventService;
import com.macsupport.Services.UserService;


@RequestMapping("/events")
public class EventController {
	
	private final UserService userService;
	private final EventService eventService;
	private final CommentService commentService;
	
	
	public EventController(UserService uService, EventService eService, CommentService cService) {
		this.userService = uService;
		this.eventService = eService;
		this.commentService = cService;
		
	}
	@RequestMapping("")
    public String dashboard(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
    	if(session.getAttribute("user_id") == null) {
    		// user is not logged in
    		redirectAttributes.addFlashAttribute("success_error", "Must be logged in to continue");
    		return "redirect:/sign_in";
    	}
    	model.addAttribute("allEvents", eventService.getAll());
        return "event.jsp";
    }
	@RequestMapping("/new")
	public String newEvent()  {
		return "event.jsp";
	}
	@RequestMapping("/{event_id}")
	public String showQuestion(@PathVariable("event_id") Long id, Model model) {
		Event event = eventService.getEventById(id);
		model.addAttribute("oneEvebt", event);
		return "show.jsp";
	}
	@PostMapping("/{event_id}")
	public String addComment(@PathVariable("comment_id") Long id, @RequestParam("comments") String comment) {
		Event event = eventService.getEventById(id);
		Comment newComment = new Comment(comment, event);
		commentService.addComment(newComment);
		return "redirect:/events/" + id;
	}
	@PostMapping("")
	public String addEvent(@RequestParam("event") String event, Integer date, String location, User user, @RequestParam("comments") String comments, HttpSession session) {
		// get user object, from the id in session
		System.out.println("--------------------------------------------------");		Long user_id = (Long)session.getAttribute("user_id");
		User logged_in_user = userService.findUserById(user_id);
		System.out.println(logged_in_user);
		
		Event newEvent = new Event(event, date, comments, logged_in_user);
//		
//		// handle the tag creation
//		// separate the tags by commas
		List<Comment> eventComments = newEvent.getComments();
		
		String[] arrOfComments = event.split(",");
		for(String tag:arrOfComments) {
			System.out.println(comments.trim());
			// get the tag object
			Comment commentInDB = commentService.findCommentByComment(tag);
			// create a new tag, and add to question tag list
			if(commentInDB == null) {
				// add tag to DB, then add tag to question
				Comment newComment = new Comment(comments, newEvent);
				Comment savedComment = commentService.addComment(newComment);
				// add the tag to the question
				eventComments.add(savedComment);
			} else {
				// add tag to question that was in DB
				eventComments.add(commentInDB);
			}
			newEvent.setComments(eventComments);
			
		}
		// save the question, and the many-to-many relationship to DB, after we checked all tags from the form
		eventService.addEvent(newEvent);
		return "redirect:/events";
	}

	
}
