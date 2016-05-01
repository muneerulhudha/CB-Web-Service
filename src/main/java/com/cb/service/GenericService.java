package com.cb.service;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
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
}
