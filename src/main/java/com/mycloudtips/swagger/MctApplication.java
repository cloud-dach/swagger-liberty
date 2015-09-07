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

import javax.ws.rs.core.Application;
//import io.swagger.jaxrs.listing.ApiListingResource;
//import io.swagger.jaxrs.listing.SwaggerSerializers;

/**
 * This is the entry point to configure the REST application. It is referenced
 * in the web.xml.
 * 
 * @author Michel Jaczynski
 *
 */
public class MctApplication extends Application {

	public MctApplication() {
		// TODO Auto-generated constructor stub
		BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        //beanConfig.setHost("localhost:8090");
        beanConfig.setBasePath("/swagger-liberty/rest");
        beanConfig.setResourcePackage("io.swagger.resources,com.mycloudtips.swagger");
        beanConfig.setScan(true);
	}
	
	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet();

        //resources.add(FirstResource.class);
        //resources.add(SecondResource.class);
        //...

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

}
