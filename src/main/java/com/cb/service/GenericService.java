package com.cb.service;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

@Path("/")
public class GenericService {
	
	@POST
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response signup(@FormParam("username") String username, @FormParam("password") String password) throws Exception {
		String url = "http://localhost:8080/CB/login/login";
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("username", username));
		urlParameters.add(new BasicNameValuePair("password", password));
		
		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		
		if(response.getStatusLine().getStatusCode() == 200){
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(200).entity(result).build();
		}else{
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(401).entity(result).build();
		}

	}
	
	@POST
	@Path("signup")
	@Produces(MediaType.APPLICATION_JSON)
	public Response signup(@FormParam("username") String username, @FormParam("password") String password, @FormParam("email") String email) throws Exception {
		String url = "http://localhost:8080/CB/login/register";
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("username", username));
		urlParameters.add(new BasicNameValuePair("password", password));
		urlParameters.add(new BasicNameValuePair("email", email));
		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		
		if(response.getStatusLine().getStatusCode() == 200){
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(200).entity(result).build();
		}else{
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(401).entity(result).build();
		}
		
	}
	
	@GET
	@Path("profile/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfile(@PathParam("username") String username) throws Exception {
		String url = "http://localhost:8080/CB/rest/profile/" + username;
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		String auth = "admin:";
		
		byte[] byteArray = Base64.encodeBase64(auth.getBytes());
		String encoding = new String(byteArray);
		
		request.setHeader("Authorization", "Basic " + encoding);
		
		HttpResponse response = client.execute(request);
		
		if(response.getStatusLine().getStatusCode() == 200){
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(200).entity(result).build();
		}else{
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(401).entity(result).build();
		}

	}
	
	
	@GET
	@Path("history/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHistory(@PathParam("username") String username) throws Exception {
		String url = "http://localhost:8080/CB/rest/history/" + username;
		
		//System.out.println("Username in Web Service: " + username);
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		String auth = "admin:";
		
		byte[] byteArray = Base64.encodeBase64(auth.getBytes());
		String encoding = new String(byteArray);
		
		request.setHeader("Authorization", "Basic " + encoding);
		
		HttpResponse response = client.execute(request);
		
		if(response.getStatusLine().getStatusCode() == 200){
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(200).entity(result).build();
		}else{
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(401).entity(result).build();
		}

	}
	
	@GET
	@Path("cart/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getItemsInCart(@PathParam("username") String username) throws Exception {
		String url = "http://localhost:8080/CB/rest/cart/" + username;
		
		//System.out.println("Username in Web Service: " + username);
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		String auth = "admin:";
		
		byte[] byteArray = Base64.encodeBase64(auth.getBytes());
		String encoding = new String(byteArray);
		
		request.setHeader("Authorization", "Basic " + encoding);
		
		HttpResponse response = client.execute(request);
		
		if(response.getStatusLine().getStatusCode() == 200){
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(200).entity(result).build();
		}else{
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(401).entity(result).build();
		}

	}
	
	
	@POST
	@Path("profile")
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveProfile(@FormParam("username") String username, @FormParam("email") String email, @FormParam("name") String name, @FormParam("address") String address, @FormParam("phoneno") String phoneno, @FormParam("dept") String dept) throws Exception {
		String url = "http://localhost:8080/CB/rest/profile/";
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		
		String auth = "admin:";
		
		byte[] byteArray = Base64.encodeBase64(auth.getBytes());
		String encoding = new String(byteArray);
		
		post.setHeader("Authorization", "Basic " + encoding);
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("username", username));
		urlParameters.add(new BasicNameValuePair("name", name));
		urlParameters.add(new BasicNameValuePair("email", email));
		urlParameters.add(new BasicNameValuePair("address", address));
		urlParameters.add(new BasicNameValuePair("phoneno", phoneno));
		urlParameters.add(new BasicNameValuePair("dept", dept));
		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		
		if(response.getStatusLine().getStatusCode() == 200){
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(200).entity(result).build();
		}else{
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(401).entity(result).build();
		}

	}
	
	@POST
	@Path("review")
	@Produces(MediaType.APPLICATION_JSON)
	public Response writeReview(@FormParam("username") String username, @FormParam("courseno") String courseno, @FormParam("rating") String rating, @FormParam("review") String review) throws Exception {
		String url = "http://localhost:8080/CB/rest/review";
		
		System.out.println("Course number: " + courseno);
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		
		String auth = "admin:";
		
		byte[] byteArray = Base64.encodeBase64(auth.getBytes());
		String encoding = new String(byteArray);
		
		post.setHeader("Authorization", "Basic " + encoding);
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("username", username));
		urlParameters.add(new BasicNameValuePair("courseno", courseno));
		urlParameters.add(new BasicNameValuePair("rating", rating));
		urlParameters.add(new BasicNameValuePair("review", review));
		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		
		if(response.getStatusLine().getStatusCode() == 200){
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(200).entity(result).build();
		}else{
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(401).entity(result).build();
		}

	}
	
	@POST
	@Path("cart")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addToCart(@FormParam("username") String username, @FormParam("courseno") String courseno) throws Exception {
		String url = "http://localhost:8080/CB/rest/cart";
		
//		System.out.println("Course number: " + courseno);
//		System.out.println("Username: " + username);
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		
		String auth = "admin:";
		
		byte[] byteArray = Base64.encodeBase64(auth.getBytes());
		String encoding = new String(byteArray);
		
		post.setHeader("Authorization", "Basic " + encoding);
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("username", username));
		urlParameters.add(new BasicNameValuePair("courseno", courseno));
		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		
		if(response.getStatusLine().getStatusCode() == 200){
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(200).entity(result).build();
		}else{
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(401).entity(result).build();
		}

	}
	
	@POST
	@Path("removeFromCart")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeFromCart(@FormParam("username") String username, @FormParam("classNumber") String classNumber) throws Exception {
		String url = "http://localhost:8080/CB/rest/cart/removeFromCart";
				
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		
		String auth = "admin:";
		
		byte[] byteArray = Base64.encodeBase64(auth.getBytes());
		String encoding = new String(byteArray);
		
		post.setHeader("Authorization", "Basic " + encoding);
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("username", username));
		urlParameters.add(new BasicNameValuePair("classNumber", classNumber));
		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		
		if(response.getStatusLine().getStatusCode() == 200){
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(200).entity(result).build();
		}else{
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(401).entity(result).build();
		}

	}
	
	@POST
	@Path("checkout")
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkout(@FormParam("username") String username) throws Exception {
		String url = "http://localhost:8080/CB/rest/checkout";

		System.out.println("In Web Service checkout");
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");

		String auth = "admin:";

		byte[] byteArray = Base64.encodeBase64(auth.getBytes());
		String encoding = new String(byteArray);
		
		post.setHeader("Authorization", "Basic " + encoding);
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("username", username));
		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		
		if(response.getStatusLine().getStatusCode() == 200){
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(200).entity(result).build();
		}else{
			System.out.println("Failing");
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(401).entity(result).build();
		}

	}
	
	@POST
	@Path("search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response search(@FormParam("searchkey") String key) throws Exception {
		String url = "http://localhost:8080/CB/rest/course/";
		
		System.out.println("Search Key in Web Server: " + key);
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		
		String auth = "admin:";
		
		byte[] byteArray = Base64.encodeBase64(auth.getBytes());
		String encoding = new String(byteArray);
		
		post.setHeader("Authorization", "Basic " + encoding);
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("searchkey", key));
		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		
		if(response.getStatusLine().getStatusCode() == 200){
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(200).entity(result).build();
		}else{
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			return Response.status(401).entity(result).build();
		}

	}
}
