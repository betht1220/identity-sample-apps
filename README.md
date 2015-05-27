# Cloud Foundry UAA Sample Applications

This repo holds separate sample applications for each one of the four OAuth 2.0 grant types.

For more information on each of the applications please look at the README.md files the respective grant types.

# Deploying application to Cloud Foundry

When you don't have a pre-registered client in the UAA the following steps are recommended to have the application running properly.

Set the correct CF environment in the CF CLI:

    cf api api.<your-domain>
    
Go to your application directory and push the app:

    cf push --no-start
    
Go to ```https://console.<your-domain>```, bind the app to a service instance and copy the credentials under the bound services.

Set the following environment variables for your app using the copied values from the previous step:

    cf set-env <app-name> CLIENT_ID <client_id>
    cf set-env <app-name> CLIENT_SECRET <client_secret>
    cf set-env <app-name> ID_SERVICE_URL <auth_domain>
    
Start your app

    cf restart <app-name>
    
Now your app is ready to be used.
