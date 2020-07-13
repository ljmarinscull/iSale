# iSale
iSale is a website for posting buy-sell ads. In iSale users can create an account and log in to create ads for products they want to sell. On the front page (public part of the site) users can see the most popular products or ads, as well as, all the products or ads created in iSale. In the products or ads you can see the date created, an image (or not) of the ad and the times it has been seen by other users. Also when entering the product details you can see the contact details of the advertiser so that users can contact him.

# Frontend

The technologies used in the development of the frontend are the following:
- Tailwind CSS
- Vuejs framawork
- VeeValidate
- Vue Router
- Vuex
- Axios
- JWT 

## Project structure


* tailwind.config.js -> tailwindcss congig file
* README.md
* postcss.config.js -> tailwindcss congig file
* package-lock.json
* package.json
* babel.config.js
  * src
  * main.js 
  * App.vue -> root app file
  * public -> Public files
  * views -> web site views
  * store -> global web site state by modules
  * services -> services to get data from the server
  * router -> web site routes
  * components -> visual components
  * assets -> web site assets
  * api -> Axios Api

# Backend

The technologies used in the development of the backend are the following:
- Spring Boot
- Programming language - > Java 8 
- PostgreSQL
- JWT

## Project structure
  * backend
     * src
         * main
             * java
                  * com
                       * webproject
                            * isale
                                * ISaleApplication.java
                                * business -> business logic
                                * controllers -> rest api controllers
                                * dao -> repository classes
                                * exceptions -> customs exceptions
                                * filter -> JWT handle class
                                * model -> app entities
                                * utils -> some utils classes and security config                                              
