package models;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import play.db.jpa.Model;

public class Calendar implements Iterable<Event> {
	
	public User owner;
	public String name;
	public PriorityQueue<Event> events;
	public PriorityQueue<Event> publicEvents;
	
	public Calendar(User owner, String name) {
		this.owner = owner;
		this.name = name;
		this.events = new PriorityQueue<Event>();
		this.publicEvents = new PriorityQueue<Event>();
	}
	
	public void addEvent(Event event) {
		if (event.isPublic()) {
			this.publicEvents.add(event);
		}
		this.events.add(event);
	}

	public User getOwner() {
		return this.owner;
	}
	
	public String getName() {
		return this.name;
	}

	public PriorityQueue<Event> getEvents() {
		return this.events;
	}
	
	public PriorityQueue<Event> getPublicEvents() {
		return this.publicEvents;
	}

	public boolean isEmpty() {
		return this.events.isEmpty();
	}

	public Event getNextEvent() {
		return this.events.peek();
	}
	
	public boolean isOwner(User user) {
		return owner.equals(user);
	}

	@Override
	public Iterator<Event> iterator() {
		ArrayList<Event> orderedEvents = new ArrayList<Event>();
		PriorityQueue<Event> events = new PriorityQueue<Event>(this.events);
		while (!events.isEmpty()) {
			orderedEvents.add(events.poll());
		}
		return orderedEvents.iterator();
	}

	public Iterator<Event> getEventsAfter(User user, Date startingDate)  {
		ArrayList<Event> iterableEvents = new ArrayList<Event>();
		if (user.equals(this.owner)) {
			for (Event event : this.events) {
				if (!event.getStartDate().before(startingDate)) {
					iterableEvents.add(event);
				}
			}
		} else {
			for (Event event : this.publicEvents) {
				if (!event.getStartDate().before(startingDate)) {
					iterableEvents.add(event);
				}
			}
		}
		return iterableEvents.iterator();
	}
	
}
