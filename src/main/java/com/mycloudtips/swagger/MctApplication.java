/**
 *  Copyright 2014 Michel Jaczynski
 *  
 *  Visit my blog for more explanation and tips: 
 *  http://www.mycloudtips.com/
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.mycloudtips.swagger;

import io.swagger.jaxrs.config.BeanConfig;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
//import io.swagger.jaxrs.listing.ApiListingResource;
//import io.swagger.jaxrs.listing.SwaggerSerializers;




import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * This is the entry point to configure the REST application. It is referenced
 * in the web.xml.
 * 
 * @author Michel Jaczynski, Rene Meyer
 *
 */

@ApplicationPath("/rest")
public class MctApplication extends Application {

	
	public MctApplication() {
		// TODO Auto-generated constructor stub
		BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0");
        beanConfig.setTitle("Books API");
        beanConfig.setDescription("Simple Books API");
        beanConfig.setSchemes(new String[]{"http","https"});
        beanConfig.setBasePath("/rest");
        beanConfig.setResourcePackage("io.swagger.resources,com.mycloudtips.swagger");
        beanConfig.setScan(true);
        //try to get the host
        try {
			JSONObject json = parseVcap("VCAP_APPLICATION");
			JSONArray msg = (JSONArray) json.get("uris");
			beanConfig.setHost(msg.get(0).toString());
        	System.out.println("We are in a CF Cloud, the uri is " + beanConfig.getHost());
        } catch (Exception e) {
			// we are not in the cloud so there is no reliable way in the current context to get Host and Port
        	System.err.println("We are not in a CF Cloud");
        }
	}
	
	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet();
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        return resources;
    }

	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();
		// this is the application resource, declared as a singleton
		singletons.add(new MctResource());
		return singletons;
	}
	
	public static JSONObject parseVcap(String val) throws Exception {  
	    
		String vcap = System.getenv(val);  
	    if (vcap == null) {  
	        return null;  
	    }  
	    JSONObject jsonObject = null;  
	    JSONParser parser = new JSONParser();  
	    Object obj = parser.parse(vcap);  
	    jsonObject = (JSONObject) obj;  
	    return jsonObject;  
	}
}
