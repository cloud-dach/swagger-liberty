# swagger-liberty 2.0
This is a modified version of the Swagger JAX-RS example using using IBM WebSphere Liberty Application Server on IBM Bluemix. The example can be pushed to IBM Bluemix, see example manifest.yml for the CF Java build pack parameters to enable the right features for that application.
The original Tutorial can be found here [blog](http://www.mycloudtips.com/2014/10/jax-rs-swagger-liberty-bluemix.html)
# Modifications
- converted to Maven project
- updated to Swagger 2.0 and JAX-RS 2.0 specs
- eliminated web.xml
- updated swagger ui components
- fixed annotations and swagger spec to comply with IBM API Management swagger import
- use CF_APPLICATION env to get hostname for swagger.json when running on Bluemix
- provided sample manifest.yml with Java Build Pack options to get it running on IBM Bluemix

## Steps
- build the project with mvn package or in Eclipse
- adopt hostname and application name in manifest.yml
- cf login, cf push to push the app to bluemix
- access the URL of the app and append /swagger-liberty 
- you should now see the swagger ui with the correct URI to swagger.json

 
