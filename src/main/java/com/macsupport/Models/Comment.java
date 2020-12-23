package com.macsupport.Models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;




@Entity
@Table(name="comments")
public class Comment {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	private String comment;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "events_comments", 
        joinColumns = @JoinColumn(name = "comment_id"), 
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
	private List<Event> events;
	
	
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    public Comment() {}
    
    public Comment(String comment, Event event) {
    	this.comment = comment;
   
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Event> getEvent() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
    

}
