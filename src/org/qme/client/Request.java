package org.qme.client;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * This represents a single request submitted to the game. The class also
 * contains the RequestType enum and the static queue that elements are placed
 * into and taken out of. Components should only submit requests when the game
 * state is changed, so the game (whether local or remote) can handle it. It
 * handles each request in FIFO order. PLEASE subclass this (and make your own
 * RequestType for it), so much more information can be carried.
 * @author adamhutchings
 * @since preA
 */
public class Request {
	
	/**
	 * This is the internal request queue that is accessed through 
	 */
	private static final LinkedBlockingQueue<Request> requestQueue
		 = new LinkedBlockingQueue<>();
	
	/**
	 * Insert a single request into the queue.
	 * @param request the request to insert
	 */
	public static void addRequest(Request request) {
		requestQueue.add(request);
	}
	
	/**
	 * Get one request from the queue. Should only be called by Application.
	 * @return the request, or null if none left
	 */
	public static Request takeRequest() {
		return requestQueue.poll();
	}
	
	/**
	 * The types that a request can be.
	 * @author adamhutchings
	 * @since preA
	 */
	public enum RequestType {
	}
	
	/**
	 * The type of a request.
	 */
	private RequestType type;
	
	/**
	 * Constructor - pass in a type.
	 * @param type the type of the request
	 */
	public Request(RequestType type) {
		this.type = type;
	}
	
	/**
	 * Get the type of the request.
	 * @return the type of the request.
	 */
	public RequestType type() {
		return this.type;
	}

}
