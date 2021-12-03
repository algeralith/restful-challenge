Building

The server was coded in Quarkus, which requires the following to be installed on your system.

JDK 11+
Apache Maven 3.8.1+

Once you have the environment set up, the problem can be compiled by exeucting the following commands.

./mvnw package

Once that process has complete, use the following command in order to run the server.

java -jar target/quarkus-app/quarkus-run.jar




The application makes use of H2 as its datasource. I currently it have it configured as a memory store, therefore data will disappear on relaunch of the application. It could be made persistent by using a flat file location, or even loading an alternative datasource like MySQL. Ideally, it would have been nice to have the tests make use of H2 mem database and configure a second datasource for persistent data.

As for Database interaction, everything is done through JPA / Hibernate. I've worked briefly with JPA in the past, though I'm far from knowedgable one how to use it properly. There were a lot of issues I ran into, mostly related to recursion of Parent / Child relationships within my entities.

I did not define any hard constraints between the entities, so they can be created within any order. So long as any references to one another actually exist.

Now, for the API calls. The server listens on port 8080, so http://localhost:8080 should be prepended to any call.
All functions will return status code 200 on success and 400 on failure.


/api/images

POST /api/images

    Example Request

        {  
            "title" : "Image",  
            "description" : "Image Descritpion"
        }

    Example Response 

        {
            "id": 1,
            "title": "Image",
            "description": "Image Description",
            "albums": []
        }

    GET /api/images/{id}

    Example Response -- Images will always be empty.
    {
        "id": 1,
        "title": "Image",
        "description": "Description",
        "albums": [        
            {
                "id": 1,
                "description": "Album 2",
                "images": []
            },
            {
                "id": 2,
                "description": "Album 2",
                "images": []
            }
        ]
    }


PUT /api/images/{id}

    Example Request

        {  
            "title" : "Updated Image",  
            "description" : " Updated Image Descritpion"
        }

    Example Response 

        {
            "id": 1,
            "title" : "Updated Image",  
            "description" : " Updated Image Descritpion"
            "albums": []
        }

DELETE /api/images/{id}

    Delete will return a status code 200 on success and a 400 failure.




/api/albums

POST /api/albums

    Example Request

    {  
        "description" : "Album",  
        "images" : [1 , 2]
    }

    Example Response 

    {
        "id": 1,
        "description": "Test Album",
        "images": [
            {
                "id": 1,
                "title": "Image 1",
                "description": "Image 1 Description",
                "albums": []
            },
            {
                "id": 2,
                "title": "Image 2",
                "description": "Image 2 Description",
                "albums": []
            }
        ]
    }

GET /api/albums/{id}

    Example Response 

    {
        "id": 1,
        "description": "Test Album",
        "images": [
            {
                "id": 1,
                "title": "Image 1",
                "description": "Image 1 Description",
                "albums": []
            },
            {
                "id": 2,
                "title": "Image 2",
                "description": "Image 2 Description",
                "albums": []
            }
        ]
    }

PUT /api/albums/{id}

    Example Request

    {  
        "description": "Updated Album Description",
        "images": [1, 5]
    }

    Example Response

    {
        "id": 1,
        "description": "Updated Album Description",
        "images": [
            {
                "id": 1,
                "title": "Image 1",
                "description": "Image 1 Description",
                "albums": []
            },
            {
                "id": 5,
                "title": "Image 5",
                "description": "Image 5 Description",
                "albums": []
            }
        ]
    }

DELETE /api/albums/{id}

    Delete will return a status code 200 on success and a 400 failure.





/api/products

POST /api/products

    Example Request

    {  
        "name" : "Product Name",  
        "description" : "Product Description",
        "album" : {
            "id" : 1
        }
	}

    Example Response 
    {
        "id": 1,
        "name": "Product Name",
        "description": "Product Description",
        "album": {
            "id": 1,
            "description": "Test Album",
            "images": [
                {
                    "id": 1,
                    "title": "Image 1",
                    "description": "Image 1 Description",
                    "albums": []
                },
                {
                    "id": 2,
                    "title": "Image 2",
                    "description": "Image 2 Description",
                    "albums": []
                }
            ]
        }

GET /api/products/{id}

    Example Response 

    {
        "id": 1,
        "name": "Product Name",
        "description": "Product Description",
        "album": {
            "id": 1,
            "description": "Test Album",
            "images": [
                {
                    "id": 1,
                    "title": "Image 1",
                    "description": "Image 1 Description",
                    "albums": []
                },
                {
                    "id": 2,
                    "title": "Image 2",
                    "description": "Image 2 Description",
                    "albums": []
                }
            ]
        }   
    }

PUT /api/products/{id}

    Example Request 

    {  
        "name" : "Updated Product Name",  
        "description" : "Updated Product Description",
        "album" : {
            "id" : 1
            }
        }
    }

    Example Response

    {
        "id": 1,
        "name": "Updated Product Name",
        "description": "Updated Product Description",
        "album": {
            "id": 1,
            "description": "Test Album",
            "images": [
                {
                    "id": 1,
                    "title": "Test Image",
                    "description": "Test Description",
                    "albums": []
                },
                {
                    "id": 2,
                    "title": "Test Image",
                    "description": "Test Description",
                    "albums": []
                }
            ]
        }
    }

DELETE /api/products/{id}

    Delete will return a status code 200 on success and a 400 failure.