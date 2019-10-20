# soil and green api

## installation

**requirements**
 - php 7.2
 - composer
 
**installation**

```

git clone git@github.com:DanielRhein/soilandgreen.git
cd sog-api
composer install

```  
 
## api endpoints

API Prefix: `\api`

**CRUD**


 
### crop

Path:  `crop`

*get entry/entries*
* HTTP **GET** https://soilandgreen.org/crop
* HTTP GET https://soilangreen.org/crop?size=20&page=5
* HTTP GET https://soilangreen.org/crop/123
* HTTP GET https://soilangreen.org/crop/123/period

*create entry* 
* HTTP **POST** https://soilangreen.org/crop

*update entry*
* HTTP **PUT** https://soilangreen.org/crop/123

*delete entry*
* HTTP **DELETE** https://soilangreen.org/crop/123

 
### period

Path: `period`

*get entry/entries*
* HTTP **GET** https://www.soilandgreen.org/period
* HTTP GET https://soilangreen.org/period?size=20&page=5
* HTTP GET https://soilangreen.org/period/123
* HTTP GET https://soilangreen.org/period/123/crop


*create entry* 
* HTTP **POST** https://soilangreen.org/period

*update entry*
* HTTP **PUT** https://soilangreen.org/period/123

*delete entry*
* HTTP **DELETE** https://soilangreen.org/period/123
 
### taxonomy

Path:  `taxonomy`

*get entry/entries*
* HTTP **GET** https://www.soilandgreen.org/taxonomy
* HTTP GET https://soilangreen.org/taxonomy?size=20&page=5
* HTTP GET https://soilangreen.org/taxonomy?plant_order=Solanum&size=20page=5
* HTTP GET https://soilangreen.org/taxonomy/123
* HTTP GET https://soilangreen.org/taxonomy/123/crop


*create entry* 
* HTTP **POST** https://soilangreen.org/taxonomy

*update entry*
* HTTP **PUT** https://soilangreen.org/taxonomy/123

*delete entry*
* HTTP **DELETE** https://soilangreen.org/taxonomy/123